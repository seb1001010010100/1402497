package ca.cours5b5.sebastienhamel.donnees;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public final class Serveur extends SourceDeDonnees {

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance(){

        return instance;

    }


    @Override
    public void chargerModele(String cheminSauvegarde, final ListenerChargement listenerChargement) {

        if(verifyNomModele(cheminSauvegarde)){

            DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
            noeud.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Map<String, Object> objetJson = (Map<String, Object>) dataSnapshot.getValue();
                    listenerChargement.reagirSucces(objetJson);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    listenerChargement.reagirErreur(new RuntimeException());

                }
            });


        }else{

            listenerChargement.reagirErreur(new RuntimeException());

        }

    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {


        DatabaseReference user = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        user.setValue(objetJson);

    }

    public void detruireSauvegarde(String cheminSauvegarde){

        DatabaseReference user = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        user.removeValue();

    }

}
