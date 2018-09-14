package ca.cours5b5.sebastienhamel.modeles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.sebastienhamel.globale.GConstantes;



public class MParametres extends Modele {

    public @Retention(RetentionPolicy.RUNTIME) @interface AttributSerialisable{


    }

    //FixMe
    public static MParametres instance;

    public static MParametres getInstance(){

        if(instance == null){

            instance = new MParametres();

        }

        return instance;

    }

    @AttributSerialisable
    public Integer hauteur = GConstantes.HAUTEURDEFAUT;
    private final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur = GConstantes.LARGEURDEFAUT;
    private final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner = GConstantes.GAGNERDEFAUT;
    private final String __pourGagner = "pourGagner";

    public List<Integer> getChoixHauteur() {

        ArrayList<Integer> listeLargeur = new ArrayList<Integer>();
        for(int i = GConstantes.HAUTEURMIN; i <= GConstantes.HAUTEURMAX; i++){

            listeLargeur.add(i);

        }
        return listeLargeur;
    }

    public List<Integer> getChoixLargeur(){

        ArrayList<Integer> listeLargeur = new ArrayList<Integer>();
       for(int i = GConstantes.LARGEURMIN; i <= GConstantes.LARGEURMAX; i++){

           listeLargeur.add(i);

       }
        return listeLargeur;
    }

    public List<Integer> getChoixPourGagner(){

        ArrayList<Integer> listePourGagner = new ArrayList<Integer>();

        for(int i = GConstantes.GAGNERMIN; i <= GConstantes.GAGNERMAX; i++){

            listePourGagner.add(i);

        }

        return listePourGagner;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
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
    public Map<String, Object> enObjetJson() {
        Map<String, Object> objetJson = new HashMap<>();
        objetJson.put(__hauteur, hauteur.toString());
        objetJson.put(__largeur, largeur.toString());
        objetJson.put(__pourGagner, pourGagner.toString());

        return objetJson;
    }
}
