package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.controleurs.Action;
import ca.cours5b5.sebastienhamel.controleurs.ControlleurAction;
import ca.cours5b5.sebastienhamel.controleurs.ControlleurObservation;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.sebastienhamel.globale.GCommande;
import ca.cours5b5.sebastienhamel.modeles.MPartie;
import ca.cours5b5.sebastienhamel.modeles.Modele;

public class VPartie extends Vue {

    private VGrille grille;

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("atelier06","VPartie :: onFinishInflate");
        this.grille = findViewById(R.id.grillePartie);
        this.observerPartie();

    }

    private void observerPartie(){

        Log.d("atelier06","VPartie :: observerPartie");
        ControlleurObservation.observerModele(MPartie.class.getSimpleName(), new ListenerObservateur() {
            @Override
            public void reagirChangementAuModele(Modele modele) {
                Log.d("Atelier 7", "VPartie.reagirChangementAuModele");
                initialiserGrille((MPartie) modele);
                miseAJourGrille((MPartie) modele);;
                Log.d("atelier06","VPartie$1 :: reagirChangementAuModele");
            }
        });

    }
    private MPartie getPartie(Modele modele){

        return null;
    }

    private void initialiserGrille(MPartie partie){

        this.grille.creerGrille(partie.getParametres().getHauteur(),partie.getParametres().getLargeur());


    }

    private void miseAJourGrille(MPartie partie){

        grille.afficherJetons(partie.getGrille());

    }
}
