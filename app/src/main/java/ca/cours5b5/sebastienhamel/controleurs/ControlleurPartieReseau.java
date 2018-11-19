package ca.cours5b5.sebastienhamel.controleurs;


import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.sebastienhamel.donnees.Serveur;
import ca.cours5b5.sebastienhamel.global.GCommande;
import ca.cours5b5.sebastienhamel.global.GConstantes;
import ca.cours5b5.sebastienhamel.modeles.MPartieReseau;
import ca.cours5b5.sebastienhamel.modeles.Modele;
import ca.cours5b5.sebastienhamel.proxy.ProxyListe;
import ca.cours5b5.sebastienhamel.usagers.UsagerCourant;

public final class ControlleurPartieReseau {

    private static final ControlleurPartieReseau instance = new ControlleurPartieReseau();
    public static ControlleurPartieReseau getInstance(){

        return instance;

    }

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    public void connecterAuServeur(){


        ControleurModeles.getModele("MPartieReseau", new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                MPartieReseau mPartieReseau = (MPartieReseau) modele;

                connecterAuServeur(mPartieReseau.getId());

            }
        });

    }

    private void connecterAuServeur(String idJoueurHote){

        if(UsagerCourant.getId().equals(idJoueurHote)){

            connecterEnTantQueJoueurHote(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));
            Log.d("test2", UsagerCourant.getId() + " " + idJoueurHote);

        }else{

            connecterEnTantQueJoueurInvite(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        }

        proxyRecevoirCoups.connecterAuServeur();
        proxyEmettreCoups.connecterAuServeur();
        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);

    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite){

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);

    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite){

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);

    }

    public void deconnecterDuServeur(){

        proxyEmettreCoups.detruireValeurs();
        proxyEmettreCoups.deconnecterDuServeur();
        proxyRecevoirCoups.deconnecterDuServeur();

    }

    public void transmettreCoup(Integer idColonne){

        proxyEmettreCoups.ajouterValeur(idColonne);

    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote){


        return "MPartieReseau/"+ idJoueurHote + "/" + GConstantes.CLE_COUPS_JOUEUR_INVITE;
    }

    private String getCheminCoupsJoueurHote(String idJoueurHote){


        return "MPartieReseau/"+ idJoueurHote + "/" + GConstantes.CLE_COUPS_JOUEUR_HOTE;
    }

    private String getCheminPartie(String idJoueurHote){


        return  "MPartieReseau/"+ idJoueurHote;
    }

    public void detruireSauvegardeServeur(){

        Serveur.getInstance().detruireSauvegarde(getCheminPartie(UsagerCourant.getId()));

    }
}
