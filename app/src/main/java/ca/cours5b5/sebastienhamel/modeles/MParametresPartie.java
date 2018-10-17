package ca.cours5b5.sebastienhamel.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.sebastienhamel.exceptions.ErreurSerialisation;
import ca.cours5b5.sebastienhamel.globale.GConstantes;

public class MParametresPartie extends Modele{


    @MParametres.AttributSerialisable
    public Integer hauteur;
    private final String __hauteur = "hauteur";

    @MParametres.AttributSerialisable
    public Integer largeur;
    private final String __largeur = "largeur";

    @MParametres.AttributSerialisable
    public Integer pourGagner;
    private final String __pourGagner = "pourGagner";

    public MParametresPartie(int hauteur, int largeur, int pourGagner){

        this.hauteur = hauteur;
        this.largeur = largeur;
        this.pourGagner = pourGagner;

    }
    public MParametresPartie aPartirMParametres(MParametres mParametres){

        MParametresPartie mParametresPartie = cloner();
        mParametresPartie.hauteur = mParametres.getMParametresPartie().getHauteur();
        mParametresPartie.largeur = mParametres.getMParametresPartie().getLargeur();
        mParametresPartie.pourGagner = mParametres.getMParametresPartie().getPourGagner();
        return mParametresPartie;

    }

    public MParametresPartie cloner(){

        return new MParametresPartie(this.hauteur, this.largeur, this.pourGagner);

    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {

            String cle = entry.getKey();
            Object valeur = entry.getValue();
            switch(cle){

                case "hauteur":
                    hauteur = Integer.valueOf((String)valeur);
                    break;
                case "largeur":
                    largeur = Integer.valueOf((String)valeur);
                    break;
                case "pourGagner":
                    pourGagner = Integer.valueOf((String)valeur);
                    break;
            }


        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();
        objetJson.put(__hauteur, hauteur.toString());
        objetJson.put(__largeur, largeur.toString());
        objetJson.put(__pourGagner, pourGagner.toString());

        return objetJson;
    }
}
