package ca.cours5b5.sebastienhamel.modeles;

import java.util.Map;

import ca.cours5b5.sebastienhamel.exceptions.ErreurSerialisation;


public abstract class Modele {

    public abstract void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation;

    public abstract Map<String, Object> enObjetJson();

}
