package ca.cours5b5.sebastienhamel.modeles;

import java.util.List;
import java.util.Map;

import ca.cours5b5.sebastienhamel.globale.GCouleur;

public class MColonne extends Modele {

    private List<GCouleur> jetons;
    public MColonne(){
        FIX ME
        //this.jetons = jetons;

    }

    public List<GCouleur> getJetons() {
        return jetons;
    }

    public void setJetons(List<GCouleur> jetons) {
        this.jetons = jetons;
    }

    public void placerJeton(GCouleur couleur){

        this.jetons.add(couleur);
        FIX ME

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {

    }

    @Override
    public Map<String, Object> enObjetJson() {
        return null;
    }
}
