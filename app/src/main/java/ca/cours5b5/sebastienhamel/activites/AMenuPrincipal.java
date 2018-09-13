package ca.cours5b5.sebastienhamel.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.sebastienhamel.R;

public class AMenuPrincipal extends Activite {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Button boutonParametres = this.findViewById(R.id.bouton_parametres);
        boutonParametres.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(AMenuPrincipal.this, AParametres.class));

            }

        });
        Log.d("Atelier04","AMenuPrincipal :: onCreate");

    }

    static{

        Log.d("Atelier04","AMenuPrincipal :: static");

    }

    protected void onResume() {


        super.onResume();
        Log.d("Atelier04","AMenuPrincipal :: onResume");
    }

    protected void onPause() {


        super.onPause();
        Log.d("Atelier04","AMenuPrincipal :: onPause");

    }

    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        Log.d("Atelier04","AMenuPrincipal :: onSaveInstanceState");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Atelier04","AMenuPrincipal :: onDestroy");
    }
}
