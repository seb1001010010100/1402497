package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.activites.AMenuPrincipal;
import ca.cours5b5.sebastienhamel.controleurs.Action;
import ca.cours5b5.sebastienhamel.controleurs.ControleurAction;
import ca.cours5b5.sebastienhamel.global.GCommande;
import ca.cours5b5.sebastienhamel.usagers.UsagerCourant;


public class VMenuPrincipal extends Vue {

    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonPartie;
    private Action actionPartie;

    private Button boutonConnexion;
    private Action actionConnexion;
    private Action actionDeconnexion;

    private Button boutonCoop;
    private Action actionOnline;

    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        recupererControles();

        demanderActions();

        installerListeners();

        if(UsagerCourant.siUsagerConnecte()){

            boutonConnexion.setText("deconnexion");

        }

    }


    private void recupererControles() {

        boutonParametres = findViewById(R.id.bouton_parametres);

        boutonPartie = findViewById(R.id.bouton_partie);

        boutonConnexion = findViewById(R.id.bouton_connection);

        boutonCoop = findViewById(R.id.bouton_coop);

    }

    private void demanderActions() {

        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);

        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);

        actionConnexion = ControleurAction.demanderAction(GCommande.OUVRIR_CONNEXION);

        actionDeconnexion = ControleurAction.demanderAction(GCommande.DECONNEXION);

        actionOnline = ControleurAction.demanderAction(GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU);

    }


    private void installerListeners() {

        installerListenerParametres();

        installerListenerPartie();

        installerListenerConnection();

        installerListenerEnligne();

    }

    private void installerListenerPartie() {

        boutonPartie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartie.executerDesQuePossible();
            }
        });

    }

    private void installerListenerEnligne() {

        boutonCoop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionOnline.executerDesQuePossible();
            }
        });

    }

    private void installerListenerParametres() {

        boutonParametres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionParametres.executerDesQuePossible();
            }
        });

    }

    private void installerListenerConnection() {

        boutonConnexion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UsagerCourant.siUsagerConnecte()){

                    actionDeconnexion.executerDesQuePossible();
                }else{

                    actionConnexion.executerDesQuePossible();

                }


            }
        });

    }


}
