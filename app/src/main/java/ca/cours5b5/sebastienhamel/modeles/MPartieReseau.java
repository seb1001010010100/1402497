package ca.cours5b5.sebastienhamel.modeles;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.sebastienhamel.controleurs.ControleurAction;
import ca.cours5b5.sebastienhamel.controleurs.ControlleurPartieReseau;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.sebastienhamel.exceptions.ErreurAction;
import ca.cours5b5.sebastienhamel.exceptions.ErreurSerialisation;
import ca.cours5b5.sebastienhamel.global.GCommande;
import ca.cours5b5.sebastienhamel.global.GConstantes;
import ca.cours5b5.sebastienhamel.serialisation.AttributSerialisable;
import ca.cours5b5.sebastienhamel.usagers.UsagerCourant;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private final String __idJoueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote;
    private final String __idJoueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;


    public MPartieReseau(MParametresPartie parametres) {

        super(parametres);
        fournirActionRecevoirCoup();
    }

    @Override
    public String getId() {
        return idJoueurHote;
    }

    private void fournirActionRecevoirCoup(){

        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{

                            recevoirCoupReseau((int)args[0]);


                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }
                });

    }

    @Override
    protected void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this,
                GCommande.JOUER_COUP_ICI,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{

                            int colonne = (Integer) args[0];

                            jouerCoup(colonne);

                            ControlleurPartieReseau.getInstance().transmettreCoup(colonne);



                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }
                });
    }

    private void recevoirCoupReseau(int colonne){

        jouerCoup(colonne);

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        super.aPartirObjetJson(objetJson);
        for(Map.Entry<String, Object> entry : objetJson.entrySet()){

            String chaineValeur = (String) entry.getValue();

            switch (entry.getKey()){

                case __idJoueurInvite:

                    idJoueurInvite = chaineValeur;
                    break;

                case __idJoueurHote:

                    idJoueurHote = chaineValeur;
                    break;

                default:

                    throw new ErreurSerialisation("Attribut inconnu: " + entry.getKey());
            }

        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__idJoueurInvite, idJoueurInvite);
        objetJson.put(__idJoueurHote, idJoueurHote);
        super.enObjetJson();
        return objetJson;

    }
}
