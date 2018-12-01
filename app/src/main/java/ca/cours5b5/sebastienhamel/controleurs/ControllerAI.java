package ca.cours5b5.sebastienhamel.controleurs;

import android.util.Log;

import java.util.Random;

import ca.cours5b5.sebastienhamel.global.GConstantes;
import ca.cours5b5.sebastienhamel.modeles.MPartie;

public final class ControllerAI {

    private static final ControllerAI instance = new ControllerAI();

    public static ControllerAI getInstance() {
        return instance;
    }

    private int[][] grid;

    int numberToWin;


    public int jouerCoup(int[][] grid, int numberToWin){

        this.grid = grid;
        this.numberToWin = numberToWin;
        int bestMove = evaluateBestMoves();

        return bestMove;

    }

    private int evaluateBestMoves(){

        int[] movesCoordinates = findMovesCoordinates();
        int[] laneScores = evaluateLanes(movesCoordinates);
        int bestScore = 0;
        int bestMove = 0;
        for(int i = 0; i < laneScores.length; i++){

            if(laneScores[i] > bestScore){

                bestScore = laneScores[i];
                bestMove = i;

            }if(laneScores[i] == bestScore){
                Random r = new Random();
                int randomChoice = r.nextBoolean() ? laneScores[i] : bestScore;
                if(laneScores[i] == randomChoice){

                    bestScore = laneScores[i];
                    bestMove = i;

                }

            }

        }

        return bestMove;
    }

    private int[] findMovesCoordinates(){

        int[] movesCoordinates = new int[grid[0].length];

        for(int column = 0; column < grid[0].length; column++){

            for(int row = 0; row < grid.length; row++){

                if(grid[row][column] == GConstantes.INDICE_NO_ONE){

                    movesCoordinates[column] = row;
                    //Log.d("test", "["+row+"]" + "[" + column + "]" + " = " + movesCoordinates[column]  );
                    break;

                }if(row == grid.length - 1 && grid[row][column] !=GConstantes.INDICE_NO_ONE){

                    movesCoordinates[column] = -1;
                    //Log.d("test", "["+row+"]" + "[" + column + "]" + " = " + movesCoordinates[column]  );
                    break;

                }


            }

        }

        return movesCoordinates;

    }

    private int[] evaluateLanes(int[] movesCoordinates){

        int[] lanesScores = new int[movesCoordinates.length];

        for(int column = 0; column < movesCoordinates.length; column++){
            
            if(movesCoordinates[column] != -1){

                lanesScores[column] = evaluateLane(movesCoordinates[column], column);

            }


        }

        return lanesScores;

    }

    private int evaluateLane(int row, int column){

        int score = 0;
        score += evaluateVertical(row, column);
        score += evaluateHorizontal(row, column);
        score += evaluateDiagonalLeft(row, column);
        score += evaluateDiagonalRight(row, column);

        return score;

    }

