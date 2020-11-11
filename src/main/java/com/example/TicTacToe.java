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

    GameEngine game = new GameEngine(1, 2);

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

    public void drawGame(){
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

    public void drawClickableFields(){
        int x=0;
        for (int i = 0; i < 3; i++){
            for (int n=0; n<3; n++){
                fields[x] = new Rectangle(200, 200);
                fields[x].setFill(Color.rgb(36, 123, 160));
                int a = i;
                int b = n;
                fields[x].setOnMouseClicked(t -> {
                    game.playerMove(a,b);
                    drawMove();
                    drawWinnerLine();
                });
                gridPaneFields.add(fields[x],n,i);

                moves[x] = new Text();
                moves[x].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
                moves[x].setFill(Color.rgb(130, 197, 233));
                moves[x].setText(game.markPosition(n,i));
                gridPaneFields.add(moves[x],n,i);
                GridPane.setHalignment(moves[x], HPos.CENTER);

                x++;
            }
        }
    }

    public void drawMove(){
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                moves[x].setText(game.markPosition(i, n));
                x ++;
            }
        }
        scoreBoard.setText(game.getScoreBoard());
    }

    public void drawWinnerLine(){
        Map<String,Boolean> playerLine = game.getWinnerLines(game.getPlayer());
        Map<String,Boolean> computerLine = game.getWinnerLines(game.getComputer());

        Line lineDiag0 = new Line();
        Line lineDiag1 = new Line();
        Line lineCol0 = new Line();
        Line lineCol1 = new Line();
        Line lineCol2 = new Line();
        Line lineRow0 = new Line();
        Line lineRow1 = new Line();
        Line lineRow2 = new Line();

        if (playerLine.get("lineDiag0") || computerLine.get("lineDiag0")){
            lineDiag0 = new Line(30, 30, 500, 500);
            lineDiag0.setStroke(Color.rgb(27, 152, 224));
            lineDiag0.setStrokeWidth(30);
        }
        if (playerLine.get("lineDiag1") || computerLine.get("lineDiag1")) {
            lineDiag1 = new Line(30, 500, 500, 30);
            lineDiag1.setStroke(Color.rgb(27, 152, 224));
            lineDiag1.setStrokeWidth(30);
        }
        if (playerLine.get("lineCol0") || computerLine.get("lineCol0")) {
            lineCol0 = new Line(-450, 0, -450, 500);
            lineCol0.setStroke(Color.rgb(27, 152, 224));
            lineCol0.setStrokeWidth(30);
        }
        if (playerLine.get("lineCol1") || computerLine.get("lineCol1")) {
            lineCol1 = new Line(0, 0, 0, 500);
            lineCol1.setStroke(Color.rgb(27, 152, 224));
            lineCol1.setStrokeWidth(30);
        }
        if (playerLine.get("lineCol2") || computerLine.get("lineCol2")) {
            lineCol2 = new Line(450, 0, 450, 500);
            lineCol2.setStroke(Color.rgb(27, 152, 224));
            lineCol2.setStrokeWidth(30);
        }
        if (playerLine.get("lineRow0") || computerLine.get("lineRow0")) {
            lineRow0 = new Line(-250, -400, 250, -400);
            lineRow0.setStroke(Color.rgb(27, 152, 224));
            lineRow0.setStrokeWidth(30);
        }
        if (playerLine.get("lineRow1") || computerLine.get("lineRow1")) {
            lineRow1 = new Line(-250, 0, 250, 0);
            lineRow1.setStroke(Color.rgb(27, 152, 224));
            lineRow1.setStrokeWidth(30);
        }
        if (playerLine.get("lineRow2") || computerLine.get("lineRow2")) {
            lineRow2 = new Line(-250, 500, 250, 500);
            lineRow2.setStroke(Color.rgb(27, 152, 224));
            lineRow2.setStrokeWidth(30);
        }

        winLines.getChildren().addAll(lineDiag0, lineDiag1,
                lineCol0, lineCol1, lineCol2,
                lineRow0, lineRow1, lineRow2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        createAllGrids();
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