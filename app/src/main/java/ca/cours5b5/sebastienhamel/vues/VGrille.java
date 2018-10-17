package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;

import java.util.ArrayList;
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

        initialiserTableauDeCases(hauteur, largeur);
        ajouterCases(hauteur, largeur);
        ajouterEnTetes(largeur);
    }

    private void initialiserTableauDeCases(int hauteur, int largeur){

        lesCases = new VCase[hauteur][largeur];

    }

    private void ajouterEnTetes(int largeur){

        this.entetes = new ArrayList<>();
        for(int i = 0; i < largeur; i++){

            VEntete entete = new VEntete(this.getContext(), i);

            entetes.add(entete);
            this.addView(entete, this.getMiseEnPageEntete(i));

        }

    }

    private LayoutParams getMiseEnPageEntete(int colonne){

        int rangee = 0;
        float poidRangee = 3, poidColonne = 0;
        Spec specRangee = GridLayout.spec(rangee, poidRangee);
        Spec specColonne = GridLayout.spec(colonne, poidColonne);
        LayoutParams params = new LayoutParams(specRangee, specColonne);
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);

        return params;

    }

    private void ajouterCases(int hauteur, int largeur){

        for(int i = 0; i < hauteur; i++){

            for(int j = 0; j < largeur; j++){

                VCase vCase = new VCase(this.getContext(), i , j);
                this.lesCases[i][j] = vCase;
                this.addView(vCase, this.getMiseEnPageCase(hauteur - i - 1, j));

            }

        }

    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){

        float poidRangee = 1, poidColonne = 1;
        Spec specRangee = GridLayout.spec(rangee + 1, poidRangee);
        Spec specColonne = GridLayout.spec(colonne, poidColonne );
        LayoutParams params = new LayoutParams(specRangee, specColonne);
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);
        return params;

    }
}
