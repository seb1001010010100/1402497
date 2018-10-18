package ca.cours5b5.sebastienhamel.controleurs;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.sebastienhamel.globale.GCommande;
import ca.cours5b5.sebastienhamel.modeles.Modele;

public class ControlleurAction {

    private static Map<GCommande, Action> actions;
    private static List<Action> fileAttenteExecution;

    static{

        actions = new HashMap<GCommande, Action>();
        for(GCommande commande : GCommande.values()){

            actions.put(commande, new Action());

        }
        fileAttenteExecution = new ArrayList<>();

    }

    public static Action demanderAction(GCommande commande){


        return actions.get(commande);

    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        enregistrerFournisseur(fournisseur,commande,listenerFournisseur);
        executerActionsExecutables();

    }

    static void executerDesQuePossible(Action action){

        Log.d("Atelier 7", "ControllerAction.executerDesQuePossible");
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();

    }

    private static void executerActionsExecutables(){

        Log.d("Atelier 7", "ControllerAction.executerActionsExecutables");
        for(Action action : fileAttenteExecution){

            if(siActionExecutable(action)){

                fileAttenteExecution.remove(action);
                executerMaintenant(action);
                lancerObservationSiPossible(action);



            }

        }

    }

    static boolean siActionExecutable(Action action){


        return action.listenerFournisseur != null ? (true) : (false);
    }

    private static synchronized void executerMaintenant(Action action){

        action.listenerFournisseur.executer(action.args);

    }

    private static void lancerObservationSiPossible(Action action){

        Log.d("Atelier 7", "ControllerAction.lancerObservationSiPossible");
        Fournisseur fournisseur = action.fournisseur;
        if(fournisseur instanceof Modele){

            ControlleurObservation.lancerObservation((Modele)fournisseur);

        }

    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        Action action = actions.get(commande);
        action.listenerFournisseur = listenerFournisseur;
        action.fournisseur = fournisseur;

    }

    private static void ajouterActionEnFileDAttente(Action action){

        fileAttenteExecution.add(action.cloner());

    }

}
