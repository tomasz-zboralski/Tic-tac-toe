package com.example;

import java.util.*;
import java.util.stream.IntStream;

public class GameEngine {

    private final char USER;
    private final char COMPUTER;
    private int userScore = 0;
    private int computerScore = 0;
    private boolean nextMove = true;
    private char[][] gameBoard = new char[3][3];

    public GameEngine(char user, char computer) {
        this.USER = user;
        this.COMPUTER = computer;
    }

    public void checkWinner(){
        if (getPlayerWinnerLines(USER).containsValue(true)) {
            userScore++;
            nextMove = false;
        } else if (getPlayerWinnerLines(COMPUTER).containsValue(true)){
            computerScore++;
            nextMove = false;
        } else {
            nextMove = true;
        }
    }

    public Map<String, Boolean> getPlayerWinnerLines(char player){
        return getPlayerWinnerLines(player, gameBoard);
    }

    public Map<String, Boolean> getPlayerWinnerLines(char player, char[][] board){
        Map<String, Boolean> winnerLines = new HashMap<>();

        for(int i=0; i<3; i++){
            int x = i;

            boolean resultCol = IntStream.range(0,3)
                .map(n -> board[n][x])
                .allMatch(n -> n == player);

            winnerLines.put("lineCol" + i, resultCol);

            boolean resultRow = IntStream.range(0,3)
                    .map(n -> board[x][n])
                    .allMatch(n -> n == player);

            winnerLines.put("lineRow" + i, resultRow);
        }

        boolean lineDiag0 = IntStream.range(0,3)
                .map(n -> board[n][n])
                .allMatch(n -> n == player);
        winnerLines.put("lineDiag0", lineDiag0);

        boolean lineDiag1 = IntStream.range(0,3)
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(n -> board[n][2-n])
                .allMatch(n -> n == player);
        winnerLines.put("lineDiag1", lineDiag1);

        return winnerLines;
    }

    public Map<String, Boolean> getWinLines(){
        Map<String,Boolean> userLine = getPlayerWinnerLines(USER);
        Map<String,Boolean> computerLine = getPlayerWinnerLines(COMPUTER);

        Map<String, Boolean> winnerLinesMerged = new HashMap<>(userLine);
        computerLine.forEach((key, value) -> winnerLinesMerged
                        .merge(key, value, (oldValue, newValue) -> oldValue || newValue));

        return winnerLinesMerged;
    }

    public void restartGame() {
        gameBoard = new char[3][3];
        nextMove = true;
    }

    public void smartComputerMove(){
        char[][] temporaryBoard = Arrays.stream(gameBoard).map(char[]::clone).toArray(char[][]::new);

        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                if (temporaryBoard[i][n] == 0 && nextMove) {
                    temporaryBoard[i][n] = COMPUTER;
                    if (getPlayerWinnerLines(COMPUTER, temporaryBoard).containsValue(true)){
                        gameBoard[i][n] = COMPUTER;
                        nextMove = false;
                    } else {
                        temporaryBoard[i][n] = USER;
                    }
                    if (getPlayerWinnerLines(USER, temporaryBoard).containsValue(true)) {
                        gameBoard[i][n] = COMPUTER;
                        nextMove = false;
                    } else {
                        temporaryBoard[i][n] = 0;
                    }
                }
            }
        }
    }

    public void computerMove(){
        smartComputerMove();
        // pick a field randomly if smart move impossible
        Random rand = new Random();
        while (nextMove){
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);

            if (gameBoard[row][col] == 0){
                gameBoard[row][col] = COMPUTER;
                nextMove = false;
            }
            if (!isEmptyFieldOnTheBoard()){
                nextMove = false;
            }
        }
        checkWinner();
    }

    public boolean isEmptyFieldOnTheBoard() {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard[i][j] == 0) return true;
            }
        }
        return false;
    }

    public void userMove(int row, int col){
        if (nextMove && gameBoard[row][col] == 0){
            gameBoard[row][col] = USER;
            checkWinner();
            if (nextMove){
                computerMove();
            }
        }
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public String getScoreBoard(){
        return "User [" + userScore +"] : [" + computerScore + "] Computer ";
    }
}