package ca.cours5b5.sebastienhamel.activites;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.controleurs.ControlleurAction;
import ca.cours5b5.sebastienhamel.controleurs.ControlleurObservation;
import ca.cours5b5.sebastienhamel.modeles.MPartie;
import ca.cours5b5.sebastienhamel.serialisation.Jsonification;

public class APartie extends Activite {

    MPartie monModele = ControlleurObservation.partie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartie);

        if(savedInstanceState != null){

            restaurerParametres(savedInstanceState);

        }
    }
    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);

        sauvegarderParametres(outState);


    }

    private void restaurerParametres(Bundle savedInstanceState){

        String json = savedInstanceState.getString("MPartie");
        Log.d("Json Atelier 7", json);
        Map<String, Object> objetJson = Jsonification.enOnjetJson(json);
        monModele.aPartirObjetJson(objetJson);

    }

    private void sauvegarderParametres(Bundle outState){

        Map<String, Object> objetJson = monModele.enObjetJson();
        String json = Jsonification.enChaine(objetJson);
        Log.d("Json Atelier 7", json);
        outState.putString("MPartie", json);
        
    }

}
