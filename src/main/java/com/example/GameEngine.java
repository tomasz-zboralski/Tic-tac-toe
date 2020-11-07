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

        Map<String, Boolean> winnerLines = new HashMap<>();

        for(int i=0; i<3; i++){
            int x = i;

            boolean resultCol = IntStream.range(0,3)
                .map(n -> gameBoard[n][x])
                .allMatch(n -> n == p);

            winnerLines.put("lineCol" + i, resultCol);

            boolean resultRow = Arrays.stream(gameBoard[x])
                .allMatch(n -> n == p);

            winnerLines.put("lineRow" + i, resultRow);
        }

        boolean lineDiag0 = IntStream.range(0,3)
                .map(n -> gameBoard[n][n])
                .allMatch(n -> n == p);
        winnerLines.put("lineDiag0", lineDiag0);

        boolean lineDiag1 = IntStream.range(0,3)
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(n -> gameBoard[n][2-n])
                .allMatch(n -> n == p);
        winnerLines.put("lineDiag1", lineDiag1);

        return winnerLines;
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
        boolean pickedField = false;
        Random rand = new Random();



        while (!pickedField && nextMove){

            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            System.out.println(row + "" + col);
            if (gameBoard[row][col] == 0){
                gameBoard[row][col] = 2;
                pickedField = true;
            }

            IntStream stream = Arrays.stream(gameBoard).flatMapToInt(Arrays::stream);
            boolean isEmptyField = stream.anyMatch(x -> x == 0);
            if (!isEmptyField){
                pickedField = true;
            }
        }
    }

    public void playerMove(int row, int col){

        if (nextMove && gameBoard[row][col] == 0){
            gameBoard[row][col]= 1;
            isWinner();
            if (nextMove){
                computerMove();
                isWinner();
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

//    public static void main(String[] args) {
//        Map<String, Boolean> winnerLines = Map.of("diag0", false, "diag1",false);
//        winnerLines.forEach((key, value) -> System.out.println(key + ":" + value));
//
//        int[][] board = {
//                {0, 0, 1},
//                {0, 1, 0},
//                {1, 0, 1}
//        };
//
//
//        boolean lineDiag0 = IntStream.range(0,3) // iterate(3, i -> i - 1) //
//                //.limit(3)
//                .boxed() // Converts Intstream to Stream<Integer>
//                .sorted(Collections.reverseOrder())
//
//                .map(n -> board[n][2-n])
//                .allMatch(n -> n == 1 && n==2);
//        System.out.println(lineDiag0);

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


//    }
}
