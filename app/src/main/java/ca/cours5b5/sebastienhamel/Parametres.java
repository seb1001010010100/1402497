package ca.cours5b5.sebastienhamel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Parametres extends AppCompatActivity {

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
