package com.example;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GameEngine {
    int[][] gameBoard = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    int player;
    int computer;
    boolean nextMove = true;

    public GameEngine(int player, int computer) {
        this.player = player;
        this.computer = computer;
    }

    public int checkLine(int[] line){

        boolean isPlayerWinner = Arrays.stream(line)
                .allMatch(n -> n == player);

        boolean isComputerWinner = Arrays.stream(line)
                .allMatch(n -> n == computer);

        if (isPlayerWinner){
            nextMove = false;
            return player;
        } else if (isComputerWinner) {
            nextMove = false;
            return computer;
        } else {
            return 0;
        }
    }

    public void getWinner(){
        int resultDiag0 = checkLine(getDiag0());
        int resultDiag1 = checkLine(getDiag1());
        int resultRow0 = checkLine(getRow0());
        int resultRow1 = checkLine(getRow1());
        int resultRow2 = checkLine(getRow2());
        int resultCol0 = checkLine(getCol0());
        int resultCol1 = checkLine(getCol1());
        int resultCol2 = checkLine(getCol2());

    }

    public void restartGame() {
        gameBoard = new int[3][3];
        nextMove = true;
    }

    public String getPosition(int row, int col){
        String sign = "";
        if (gameBoard[row][col] == 1){
            sign = "O";
        } else if (gameBoard[row][col] == 2){
            sign = "X";
        }

        return sign;
    }
    public void computerMove(){
        boolean picked = false;
        Random rand = new Random();



        while (!picked && nextMove){

            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            System.out.println(row + "" + col);
            if (gameBoard[row][col] == 0 && nextMove){
                gameBoard[row][col] = 2;
                picked = true;
            }

            IntStream stream = Arrays.stream(gameBoard).flatMapToInt(Arrays::stream);
            boolean contains = stream.anyMatch(x -> x == 0);
            if (!contains){
                picked = true;
            }
            System.out.println(contains);




        }
    }

    public void playerMove(int row, int col){

        if (nextMove && gameBoard[row][col] == 0){
            gameBoard[row][col]= 1;
            getWinner();
            if (nextMove){
                computerMove();
            }



        }
    }

    public int[] getRow0() {
        return gameBoard[0];
    }

    public int[] getRow1() {
        return gameBoard[1];
    }

    public int[] getRow2() {
        return gameBoard[2];
    }

    public int[] getCol0() {
        return new int[]{gameBoard[0][0], gameBoard[1][0], gameBoard[2][0]};
    }

    public int[] getCol1() {
        return new int[]{gameBoard[0][1], gameBoard[1][1], gameBoard[2][1]};
    }

    public int[] getCol2() {
        return new int[]{gameBoard[0][2], gameBoard[1][2], gameBoard[2][2]};
    }

    public int[] getDiag0() {
        return new int[]{gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]};
    }

    public int[] getDiag1() {
        return new int[]{gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]};
    }

    public void setNextMove(boolean nextMove) {
        this.nextMove = nextMove;
    }

    public static void main(String[] args) {

//        int[] row0 = gameBoard[0];
//        int[] row1 = gameBoard[1];
//        int[] row2 = gameBoard[2];
//        int[] col0 = {gameBoard[0][0], gameBoard[1][0], gameBoard[2][0]};
//        int[] col1 = {gameBoard[0][1], gameBoard[1][1], gameBoard[2][1]};
//        int[] col2 = {gameBoard[0][2], gameBoard[1][2], gameBoard[2][2]};
//        int[] diag0 = {gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]};
//        int[] diag1 = {gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]};

//        int[][] gameBoard = {
//                {2, 1, 2},
//                {1, 1, 1},
//                {0, 0, 0}
//        };

        //GameEngine game = new GameEngine(gameBoard,1,2);
        //int x = game.checkLine(game.getRow1());

       //System.out.println(x);
    }
}
