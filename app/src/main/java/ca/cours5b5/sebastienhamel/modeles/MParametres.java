package ca.cours5b5.sebastienhamel.modeles;

import android.util.Log;

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

    public MParametres(){

        genererListesDeChoix();

    }

    @AttributSerialisable
    public MParametresPartie parametresPartie;
    private String __parametresPartie = "parametresPartie";


    private List<Integer> listeHauteur;
    private List<Integer> listeLargeur;
    private List<Integer> listePourGagner;

    public List<Integer> getChoixHauteur() {


        return listeHauteur;
    }

    public List<Integer> getChoixLargeur(){

        return listeLargeur;
    }

    public List<Integer> getChoixPourGagner(){

        return listePourGagner;
    }


    public MParametresPartie getMParametresPartie() {

        if(parametresPartie == null){

            parametresPartie = new MParametresPartie(GConstantes.HAUTEURDEFAUT,GConstantes.LARGEURDEFAUT,GConstantes.GAGNERDEFAUT);

        }

        return parametresPartie;
    }
    private void genererListesDeChoix(){
        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();
    }
    private List<Integer> genererListeChoix(int min, int max){
        List<Integer> listeChoix = new ArrayList<>();

        for(int i = min; i <= max; i++){
            listeChoix.add(i);
        }

        return listeChoix;
    }

    private void genererListeChoixHauteur(){
        listeHauteur = genererListeChoix(GConstantes.HAUTEURMIN, GConstantes.HAUTEURMAX);
    }

    private void genererListeChoixLargeur(){
        listeLargeur = genererListeChoix(GConstantes.LARGEURMIN, GConstantes.LARGEURMAX);
    }

    private void genererListeChoixPourGagner(){
        listePourGagner = genererListeChoix(GConstantes.GAGNERMIN, GConstantes.GAGNERMAX);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {

            if (entry.getKey().equals(__parametresPartie)) {

                this.parametresPartie.aPartirObjetJson((Map<String, Object>) entry.getValue());

            }

        }
    }

    @Override
    public Map<String, Object> enObjetJson() {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__parametresPartie, this.getMParametresPartie().enObjetJson());

        return objetJson;
    }
}
