package ca.cours5b5.sebastienhamel.controleurs;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.sebastienhamel.modeles.MParametres;
import ca.cours5b5.sebastienhamel.modeles.MPartie;
import ca.cours5b5.sebastienhamel.modeles.Modele;

public class ControlleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    private static MPartie partie;

    static {

        observations = new HashMap<>();


    }

    public static void lancerObservation(Modele modele){

        Log.d("Atelier 7", "ControllerObservation.lancerObservation");
        ListenerObservateur listener = observations.get(modele);
        if(listener != null){

            listener.reagirNouveauModele(modele);
        }

    }

    public static void observerModele(String nomModele,
                                      final ListenerObservateur listenerObservateur){

        Log.d("atelier06","ControlleurObservation :: observerModele");
        if(nomModele.equals(MParametres.class.getSimpleName())){

            observations.put(MParametres.getInstance(), listenerObservateur);
            listenerObservateur.reagirNouveauModele(MParametres.getInstance());

        }else if(nomModele.equals(MPartie.class.getSimpleName())){
            partie = new MPartie(MParametres.getInstance().getMParametresPartie());
            observations.put(ControlleurObservation.partie, listenerObservateur);
            listenerObservateur.reagirNouveauModele(ControlleurObservation.partie);

        }


    }
}
