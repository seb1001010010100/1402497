package ca.cours5b5.sebastienhamel.activites;

import android.content.Intent;
import android.os.Bundle;
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

    }

}
