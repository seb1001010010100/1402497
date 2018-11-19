package ca.cours5b5.sebastienhamel.controleurs;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.sebastienhamel.donnees.ListenerChargement;
import ca.cours5b5.sebastienhamel.donnees.Serveur;
import ca.cours5b5.sebastienhamel.donnees.SourceDeDonnees;
import ca.cours5b5.sebastienhamel.exceptions.ErreurModele;
import ca.cours5b5.sebastienhamel.modeles.Identifiable;
import ca.cours5b5.sebastienhamel.modeles.MParametres;
import ca.cours5b5.sebastienhamel.modeles.MParametresPartie;
import ca.cours5b5.sebastienhamel.modeles.MPartie;
import ca.cours5b5.sebastienhamel.modeles.MPartieReseau;
import ca.cours5b5.sebastienhamel.modeles.Modele;
import ca.cours5b5.sebastienhamel.donnees.Disque;
import ca.cours5b5.sebastienhamel.usagers.UsagerCourant;

public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();
        listeDeSauvegardes.add(Serveur.getInstance());
        listeDeSauvegardes.add(Disque.getInstance());


    }

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){

        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }
    }

    static void getModele(final String nomModele, final ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null) {

            creerModeleEtChargerDonnees(nomModele, new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    listenerGetModele.reagirAuModele(modele);

                }
            });

        }else{

            listenerGetModele.reagirAuModele(modele);

        }



    }


//    private static Modele chargerViaSequenceDeChargement(final String nomModele){
//
//        Modele modele = creerModeleSelonNom(nomModele);
//
//        modelesEnMemoire.put(nomModele, modele);
//
//        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){
//
//            sourceDeDonnees.chargerModele(getCheminSauvegarde(nomModele));
//
//            if(objetJson != null){
//
//                modele.aPartirObjetJson(objetJson);
//                break;
//
//            }
//
//        }
//
//        return modele;
//    }

    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }


    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {

        if(nomModele.equals(MParametres.class.getSimpleName())){

            listenerGetModele.reagirAuModele(new MParametres());

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;
                    listenerGetModele.reagirAuModele(new MPartie(mParametres.getParametresPartie().cloner()));

                }
            });

        }else if(nomModele.equals(MPartieReseau.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;
                    listenerGetModele.reagirAuModele(new MPartieReseau(mParametres.getParametresPartie().cloner()));

                }
            });

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele){

        creerModeleSelonNom(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {

                modelesEnMemoire.put(nomModele, modele);
                chargerDonnees(modele, nomModele, listenerGetModele);

            }
        });

    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){

        chargementViaSequence(modele, getCheminSauvegarde(nomModele), listenerGetModele, 0);

    }

    private static void chargementViaSequence(Modele modele, String cheminDeSauvegarde,ListenerGetModele listenerGetModele, int indiceSourceCourante){

        chargementViaSourceCouranteOuSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminDeSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante){

        sequenceDeChargement[indiceSourceCourante].chargerModele(cheminDeSauvegarde, new ListenerChargement() {
            @Override
            public void reagirSucces(Map<String, Object> objetJson) {

                terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);

            }

            @Override
            public void reagirErreur(Exception e) {

                if(indiceSourceCourante == sequenceDeChargement.length -1){

                    terminerChargement(modele,listenerGetModele);

                }else{

                    chargementViaSourceSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

                }


            }
        });

    }

    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele){

        modele.aPartirObjetJson(objetJson);
        terminerChargement(modele,listenerGetModele);

    }

    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){

        listenerGetModele.reagirAuModele(modele);

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiciSourceCourante){

        chargementViaSequence(modele, cheminDeSauvegarde, listenerGetModele, indiciSourceCourante + 1);

    }

    public static void detruireModele(String nomModele) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            modelesEnMemoire.remove(nomModele);

            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){

                ControleurAction.oublierFournisseur((Fournisseur) modele);

            }
        }
    }

    private static String getCheminSauvegarde(String nomModele){



        if(nomModele.equals("Identifiable")){

            Identifiable identifiable = (Identifiable) modelesEnMemoire.get(nomModele);
            return nomModele + "/" + identifiable.getId();

        }

        return nomModele + "/" + UsagerCourant.getId();


    }

}
