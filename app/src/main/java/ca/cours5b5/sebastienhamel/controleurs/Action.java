package ca.cours5b5.sebastienhamel.controleurs;

import android.util.Log;

import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerFournisseur;

public class Action {

    Fournisseur fournisseur;
    ListenerFournisseur listenerFournisseur;

    Object[] args;

    public void setArgs(Object... args) {
        this.args = args;
    }

    public void executerDesQuePossible(){

        Log.d("Atelier 7", "Action.executerDesQuePossible");
        ControlleurAction.executerDesQuePossible(this);

    }

    Action cloner(){

        Action action = new Action();
        action.fournisseur = this.fournisseur;
        action.listenerFournisseur = this.listenerFournisseur;
        if(args != null){

            action.setArgs(args);

        }
        return action;

    }

}

