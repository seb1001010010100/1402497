package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.controleurs.ControleurObservation;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.sebastienhamel.exceptions.ErreurObservation;
import ca.cours5b5.sebastienhamel.modeles.MParametresPartie;
import ca.cours5b5.sebastienhamel.modeles.MPartie;
import ca.cours5b5.sebastienhamel.modeles.MPartieReseau;
import ca.cours5b5.sebastienhamel.modeles.Modele;


public class VPartieReseau extends VPartie {


    private VGrille grille;


    public VPartieReseau(Context context) {
        super(context);
    }

    public VPartieReseau(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieReseau(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected String getNomModele(){
        return MPartieReseau.class.getSimpleName();
    }

}
