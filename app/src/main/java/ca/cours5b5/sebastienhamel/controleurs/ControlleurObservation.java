package ca.cours5b5.sebastienhamel.controleurs;

import java.util.Map;

import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.sebastienhamel.modeles.MParametres;
import ca.cours5b5.sebastienhamel.modeles.MPartie;
import ca.cours5b5.sebastienhamel.modeles.Modele;

public class ControlleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    private static MPartie partie;

    static {


    }

    public static void observerModele(String nomModele,
                                      final ListenerObservateur listenerObservateur){

        Modele modele;
        switch(nomModele){

            case "MPartie":
                observations.put(ControlleurObservation.partie, listenerObservateur);
                listenerObservateur.reagirNouveauModele(ControlleurObservation.partie);
                break;

            case "MParametres":
                observations.put(MParametres.instance, listenerObservateur);
                listenerObservateur.reagirNouveauModele(MParametres.instance);
                break;

        }


    }
}
