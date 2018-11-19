package ca.cours5b5.sebastienhamel.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.controleurs.ControleurAction;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.sebastienhamel.global.GCommande;
import ca.cours5b5.sebastienhamel.global.GConstantes;
import ca.cours5b5.sebastienhamel.usagers.UsagerCourant;

public class AMenuPrincipal extends Activite implements Fournisseur {

    private Button buttonConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        buttonConnection = findViewById(R.id.bouton_connection);
        fournirActions();

    }

    private void fournirActions() {

        fournirActionOuvrirMenuParametres();

        fournirActionDemarrerPartie();

        fournirActionConnexion();

        fournirActionDeconnexion();

        fournirActionJoindreOuCreerPartieReseau();
    }

    private void fournirActionConnexion() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_CONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionConnexion();

                    }
                });
    }

    private void fournirActionDeconnexion() {

        ControleurAction.fournirAction(this,
                GCommande.DECONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionDeconnexion();

                    }
                });
    }

    private void fournirActionOuvrirMenuParametres() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionParametres();

                    }
                });
    }



    private void fournirActionDemarrerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartie();

                    }
                });
    }

    private void fournirActionJoindreOuCreerPartieReseau(){

        ControleurAction.fournirAction(this,
                GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartieReseau();

                    }
                });

    }

    private void transitionParametres(){

        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);

    }

    private void transitionPartie(){

        Intent intentionParametres = new Intent(this, APartie.class);
        startActivity(intentionParametres);

    }

    private void transitionPartieReseau(){

        Intent intentionReseau = new Intent(this, APartieReseau.class);
        intentionReseau.putExtra("FIXME_JSON_PARTIE_RESEAU",GConstantes.FIXME_JSON_PARTIE_RESEAU);
        startActivity(intentionReseau);

    }

    private void transitionConnexion(){

        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(fournisseursDeConnexion).build();

        this.startActivityForResult(intentionConnexion, GConstantes.CODE_CONNEXION);

    }

    private void transitionDeconnexion(){

        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>(){

            public void onComplete(@NonNull Task<Void> task){

                buttonConnection.setText("connexion");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GConstantes.CODE_CONNEXION){

            if(resultCode == RESULT_OK){



                buttonConnection.setText("deconnexion");
                Log.d("Connection", "connected" + UsagerCourant.getId());

            }else{



            }

        }
    }


}
