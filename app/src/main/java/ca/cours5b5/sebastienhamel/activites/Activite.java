package ca.cours5b5.sebastienhamel.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.cours5b5.sebastienhamel.controleurs.ControleurModeles;
import ca.cours5b5.sebastienhamel.donnees.Disque;
import ca.cours5b5.sebastienhamel.donnees.SauvegardeTemporaire;
import ca.cours5b5.sebastienhamel.donnees.Serveur;
import ca.cours5b5.sebastienhamel.donnees.Transition;
import ca.cours5b5.sebastienhamel.modeles.MParametres;


public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialiserControleurModeles(savedInstanceState);
        initialiserApplication();

    }

    protected void initialiserControleurModeles(Bundle savedInstanceState) {

        ControleurModeles.setSequenceDeChargement(
                new Transition(getIntent().getExtras()),
                Serveur.getInstance(),
                new SauvegardeTemporaire(savedInstanceState),
                Disque.getInstance()

                );

    }

    protected void initialiserApplication(){

        Disque.getInstance().setRepertoireRacine(getFilesDir());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ControleurModeles.sauvegarderModeleDansCetteSource(MParametres.class.getSimpleName(),
                new SauvegardeTemporaire(outState));
    }

}