    private int[] lookLeft(int row, int column,int numberOfTime, int player){

        int[] result = {numberOfTime, player};

        if(column >= 0){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){


                    return lookLeft(row, column - 1, 1, grid[row][column]);


                }else if(player == grid[row][column]){

                    return lookLeft(row, column - 1, (numberOfTime + 1), player);

                }

            }

        }

        return result;

    }

    private int[] lookRight(int row, int column,int numberOfTime, int player){

        int[] result = {numberOfTime, player};

        if(column <= (grid[row].length - 1)){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){

                    return lookRight(row, column + 1, 1, grid[row][column]);

                }else if(player == grid[row][column]){

                    return lookRight(row, column + 1, numberOfTime + 1, player);

                }

            }

        }

        return result;

    }

    private int evaluateHorizontal(int row, int column){

        int score = 0;

        int[] resultLeft = lookLeft(row, column - 1, 0, GConstantes.INDICE_NO_ONE);
        int[] resultRight = lookRight(row, column + 1, 0, GConstantes.INDICE_NO_ONE);

        //same user
        if(resultLeft[1] == resultRight[1]){

            score += evaluate(resultLeft[0] + resultRight[0], resultLeft[1]);

        }else{

            score += evaluate(resultLeft[0], resultLeft[1]);
            score += evaluate(resultRight[0], resultRight[1]);
        }

        return score;
    }

    private int[] lookDown(int row, int column, int numberOfTime, int player){

        int[] result = {numberOfTime, player};

        if(row >= 0){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){

                    return lookDown(row - 1, column, 1, grid[row][column]);

                }else if(player == grid[row][column]){

                    return lookDown(row - 1, column, numberOfTime + 1, player);

                }

            }

        }

        return result;

    }

    private int evaluateVertical(int row, int column){

        int[] resultDown = lookDown(row - 1, column, 0, GConstantes.INDICE_NO_ONE);

        return evaluate(resultDown[0], resultDown[1]);

    }

    private int[] lookDiagonalTopRight(int row, int column, int numberOfTime, int player){

        int[] result = { numberOfTime, player };

        if(row <= (grid.length - 1) && column <= (grid[row].length - 1)){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){

                    return lookDiagonalTopRight(row + 1, column + 1, 1, grid[row][column]);

                }else if(grid[row][column] == player){

                    return lookDiagonalTopRight(row + 1, column + 1, numberOfTime + 1, player);

                }

            }

        }

        return result;

    }

    private int[] lookDiagonalBottomLeft(int row, int column, int numberOfTime, int player){

        int[] result = { numberOfTime, player };

        if(row >= 0 && column >= 0){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){

                    return lookDiagonalBottomLeft(row - 1, column - 1, 1, grid[row][column]);

                }else if(grid[row][column] == player){

                    return lookDiagonalBottomLeft(row - 1, column - 1, numberOfTime + 1, player);

                }

            }

        }

        return result;

    }

    private int evaluateDiagonalRight(int row, int column){

        int score = 0;
        int[] resultDiagonalTopRight = lookDiagonalTopRight(row + 1, column + 1, 0, GConstantes.INDICE_NO_ONE);
        int[] resultDiagonalBottomLeft = lookDiagonalBottomLeft(row - 1, column - 1, 0, GConstantes.INDICE_NO_ONE);

        if(resultDiagonalBottomLeft[1] == resultDiagonalTopRight[1]){

            score += evaluate(resultDiagonalTopRight[0] + resultDiagonalBottomLeft[0], resultDiagonalTopRight[1]);


        }else{

            score += evaluate(resultDiagonalTopRight[0], resultDiagonalTopRight[1]);
            score += evaluate(resultDiagonalBottomLeft[0], resultDiagonalBottomLeft[1]);

        }

        return score;
    }

    private int[] lookDiagonalTopLeft(int row, int column, int numberOfTime, int player){

        int[] result = {numberOfTime, player};

        if(row <= grid.length - 1 && column >= 0){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){

                    return lookDiagonalTopLeft(row + 1, column - 1, 1, grid[row][column]);

                }else if(grid[row][column] == player){

                    return lookDiagonalTopLeft(row + 1, column - 1, numberOfTime + 1, player);

                }

            }

        }

        return result;

    }

    private int[] lookDiagonalBottomRight(int row, int column, int numberOfTime, int player){

        int[] result = { numberOfTime, player };

        if(row >= 0 && column <= grid[row].length - 1){

            if(grid[row][column] != GConstantes.INDICE_NO_ONE){

                if(numberOfTime == 0){

                    return lookDiagonalBottomRight(row - 1, column + 1, 1, grid[row][column] );

                }else if(grid[row][column] == player){

                    return lookDiagonalBottomRight(row - 1, column + 1, numberOfTime + 1, player );

                }

            }

        }

        return result;

    }

    private int evaluateDiagonalLeft(int row, int column){

        int score = 0;
        int[] resultDiagonalTopLeft = lookDiagonalTopLeft(row + 1, column - 1, 0, GConstantes.INDICE_NO_ONE);
        int[] resultDiagonalBottomRight = lookDiagonalBottomRight(row - 1, column + 1, 0, GConstantes.INDICE_NO_ONE);

        if(resultDiagonalTopLeft[1] == resultDiagonalBottomRight[1]){

            score += evaluate(resultDiagonalBottomRight[0] + resultDiagonalTopLeft[0], resultDiagonalBottomRight[1]);

        }else{

            score += evaluate(resultDiagonalBottomRight[0], resultDiagonalBottomRight[1]);
            score += evaluate(resultDiagonalTopLeft[0], resultDiagonalTopLeft[1]);

        }

        return score;

    }

    private int evaluate(int numberOfTime,int player){

        int score = 0;

        if(player == GConstantes.INDICE_NO_ONE){

            score += GConstantes.SCORE_RIEN;

        }else if(numberOfTime == 1){

            score += GConstantes.SCORE_FAIBLE;

        }else if(numberToWin == 4 && (numberToWin - numberOfTime) == 2){

            score += GConstantes.SCORE_ELEVE;

        }else if((numberToWin - numberOfTime) <= 1){

            if(player == GConstantes.INDICE_HUMAIN){

                score += GConstantes.SCORE_PERTE;

            }else if(player == GConstantes.INDICE_AI){

                score += GConstantes.SCORE_GAIN;

            }

        }else{

            score += GConstantes.SCORE_FAIBLE;

        }

        return score;
    }

}
