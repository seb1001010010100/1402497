package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.text.MessageFormat;

import ca.cours5b5.sebastienhamel.globale.GCouleur;

public class VCase extends AppCompatButton{

    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int rangee, colonne;

    public VCase(Context context, int rangee, int colonne){
        super(context);
        this.rangee = rangee;
        this.colonne = colonne;
        this.setText(MessageFormat.format("{0}, {1}", rangee, colonne));

    }

    public void afficherJeton(GCouleur jeton){

        this.setBackgroundColor(Color.parseColor("#000000"));

    }
}
