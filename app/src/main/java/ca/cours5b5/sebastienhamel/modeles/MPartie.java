package ca.cours5b5.sebastienhamel.modeles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

public class MPartie extends Modele{

    public @Retention(RetentionPolicy.RUNTIME) @interface AttributSerialisable{


    }

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    public MPartie(MParametresPartie parametres){

        this.parametres = parametres;

    }

    public MParametresPartie getParametres() {
        return parametres;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {

    }

    @Override
    public Map<String, Object> enObjetJson() {
        return null;
    }
}
