package ca.cours5b5.sebastienhamel.controleurs;

import ca.cours5b5.sebastienhamel.proxy.ProxyListe;

public final class ControlleurPartieReseau {

    private static final ControlleurPartieReseau instance = new ControlleurPartieReseau();
    public static ControlleurPartieReseau getInstance(){

        return instance;

    }

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    private void connecterAuServeur(String indJoueurHote){



    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite){



    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite){



    }

    public void deconnecterDuServeur(){



    }

    public void transmettreCoup(Integer idColonne){



    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote){


        return null;
    }

    private String getCheminCoupsJoueurHote(String idJoueurHote){


        return null;
    }

    private String getCheminPartie(String idJoueurHote){


        return null;
    }

    public void detruireSauvegardeServeur(){


    }
}
