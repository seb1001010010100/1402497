package ca.cours5b5.sebastienhamel.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.cours5b5.sebastienhamel.controleurs.ControleurPartie;
import ca.cours5b5.sebastienhamel.controleurs.ControllerAI;
import ca.cours5b5.sebastienhamel.global.GConstantes;
import ca.cours5b5.sebastienhamel.global.GCouleur;


public class MPartieAI extends MPartie {

    private GCouleur humainColor = GCouleur.ROUGE;
    private int[][] completeGrid;

    public MPartieAI(MParametresPartie parametres) {
        super(parametres);
        initiateCompleteGrid();
    }

    @Override
    protected void jouerCoup(int colonne) {
        if(isHumainTurn()){

            super.jouerCoup(colonne);

        }

    }

    @Override
    protected void jouerCoupLegal(int colonne) {

        listeCoups.add(colonne);

        if(isHumainTurn()){

            addToCompleteList(colonne, GConstantes.INDICE_HUMAIN);

        }else{

            addToCompleteList(colonne, GConstantes.INDICE_AI);

        }

        getGrille().placerJeton(colonne, getCouleurCourante());

        if (getGrille().siCouleurGagne(getCouleurCourante(), parametres.getPourGagner())) {

            ControleurPartie.getInstance().gagnerPartie(getCouleurCourante());

        } else {

            prochaineCouleurCourante();
            if(!isHumainTurn()){

                AITurn();

            }

        }

    }

    private void jouerCoupAI(int colonne){

        super.jouerCoup(colonne);

    }

    private void AITurn(){


        jouerCoupAI(ControllerAI.getInstance().jouerCoup(completeGrid,getParametres().pourGagner));

    }



    private boolean isHumainTurn(){

        return (getCouleurCourante() == humainColor) ? true : false;

    }

    private void addToCompleteList(int coup, int indiceJoueur){


            for(int row = 0; row < completeGrid.length; row++ ){


                if(completeGrid[row][coup] == 0){
                    completeGrid[row][coup] = indiceJoueur;

                    break;

                }

            }

    }



    private void initiateCompleteGrid(){

        completeGrid = new int[getParametres().hauteur][getParametres().largeur];

        for(int row = 0; row < completeGrid.length; row++ ){

            for(int column = 0; column < completeGrid[row].length; column++){


                completeGrid[row][column] = 0;


            }

        }

    }

    public int[][] getCompleteGrid() {
        return completeGrid;
    }
}
