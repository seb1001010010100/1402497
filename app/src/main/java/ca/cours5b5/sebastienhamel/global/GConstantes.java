package ca.cours5b5.sebastienhamel.global;

public final class GConstantes {

    private GConstantes(){}

    public static final int LARGEUR_MIN = 4;
    public static final int LARGEUR_MAX = 10;
    public static final int LARGEUR_PAR_DEFAUT = 7;

    public static final int HAUTEUR_MIN = 4;
    public static final int HAUTEUR_MAX = 10;
    public static final int HAUTEUR_PAR_DEFAUT = 6;

    public static final int POUR_GAGNER_MIN = 3;
    public static final int POUR_GAGNER_PAR_DEFAUT = 4;

    public static final String EXTENSION_PAR_DEFAUT=".json";

    public static final int CODE_CONNEXION = 123;

    public static final int NOMBRE_DE_VALEURS_A_CHANGER_DU_SERVEUR_PAR_DEFAUT = 10;
    public static final String CLE_ID_JOUEUR_HOTE = "idJoueurHote",
                            CLE_ID_JOUEUR_INVITE = "idJoueurInvite",
                            CLE_COUPS_JOUEUR_HOTE = "coupsJoueurHote",
                            CLE_COUPS_JOUEUR_INVITE = "coupsJoueurInvite",
                            //TODO: ramplacer les ids
                            FIXME_JSON_PARTIE_RESEAU = "{\"listeCoups\":[],\"parametres\":{\"largeur\":\"7\",\"pourGagner\":\"4\",\"hauteur\":\"6\"},\"idJoueurInvite\":\"PHMRKe3q7zOhfQQOwemjtfBFNp12\",\"idJoueurHote\":\"T1m8GxiBAlhLUcF6Ne0GV06nnEg1\"}";

}
