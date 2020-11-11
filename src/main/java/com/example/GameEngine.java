package com.example;

import java.util.*;
import java.util.stream.IntStream;

public class GameEngine {
    int[][] gameBoard = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    int player;
    int computer;
    int playerScore = 0;
    int computerScore = 0;
    boolean nextMove = true;

    public GameEngine(int player, int computer) {
        this.player = player;
        this.computer = computer;
    }

    public void isWinner(){
        if (getWinnerLines(player).containsValue(true)) {
            playerScore++;
            nextMove = false;
        }
        if (getWinnerLines(computer).containsValue(true)){
            computerScore++;
            nextMove = false;
        }
    }

    public Map<String, Boolean> getWinnerLines(int p){
        return getWinnerLines(p, gameBoard);
    }

    public Map<String, Boolean> getWinnerLines(int p, int[][] board){


        Map<String, Boolean> winnerLines = new HashMap<>();

        for(int i=0; i<3; i++){
            int x = i;

            boolean resultCol = IntStream.range(0,3)
                .map(n -> board[n][x])
                .allMatch(n -> n == p);

            winnerLines.put("lineCol" + i, resultCol);

            boolean resultRow = Arrays.stream(board[x])
                .allMatch(n -> n == p);

            winnerLines.put("lineRow" + i, resultRow);
        }

        boolean lineDiag0 = IntStream.range(0,3)
                .map(n -> board[n][n])
                .allMatch(n -> n == p);
        winnerLines.put("lineDiag0", lineDiag0);

        boolean lineDiag1 = IntStream.range(0,3)
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(n -> board[n][2-n])
                .allMatch(n -> n == p);
        winnerLines.put("lineDiag1", lineDiag1);

        return winnerLines;
    }

    public void restartGame() {
        gameBoard = new int[3][3];
        nextMove = true;
    }

    public String markPosition(int row, int col){
        String sign = "";
        if (gameBoard[row][col] == 1){
            sign = "O";
        } else if (gameBoard[row][col] == 2){
            sign = "X";
        }
        return sign;
    }

    public void computerMove(){
        boolean pickedField = false;
        Random rand = new Random();

        int[][] temporaryBoard = Arrays.stream(gameBoard).map(int[]::clone).toArray(int[][]::new);

        // check if the move can win
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {

                if (temporaryBoard[i][n] == 0 && !pickedField) {
                    temporaryBoard[i][n] = computer;
                    if (getWinnerLines(computer, temporaryBoard).containsValue(true)){
                        gameBoard[i][n] = computer;
                        pickedField = true;
                    } else {
                        temporaryBoard[i][n] = player;
                    }
                    if (getWinnerLines(player, temporaryBoard).containsValue(true)) {
                        gameBoard[i][n] = computer;
                        pickedField = true;
                    } else {
                        temporaryBoard[i][n] = 0;
                    }
                }
            }
        }

        // pick randomly if smart move impossible
        while (!pickedField){
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);

            if (gameBoard[row][col] == 0){
                gameBoard[row][col] = 2;

                pickedField = true;
            }

            // check if board is full
            IntStream stream = Arrays.stream(gameBoard).flatMapToInt(Arrays::stream);
            boolean isEmptyField = stream.anyMatch(x -> x == 0);
            if (!isEmptyField){
                pickedField = true;
            }
        }
        isWinner();
    }

    public void playerMove(int row, int col){

        if (nextMove && gameBoard[row][col] == 0){
            gameBoard[row][col]= 1;
            isWinner();
            if (nextMove){
                computerMove();
            }
        }
    }

    public int getPlayer() {
        return player;
    }

    public int getComputer() {
        return computer;
    }

    public String getScoreBoard(){
        return "Player [" + playerScore +"] : [" + computerScore + "] Computer ";
    }
}
