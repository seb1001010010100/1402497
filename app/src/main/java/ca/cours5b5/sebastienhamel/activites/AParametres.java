package ca.cours5b5.sebastienhamel.activites;

import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.sebastienhamel.R;

public class AParametres extends Activite {

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
    }

}
