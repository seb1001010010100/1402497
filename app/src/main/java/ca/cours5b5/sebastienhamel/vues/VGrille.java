package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import java.util.List;

public class VGrille extends GridLayout {


    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int nombreRangees;
    private List<VEntete> entetes;
    private VCase[][] lesCases;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    void creerGrille(int hauteur, int largeur){


    }

    private void initialiserTableauDeCases(int hauteur, int largeur){



    }

    private void ajouterEnTetes(int largeur){



    }

    private LayoutParams getMiseEnPageEntete(int colonne){

        return null;

    }

    private void ajouterCases(int hauteur, int largeur){



    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){

        return null;

    }
}
