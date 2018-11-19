package ca.cours5b5.sebastienhamel.activites;

import android.os.Bundle;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.controleurs.ControlleurPartieReseau;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.global.GConstantes;

public class APartieReseau extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie_reseau);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ControlleurPartieReseau.getInstance().detruireSauvegardeServeur();
        ControlleurPartieReseau.getInstance().deconnecterDuServeur();

    }

    @Override
    protected void onResume() {

        super.onResume();
        ControlleurPartieReseau.getInstance().connecterAuServeur();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
