package ca.cours5b5.sebastienhamel.donnees;

import java.util.Map;


public abstract class SourceDeDonnees {

    public abstract void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement);

    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);

    protected String getNomModele(String cheminSauvegarde){

        if(verifyNomModele(cheminSauvegarde)){

            return cheminSauvegarde.split("/")[0];

        }

        return null;
    }

    public boolean verifyNomModele(String cheminSauvegarde){

        String pattern = "[a-zA-Z0-9]+[\\//].+";
        if(cheminSauvegarde.matches(pattern)){

            return true;

        }
        return false;

    }

}
