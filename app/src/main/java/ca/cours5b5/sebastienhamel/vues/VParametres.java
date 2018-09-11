package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.globale.GConstantes;

public class VParametres extends Vue {


    public VParametres(Context context) {
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //ajout des valeur (GConstantes) dans le spinnerGagner
        Spinner spinnerGagner = this.findViewById(R.id.spinnerGagner);
        ArrayAdapter<Integer> adapterGagner = new ArrayAdapter<>
                (this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinnerGagner.setAdapter(adapterGagner);
        for(int i = GConstantes.GAGNERMIN; i <= GConstantes.GAGNERMAX; i++){

            adapterGagner.add(i);

        }
        spinnerGagner.setSelection(GConstantes.GAGNERDEFAUT - GConstantes.GAGNERMIN);

        //ajout des valeur (GConstantes) dans le spinnerLargeur
        Spinner spinnerLargeur = this.findViewById(R.id.spinnerLargeur);
        ArrayAdapter<Integer> adapterLargeur = new ArrayAdapter<>
                (this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinnerLargeur.setAdapter(adapterLargeur);
        for(int i = GConstantes.LARGEURMIN; i <= GConstantes.LARGEURMAX; i++){

            adapterLargeur.add(i);

        }
        spinnerLargeur.setSelection(GConstantes.LARGEURDEFAUT - GConstantes.LARGEURMIN);

        //ajout des valeur (GConstantes) dans le spinnerHauteur
        Spinner spinnerHauteur = this.findViewById(R.id.spinnerHauteur);
        ArrayAdapter<Integer> adapterHauteur = new ArrayAdapter<>
                (this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinnerHauteur.setAdapter(adapterHauteur);
        for(int i = GConstantes.HAUTEURMIN; i <= GConstantes.HAUTEURMAX; i++){

            adapterHauteur.add(i);

        }
        spinnerHauteur.setSelection(GConstantes.HAUTEURDEFAUT - GConstantes.HAUTEURMIN);
        Log.d("Atelier04","VParametres :: onFinishInflate");
    }

    static{

        Log.d("Atelier04","VParametres :: static");

    }

}
