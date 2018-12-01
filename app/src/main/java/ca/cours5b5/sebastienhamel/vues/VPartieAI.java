package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.sebastienhamel.modeles.MPartieAI;


public class VPartieAI extends VPartie {

    private VGrille grille;


    public VPartieAI(Context context) {
        super(context);
    }

    public VPartieAI(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieAI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected String getNomModele(){
        return MPartieAI.class.getSimpleName();
    }
}
