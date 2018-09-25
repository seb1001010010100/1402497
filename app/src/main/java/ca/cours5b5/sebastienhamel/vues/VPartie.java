package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.sebastienhamel.controleurs.ControlleurObservation;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerObservateur;
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

    }

    private void observerPartie(){

        ControlleurObservation.observerModele(VPartie.class.getSimpleName(), new ListenerObservateur() {
            @Override
            public void reagirChangementAuModele(Modele modele) {
                initialiserGrille((MPartie) modele);
            }
        });

    }
    private MPartie getPartie(Modele modele){

        return null;
    }

    private void initialiserGrille(MPartie partie){



    }
}
