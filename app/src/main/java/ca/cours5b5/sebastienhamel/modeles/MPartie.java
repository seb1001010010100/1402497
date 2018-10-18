package ca.cours5b5.sebastienhamel.modeles;

import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.sebastienhamel.controleurs.ControlleurAction;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.sebastienhamel.globale.GCommande;
import ca.cours5b5.sebastienhamel.globale.GCouleur;

import static ca.cours5b5.sebastienhamel.globale.GCouleur.JAUNE;
import static ca.cours5b5.sebastienhamel.globale.GCouleur.ROUGE;

public class MPartie extends Modele implements Fournisseur{

    public @Retention(RetentionPolicy.RUNTIME) @interface AttributSerialisable{


    }

    private MGrille grille;
    private GCouleur couleurCourante;

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    @AttributSerialisable
    public List<Integer> coups = new ArrayList<>();
    private final String __coups = "coups";

    public MPartie(MParametresPartie parametres){

        this.parametres = parametres;
        this.grille = new MGrille(parametres.getLargeur());
        initialiserCouleurCourante();
        fournirActionPlacerJeton();

    }



    public MParametresPartie getParametres() {
        return parametres;
    }

    public MGrille getGrille() {
        return grille;
    }

    private void initialiserCouleurCourante(){

        couleurCourante = JAUNE;


    }


    private void fournirActionPlacerJeton(){

        ControlleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                int colonne = ((int) args[0]);
                coups.add(colonne);
                jouerCoup(colonne);
                prochaineCouleurCourante();
            }
        });

    }

    protected void jouerCoup(int colonne){


        grille.placerJeton(colonne, couleurCourante);


    }

    private void prochaineCouleurCourante(){

        switch(couleurCourante){

            case ROUGE:
                couleurCourante = JAUNE;
                break;

            case JAUNE:
                couleurCourante = ROUGE;
                break;

        }

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {

        this.parametres = MParametres.getInstance().parametresPartie;
        this.grille = new MGrille(parametres.getLargeur());
        initialiserCouleurCourante();
        List<Integer> listecoups = new ArrayList<>();
        for(Map.Entry<String, Object> entry : objetJson.entrySet()){

            listecoups = listeCoupsAPartirJson((ArrayList)entry.getValue());

        }

        rejouerLesCoups(listecoups);

    }

    @Override
    public Map<String, Object> enObjetJson() {

        Map<String, Object> objetJson = new HashMap<>();
        Log.d("TEST", " " + coups);
        objetJson.put(__coups, listeCoupsEnObjetJson(coups));


        return objetJson;
    }

    private void rejouerLesCoups(List<Integer> coupsARejouer){

        for(Integer coup : coupsARejouer){

            this.jouerCoup(coup);
            prochaineCouleurCourante();

        }

    }

    private List<Integer> listeCoupsAPartirJson(List<String> listeCoupObjetJson){

        List<Integer> listeCoups = new ArrayList<>();
        for(String coup : listeCoupObjetJson){

            listeCoups.add(Integer.parseInt(coup));

        }
        return listeCoups;

    }

    private List<String> listeCoupsEnObjetJson(List<Integer> listeCoups){

        List<String> listeCoupsEnObjetJson = new ArrayList<>();
        for(int coup : listeCoups){

            listeCoupsEnObjetJson.add(Integer.toString(coup));

        }
        return listeCoupsEnObjetJson;

    }
}
