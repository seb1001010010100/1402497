package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.sebastienhamel.R;
import ca.cours5b5.sebastienhamel.globale.GConstantes;
import ca.cours5b5.sebastienhamel.modeles.MParametres;

public class VParametres extends Vue {

    MParametres monModele = MParametres.getInstance();

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
        adapterGagner.addAll(monModele.getChoixPourGagner());
        spinnerGagner.setSelection(monModele.getMParametresPartie().getPourGagner() - GConstantes.GAGNERMIN);

        //ajout des valeur (GConstantes) dans le spinnerLargeur
        Spinner spinnerLargeur = this.findViewById(R.id.spinnerLargeur);
        ArrayAdapter<Integer> adapterLargeur = new ArrayAdapter<>
                (this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinnerLargeur.setAdapter(adapterLargeur);

        adapterLargeur.addAll(monModele.getChoixLargeur());
        spinnerLargeur.setSelection(monModele.getMParametresPartie().getLargeur() - GConstantes.LARGEURMIN);

        //ajout des valeur (GConstantes) dans le spinnerHauteur
        Spinner spinnerHauteur = this.findViewById(R.id.spinnerHauteur);
        ArrayAdapter<Integer> adapterHauteur = new ArrayAdapter<>
                (this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinnerHauteur.setAdapter(adapterHauteur);
        adapterHauteur.addAll(monModele.getChoixHauteur());
        spinnerHauteur.setSelection(monModele.getMParametresPartie().getHauteur() - GConstantes.HAUTEURMIN);
        Log.d("Atelier04","VParametres :: onFinishInflate");
    }

    static{

        Log.d("Atelier04","VParametres :: static");

    }

}
