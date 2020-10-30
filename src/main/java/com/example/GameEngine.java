package com.example;

import java.util.Arrays;

public class GameEngine {

    int[][] gameBoard;

    public GameEngine(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int checkLine(int[] line){
        int player = 1;
        int computer = 2;

        boolean isPlayerWinner = Arrays.stream(line)
                .allMatch(n -> n == player);

        boolean isComputerWinner = Arrays.stream(line)
                .allMatch(n -> n == computer);

        if (isPlayerWinner){
            return player;
        } else if (isComputerWinner) {
            return computer;
        } else {
            return 0;
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
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

    public static void main(String[] args) {

//        int[] row0 = gameBoard[0];
//        int[] row1 = gameBoard[1];
//        int[] row2 = gameBoard[2];
//        int[] col0 = {gameBoard[0][0], gameBoard[1][0], gameBoard[2][0]};
//        int[] col1 = {gameBoard[0][1], gameBoard[1][1], gameBoard[2][1]};
//        int[] col2 = {gameBoard[0][2], gameBoard[1][2], gameBoard[2][2]};
//        int[] diag0 = {gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]};
//        int[] diag1 = {gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]};

        int[][] gameBoard = {
                {2, 1, 2},
                {1, 1, 1},
                {0, 0, 0}
        };

        GameEngine game = new GameEngine(gameBoard);
        int x = game.checkLine(game.getRow1());

        System.out.println(x);
    }
}
