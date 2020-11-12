package com.example;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Map;

public class TicTacToe extends Application {

    GameEngine game;

    Text title = new Text();
    Rectangle[] fields = new Rectangle[10];
    Text[] moves = new Text[10];
    Button buttonNewGame = new Button();
    Text scoreBoard = new Text();

    BorderPane mainGrid = new BorderPane();
    GridPane gridPaneFields = new GridPane();
    StackPane StackPaneCenter = new StackPane();
    Group winLines = new Group();
    HBox hBoxBottom = new HBox();

    public void drawGame(){
        createAllGrids();

        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setFill(Color.rgb(130, 197, 233));
        title.setText("Tic-Tac-Toe Game");

        drawClickableFields();

        buttonNewGame.setText("New Game");
        buttonNewGame.setPrefSize(100, 50);
        buttonNewGame.setOnAction((e) -> {
            winLines.getChildren().clear();
            game.restartGame();
            drawMove();
        });

        scoreBoard.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        scoreBoard.setFill(Color.rgb(130, 197, 233));
        scoreBoard.setY(50);
        scoreBoard.setText(game.getScoreBoard());
    }

    public void createAllGrids(){
        mainGrid.setStyle("-fx-background-color: #006594");
        mainGrid.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainGrid.setCenter(StackPaneCenter);
        mainGrid.setBottom(hBoxBottom);
        BorderPane.setAlignment(hBoxBottom, Pos.TOP_CENTER);

        StackPaneCenter.getChildren().addAll(gridPaneFields, winLines);

        gridPaneFields.setMinSize(400, 200);
        gridPaneFields.setPadding(new Insets(75, 10, 50, 10));
        gridPaneFields.setVgap(5);
        gridPaneFields.setHgap(15);
        gridPaneFields.setAlignment(Pos.CENTER);

        hBoxBottom.getChildren().addAll(buttonNewGame, scoreBoard);
        hBoxBottom.setAlignment(Pos.CENTER);
        HBox.setMargin(buttonNewGame,new Insets(20, 70, 40, 20));
        HBox.setMargin(scoreBoard,new Insets(20, 20, 40, 20));
    }

    public void drawClickableFields(){
        int x=0;
        for (int i = 0; i < 3; i++){
            for (int n=0; n<3; n++){
                fields[x] = new Rectangle(200, 200);
                fields[x].setFill(Color.rgb(36, 123, 160));
                int a = i;
                int b = n;
                fields[x].setOnMouseClicked(t -> {
                    game.userMove(a,b);
                    drawMove();
                    drawWinLine();
                });
                gridPaneFields.add(fields[x],n,i);

                moves[x] = new Text();
                moves[x].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
                moves[x].setFill(Color.rgb(130, 197, 233));
                moves[x].setText(positionToString(n,i));
                gridPaneFields.add(moves[x],n,i);
                GridPane.setHalignment(moves[x], HPos.CENTER);

                x++;
            }
        }
    }

    public String positionToString(int row, int col){
        String sign = Character.toString(game.getGameBoard()[row][col]);
        return sign.toUpperCase();
    }

    public void drawMove(){
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                moves[x].setText(positionToString(i, n));
                x ++;
            }
        }
        scoreBoard.setText(game.getScoreBoard());
    }

    public void drawWinLine(){
        Map<String, Boolean> winLine = game.getWinLines();

        Line winnerLine = new Line();

        if (winLine.get("lineDiag0")){
            winnerLine = new Line(30, 30, 500, 500);
        }
        if (winLine.get("lineDiag1")){
            winnerLine = new Line(30, 500, 500, 30);
        }
        if (winLine.get("lineCol0")){
            winnerLine = new Line(-450, 0, -450, 500);
        }
        if (winLine.get("lineCol1")){
            winnerLine = new Line(0, 0, 0, 500);
        }
        if (winLine.get("lineCol2")){
            winnerLine = new Line(450, 0, 450, 500);
        }
        if (winLine.get("lineRow0")){
            winnerLine = new Line(-250, -400, 250, -400);
        }
        if (winLine.get("lineRow1")){
            winnerLine = new Line(-250, 0, 250, 0);
        }
        if (winLine.get("lineRow2")){
            winnerLine = new Line(-250, 500, 250, 500);
        }

        if (winLine.containsValue(true)){
            winnerLine.setStroke(Color.rgb(27, 152, 224));
            winnerLine.setStrokeWidth(30);
        }

        winLines.getChildren().addAll(winnerLine);
    }

    @Override
    public void start(Stage primaryStage){
        game = new GameEngine('o', 'x');

        drawGame();

        Scene scene = new Scene(mainGrid, 800, 800, Color.rgb(0, 100, 148));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}