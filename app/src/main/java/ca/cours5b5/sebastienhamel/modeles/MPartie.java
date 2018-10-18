package ca.cours5b5.sebastienhamel.modeles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
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

        if(couleurCourante == null){

            couleurCourante = JAUNE;

        }else{

            prochaineCouleurCourante();

        }

    }


    private void fournirActionPlacerJeton(){

        ControlleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                int colonne = ((int) args[0]);
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

    }

    @Override
    public Map<String, Object> enObjetJson() {
        return null;
    }
}
