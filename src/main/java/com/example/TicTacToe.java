package com.example;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe extends Application {

    private GameEngine game = new GameEngine('o', 'x');

    private final Text TITLE = new Text();
    private final Rectangle[] FIELDS = new Rectangle[10];
    private final Text[] MOVES = new Text[10];
    private final Button BUTTON_NEXT_ROUND = new Button("Next Round");
    private final Button BUTTON_NEW_GAME = new Button("New Game");
    private final Text SCORE_BOARD = new Text();
    private final RadioButton LEVEL_BUTTON_EASY = new RadioButton("Easy");
    private final RadioButton LEVEL_BUTTON_HARD = new RadioButton("Hard");
    private final TextField USER_NAME_TEXT_FIELD = new TextField("User");
    private String userName = USER_NAME_TEXT_FIELD.getText();

    private final BorderPane MAIN_GRID = new BorderPane();
    private final GridPane GRID_PANE_FIELDS = new GridPane();
    private final StackPane STACK_PANE_CENTER = new StackPane();
    private final GridPane SETTINGS_WINDOW_PANE = new GridPane();
    private final HBox H_BOX_BOTTOM = new HBox();

    private final Group WIN_LINES = new Group();
    private final ToggleGroup CHAR_BUTTONS_GROUP = new ToggleGroup();
    private final ToggleButton O_BUTTON = new ToggleButton("O");
    private final ToggleButton X_BUTTON = new ToggleButton("X");
    private final ToggleGroup RADIO_LEVEL_GROUP = new ToggleGroup();

    private final ListView<String> HIGH_SCORE_LIST_VIEW = new ListView<>();

    Scene mainScene = new Scene(MAIN_GRID, 800, 800, Color.rgb(0, 100, 148));
    Scene settingsScene = new Scene(SETTINGS_WINDOW_PANE, 800, 800, Color.rgb(0, 100, 148));

    public void drawMainGame(){
        createAllGrids();

        TITLE.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        TITLE.setFill(Color.rgb(130, 197, 233));
        TITLE.setText("Tic-Tac-Toe Game");

        drawClickableFields();

        BUTTON_NEXT_ROUND.setOnAction((e) -> {
            WIN_LINES.getChildren().clear();
            game.restartGame();
            drawMove();
        });

        SCORE_BOARD.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        SCORE_BOARD.setFill(Color.rgb(130, 197, 233));
        SCORE_BOARD.setY(50);
        SCORE_BOARD.setText(userName + game.getScoreBoard());
    }

    public void createAllGrids(){
        MAIN_GRID.setStyle("-fx-background-color: #006594");
        MAIN_GRID.setTop(TITLE);
        BorderPane.setAlignment(TITLE, Pos.CENTER);
        MAIN_GRID.setCenter(STACK_PANE_CENTER);
        MAIN_GRID.setBottom(H_BOX_BOTTOM);
        BorderPane.setAlignment(H_BOX_BOTTOM, Pos.TOP_CENTER);

        STACK_PANE_CENTER.getChildren().addAll(GRID_PANE_FIELDS, WIN_LINES);

        GRID_PANE_FIELDS.setMinSize(400, 200);
        GRID_PANE_FIELDS.setPadding(new Insets(75, 10, 50, 10));
        GRID_PANE_FIELDS.setVgap(5);
        GRID_PANE_FIELDS.setHgap(15);
        GRID_PANE_FIELDS.setAlignment(Pos.CENTER);

        H_BOX_BOTTOM.getChildren().addAll(BUTTON_NEXT_ROUND, SCORE_BOARD);
        H_BOX_BOTTOM.setAlignment(Pos.CENTER);
        HBox.setMargin(BUTTON_NEXT_ROUND,new Insets(20, 20, 40, 20));
        HBox.setMargin(SCORE_BOARD,new Insets(20, 20, 40, 20));
    }

    public void drawClickableFields(){
        int x=0;
        for (int i = 0; i < 3; i++){
            for (int n=0; n<3; n++){
                FIELDS[x] = new Rectangle(200, 200);
                FIELDS[x].setFill(Color.rgb(36, 123, 160));
                int a = i;
                int b = n;
                FIELDS[x].setOnMouseClicked(t -> {
                    game.userMove(a,b);
                    drawMove();
                    drawWinLine();
                });
                GRID_PANE_FIELDS.add(FIELDS[x],n,i);

                MOVES[x] = new Text();
                MOVES[x].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
                MOVES[x].setFill(Color.rgb(130, 197, 233));
                MOVES[x].setText(positionToString(n,i));
                GRID_PANE_FIELDS.add(MOVES[x],n,i);
                GridPane.setHalignment(MOVES[x], HPos.CENTER);

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
                MOVES[x].setText(positionToString(i, n));
                x ++;
            }
        }
        SCORE_BOARD.setText(userName + game.getScoreBoard());
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

        WIN_LINES.getChildren().addAll(winnerLine);
    }

    public void drawSettingsWindow(){
        O_BUTTON.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
        O_BUTTON.setStyle("-fx-text-fill: #247ba0");
        X_BUTTON.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
        X_BUTTON.setStyle("-fx-text-fill: #247ba0");
        O_BUTTON.setToggleGroup(CHAR_BUTTONS_GROUP);
        X_BUTTON.setToggleGroup(CHAR_BUTTONS_GROUP);
        O_BUTTON.setUserData("o");
        X_BUTTON.setUserData("x");
        O_BUTTON.setSelected(true);

        LEVEL_BUTTON_EASY.setToggleGroup(RADIO_LEVEL_GROUP);
        LEVEL_BUTTON_HARD.setToggleGroup(RADIO_LEVEL_GROUP);
        LEVEL_BUTTON_EASY.setUserData(false);
        LEVEL_BUTTON_HARD.setUserData(true);
        LEVEL_BUTTON_HARD.setSelected(true);

        readRanking();

        HIGH_SCORE_LIST_VIEW.setPrefWidth(5060);
        HIGH_SCORE_LIST_VIEW.setPrefHeight(5000);
        HIGH_SCORE_LIST_VIEW.setMouseTransparent(true);
        HIGH_SCORE_LIST_VIEW.setFocusTraversable(false);

        Label labelChar = new Label("Character:");
        Label levelLabel = new Label("Level:");
        Label enterNameLabel = new Label("Name:");
        labelChar.setTextFill(Color.rgb(255,255,255));
        levelLabel.setTextFill(Color.rgb(255,255,255));
        enterNameLabel.setTextFill(Color.rgb(255,255,255));
        LEVEL_BUTTON_HARD.setTextFill(Color.rgb(255,255,255));
        LEVEL_BUTTON_EASY.setTextFill(Color.rgb(255,255,255));
        USER_NAME_TEXT_FIELD.setMaxWidth(235);

        SETTINGS_WINDOW_PANE.setStyle("-fx-background-color: #006594");
        SETTINGS_WINDOW_PANE.setPadding(new Insets(10, 10,10,200));
        SETTINGS_WINDOW_PANE.setVgap(20);
        SETTINGS_WINDOW_PANE.setHgap(20);

        SETTINGS_WINDOW_PANE.add(HIGH_SCORE_LIST_VIEW, 0,0,3,10);
        SETTINGS_WINDOW_PANE.add(labelChar,0,10);
        SETTINGS_WINDOW_PANE.add(O_BUTTON, 1,10);
        SETTINGS_WINDOW_PANE.add(X_BUTTON,2,10);
        SETTINGS_WINDOW_PANE.add(levelLabel, 0, 11);
        SETTINGS_WINDOW_PANE.add(LEVEL_BUTTON_EASY, 1,11);
        SETTINGS_WINDOW_PANE.add(LEVEL_BUTTON_HARD, 2, 11);
        SETTINGS_WINDOW_PANE.add(enterNameLabel,0,12);
        SETTINGS_WINDOW_PANE.add(USER_NAME_TEXT_FIELD,1,12,10,1);
        SETTINGS_WINDOW_PANE.add(BUTTON_NEW_GAME, 1,13);
    }

    public void newGame() {
        WIN_LINES.getChildren().clear();
        game.restartGame();
        char userCharChoice = CHAR_BUTTONS_GROUP.getSelectedToggle().getUserData().toString().charAt(0);
        char determinateCompChar = 'o';
        if (userCharChoice == 'o'){
            determinateCompChar = 'x';
        }
        game = new GameEngine(userCharChoice, determinateCompChar);
        game.setLevelHard((Boolean) RADIO_LEVEL_GROUP.getSelectedToggle().getUserData());
        userName = USER_NAME_TEXT_FIELD.getText();
        drawMove();
    }

    public void saveRanking(){
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/output.txt", true);
            if (game.getPoints() > 0){
                myWriter.write(userName + ": " + game.getPoints() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readRanking(){
        try {
            File myObj = new File("src/main/resources/output.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                HIGH_SCORE_LIST_VIEW.getItems().add(0, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){
        saveRanking();
    }

    @Override
    public void start(Stage primaryStage){
        drawMainGame();
        drawSettingsWindow();

        BUTTON_NEW_GAME.setOnAction(e -> {
            newGame();
            primaryStage.setScene(mainScene);
        });

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(settingsScene);
        primaryStage.setX(400);
        primaryStage.setX(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}