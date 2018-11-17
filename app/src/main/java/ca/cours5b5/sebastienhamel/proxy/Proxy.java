package ca.cours5b5.sebastienhamel.proxy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Proxy {

    private String cheminServeur;
    protected DatabaseReference noeudServeur = FirebaseDatabase.getInstance().getReference(cheminServeur);

    public Proxy(String cheminServeur){

        this.cheminServeur = cheminServeur;

    }

    public void connecterAuServeur(){

        noeudServeur.orderByValue().limitToLast(10);

    }

    public void deconnecterDuServeur(){

        noeudServeur.

    }

    public abstract void detruireValeurs();

}
