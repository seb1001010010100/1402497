package ca.cours5b5.sebastienhamel.donnees;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;


import ca.cours5b5.sebastienhamel.serialisation.Jsonification;

public class SauvegardeTemporaire extends SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle){
        this.bundle = bundle;
    }


    public Map<String, Object> chargerModele(String cheminSauvegarde) {

        if(bundle != null && bundle.containsKey(getCle(cheminSauvegarde))){

            String json = bundle.getString(getCle(cheminSauvegarde));

            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            return objetJson;

        }else{

            return null;

        }
    }

    @Override
    public void chargerModele(String cheminSauvegarde, ListenerChargement listenerChargement) {

        if(bundle != null && bundle.containsKey(getCle(cheminSauvegarde))){

            String json = bundle.getString(getCle(cheminSauvegarde));

            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            listenerChargement.reagirSucces(objetJson);


        }else{

            listenerChargement.reagirErreur(new RuntimeException());

        }
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        if(bundle != null){

            String json = Jsonification.enChaineJson(objetJson);
            bundle.putString(getCle(cheminSauvegarde), json);

        }
    }

    private String getCle(String cheminSauvegarde){

        return getNomModele(cheminSauvegarde);

    }

}
