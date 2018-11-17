package ca.cours5b5.sebastienhamel.usagers;

import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {

    public static boolean siUsagerConnecte(){

        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            return true;

        }
        return false;
    }


    public static String getId(){

        if(siUsagerConnecte()){

           return FirebaseAuth.getInstance().getCurrentUser().getUid();

        }

        return "default";

    }

}
