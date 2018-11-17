package ca.cours5b5.sebastienhamel.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import ca.cours5b5.sebastienhamel.controleurs.Action;
import ca.cours5b5.sebastienhamel.controleurs.interfaces.Fournisseur;
import ca.cours5b5.sebastienhamel.global.GCommande;

public class ProxyListe extends Proxy implements Fournisseur {

    private ChildEventListener childEventListener;

    private Query requete;

    private Action actionNouvelItem;

    private List<DatabaseReference> noeudsAjoutes;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);
    }

    public void setActionNouvelItem(GCommande commande){

        sd

    }

    public void ajouterValeur(Object valeur){



    }

    @Override
    public void connecterAuServeur() {
        super.connecterAuServeur();
        creerListener();

    }

    private void creerListener(){



    }

    protected Query getRequete(){

        return null;

    }

    @Override
    public void deconnecterDuServeur() {
        super.deconnecterDuServeur();
    }

    @Override
    public void detruireValeurs() {

    }
}
