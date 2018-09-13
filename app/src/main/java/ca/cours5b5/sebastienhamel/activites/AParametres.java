package ca.cours5b5.sebastienhamel.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Map;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.modeles.MParametres;
import ca.cours5b5.sebastienhamel.serialisation.Jsonification;

public class AParametres extends Activite {

    MParametres monModele = new MParametres();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Log.d("MonEtiquette","Bonjour!");
        Log.d("langue", this.getResources().getString(R.string.bienvenue));
        if(this.getResources().getBoolean(R.bool.est_portrait)){

            Log.d("portrait",
                    this.getResources().getString(R.string.bienvenue) + " (portrait)");

        }else{

            Log.d("portrait",
                    this.getResources().getString(R.string.bienvenue) + " (paysage)");

        }

        if(savedInstanceState != null){

            restaurerParametres(savedInstanceState);

        }
        Log.d("Atelier04","AParametres :: onCreate");
        Spinner spinnerGagner = this.findViewById(R.id.spinnerGagner);
        Spinner spinnerLargeur = this.findViewById(R.id.spinnerLargeur);
        Spinner spinnerHauteur = this.findViewById(R.id.spinnerHauteur);
        spinnerGagner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int pourGagner = (int) parent.getAdapter().getItem(position);
                monModele.pourGagner = pourGagner;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerHauteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int hauteur = (int) parent.getAdapter().getItem(position);
                monModele.hauteur = hauteur;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerLargeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int largeur = (int) parent.getAdapter().getItem(position);
                monModele.largeur = largeur;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    static{

        Log.d("Atelier04","AParametres :: static");

    }

    protected void onResume() {


        super.onResume();
        Log.d("Atelier04","AParametres :: onResume");
    }

    protected void onPause() {


        super.onPause();
        Log.d("Atelier04","AParametres :: onPause");

    }

    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);

        sauvegarderParametres(outState);

        Log.d("Atelier04","AParametres :: onSaveInstanceState");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Atelier04","AParametres :: onDestroy");
    }

    private void restaurerParametres(Bundle savedInstanceState){

        String json = savedInstanceState.getString("parametres");
        Log.d("json", json);
        Map<String, Object> objetJson = Jsonification.enOnjetJson(json);
        monModele.aPartirObjetJson(objetJson);

    }

    private void sauvegarderParametres(Bundle outState){

        Map<String, Object> objetJson = monModele.enObjetJson();
        String json = Jsonification.enChaine(objetJson);
        outState.putString("parametres", json);

    }
}
