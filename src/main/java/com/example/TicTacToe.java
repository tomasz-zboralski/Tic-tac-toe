package com.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
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
//    Group board;
    Group winLines = new Group();

    GameEngine game = new GameEngine(1, 2);

//    Line lineDiag0 = new Line();//0, 0, 0, 0);
//    Line lineDiag1 = new Line();//0, 0, 0, 0);
//    Line lineCol0 = new Line();//0, 0, 0, 0);
//    Line lineCol1 = new Line();//0, 0, 0, 0);
//    Line lineCol2 = new Line();//0, 0, 0, 0);
//    Line lineRow0 = new Line();//0, 0, 0, 0);
//    Line lineRow1 = new Line();//0, 0, 0, 0);
//    Line lineRow2 = new Line();//0, 0, 0, 0);







//    Text pos00 = new Text();
//    Text pos01 = new Text();
//    Text pos02 = new Text();
//    Text pos10 = new Text();
//    Text pos11 = new Text();
//    Text pos12 = new Text();
//    Text pos20 = new Text();
//    Text pos21 = new Text();
//    Text pos22 = new Text();

    Text[] moves = new Text[10];


//    Text[] pos = new Text[9];
//    for(int i=0; i<pos.length; i++){
//        pos[i] = new Text();
//    }

    Text scoreBoard = new Text();
// Foo[] arrFoo = new Foo[10];
//    public Text[] createBoard(){
//        Text[] pos = new Text[9];// = new Text[];
//        for(int i=0; i<pos.length; i++){
//            pos[i] = new Text();
//        }
//        return pos;
//    }


    public void drawMove(){

//        pos00.setText(game.getPosition(0,0));
//        pos01.setText(game.getPosition(0,1));
//        pos02.setText(game.getPosition(0,2));
//        pos10.setText(game.getPosition(1,0));
//        pos11.setText(game.getPosition(1,1));
//        pos12.setText(game.getPosition(1,2));
//        pos20.setText(game.getPosition(2,0));
//        pos21.setText(game.getPosition(2,1));
//        pos22.setText(game.getPosition(2,2));

        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                moves[x].setText(game.getPosition(i, n));
                x ++;
            }
        }

//        moves[0].setText(game.getPosition(0,0));
//        moves[1].setText(game.getPosition(0,1));
//        moves[2].setText(game.getPosition(0,2));
//        moves[3].setText(game.getPosition(1,0));
//        moves[4].setText(game.getPosition(1,1));
//        moves[5].setText(game.getPosition(1,2));
//        moves[6].setText(game.getPosition(2,0));
//        moves[7].setText(game.getPosition(2,1));
//        moves[8].setText(game.getPosition(2,2));



        scoreBoard.setText(game.getScoreBoard());
    }



    public void drawWinnerLine(){
        Map<String,Boolean> playerLine = game.getWinnerLines(game.getPlayer());
        Map<String,Boolean> computerLine = game.getWinnerLines(game.getComputer());

        Line lineDiag0 = new Line();//0, 0, 0, 0);
        Line lineDiag1 = new Line();//0, 0, 0, 0);
        Line lineCol0 = new Line();//0, 0, 0, 0);
        Line lineCol1 = new Line();//0, 0, 0, 0);
        Line lineCol2 = new Line();//0, 0, 0, 0);
        Line lineRow0 = new Line();//0, 0, 0, 0);
        Line lineRow1 = new Line();//0, 0, 0, 0);
        Line lineRow2 = new Line();//0, 0, 0, 0);

//        Line lineDiag0 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineDiag0") || computerLine.get("lineDiag0")){//(game.checkLine(game.getDiag0()) == 1 || game.checkLine(game.getDiag0()) == 2) {
            lineDiag0 = new Line(30, 30, 500, 500);
            lineDiag0.setStroke(Color.rgb(27, 152, 224));
            lineDiag0.setStrokeWidth(30);
        }

//        Line lineDiag1 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineDiag1") || computerLine.get("lineDiag1")) {
            lineDiag1 = new Line(30, 500, 500, 30);
            lineDiag1.setStroke(Color.rgb(27, 152, 224));
            lineDiag1.setStrokeWidth(30);
        }

        // line |
//        Line lineCol0 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineCol0") || computerLine.get("lineCol0")) {
            lineCol0 = new Line(-400, 0, -400, 500);
            lineCol0.setStroke(Color.rgb(27, 152, 224));
            lineCol0.setStrokeWidth(30);
        }

//        Line lineCol1 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineCol1") || computerLine.get("lineCol1")) {
            lineCol1 = new Line(0, 0, 0, 500);
            lineCol1.setStroke(Color.rgb(27, 152, 224));
            lineCol1.setStrokeWidth(30);
        }

//        Line lineCol2 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineCol2") || computerLine.get("lineCol2")) {
            lineCol2 = new Line(400, 0, 400, 500);
            lineCol2.setStroke(Color.rgb(27, 152, 224));
            lineCol2.setStrokeWidth(30);
        }

        //row1 --
//        Line lineRow0 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineRow0") || computerLine.get("lineRow0")) {
            lineRow0 = new Line(-250, -400, 250, -400);
            lineRow0.setStroke(Color.rgb(27, 152, 224));
            lineRow0.setStrokeWidth(30);
        }

//        Line lineRow1 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineRow1") || computerLine.get("lineRow1")) {
            lineRow1 = new Line(-250, 0, 250, 0);
            lineRow1.setStroke(Color.rgb(27, 152, 224));
            lineRow1.setStrokeWidth(30);
        }

//        Line lineRow2 = new Line(0, 0, 0, 0);
        if (playerLine.get("lineRow2") || computerLine.get("lineRow2")) {
            lineRow2 = new Line(-250, 400, 250, 400);
            lineRow2.setStroke(Color.rgb(27, 152, 224));
            lineRow2.setStrokeWidth(30);
        }

        winLines.getChildren().addAll(lineDiag0, lineDiag1,
                lineCol0, lineCol1, lineCol2,
                lineRow0, lineRow1, lineRow2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Text title = new Text();
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setFill(Color.rgb(130, 197, 233));
        title.setText("Tic-Tac-Toe Game");
//        Reflection reflection = new Reflection();
//        reflection.setBottomOpacity(0.0);
//        reflection.setTopOpacity(0.4);
//        reflection.setTopOffset(0.0);
//        reflection.setFraction(0.5);
//        title.setEffect(reflection);
//
//        Rectangle field1 = new Rectangle(100,100,200,200);
//        field1.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field2 = new Rectangle(300,100,200,200);
//        field2.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field3 = new Rectangle(500,100,200,200);
//        field3.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field4 = new Rectangle(100,300,200,200);
//        field4.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field5 = new Rectangle(300,300,200,200);
//        field5.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field6 = new Rectangle(500,300,200,200);
//        field6.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field7 = new Rectangle(100,500,200,200);
//        field7.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field8 = new Rectangle(300,500,200,200);
//        field8.setFill(Color.rgb(36, 123, 160));
//
//        Rectangle field9 = new Rectangle(500,500,200,200);
//        field9.setFill(Color.rgb(36, 123, 160));






//        TilePane tilePane = new TilePane();
//
//        //Setting the orientation for the Tile Pane
//        tilePane.setOrientation(Orientation.HORIZONTAL);
//
//        //Setting the alignment for the Tile Pane
//        tilePane.setTileAlignment(Pos.CENTER_LEFT);
//
//        //Setting the preferred columns for the Tile Pane
//        tilePane.setPrefRows(3);
//        tilePane.setPrefColumns(3);
//
//        tilePane.getChildren().addAll(field1,field2,field3,field4,field5,field6,field7,field8,field9);

//
//
//
//
//
//
//
//
//        // #board
//        Line line1 = new Line(300, 150, 300, 650);
//        line1.setStroke(Color.rgb(10, 71, 105));
//        line1.setStrokeWidth(20);
//        Line line2 = new Line(500, 150, 500, 650);
//        line2.setStroke(Color.rgb(10, 71, 105));
//        line2.setStrokeWidth(20);
//        Line line3 = new Line(150, 300, 650, 300);
//        line3.setStroke(Color.rgb(10, 71, 105));
//        line3.setStrokeWidth(20);
//        Line line4 = new Line(150, 500, 650, 500);
//        line4.setStroke(Color.rgb(10, 71, 105));
//        line4.setStrokeWidth(20);
//
//
//        pos00 = new Text();
//        pos00.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos00.setFill(Color.rgb(130, 197, 233));
//        pos00.setX(150);
//        pos00.setY(250);
//        pos00.setText(game.getPosition(0,0));
////        GridPane.setConstraints(pos00, 4, 4);
//
//        pos01 = new Text();
//        pos01.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos01.setFill(Color.rgb(130, 197, 233));
//        pos01.setX(350);
//        pos01.setY(250);
//        pos01.setText(game.getPosition(0,1));
//
//        pos02 = new Text();
//        pos02.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos02.setFill(Color.rgb(130, 197, 233));
//        pos02.setX(525);
//        pos02.setY(250);
//        pos02.setText(game.getPosition(0,2));
//
//        pos10 = new Text();
//        pos10.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos10.setFill(Color.rgb(130, 197, 233));
//        pos10.setX(150);
//        pos10.setY(450);
//        pos10.setText(game.getPosition(1,0));
//
//        pos11 = new Text();
//        pos11.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos11.setFill(Color.rgb(130, 197, 233));
//        pos11.setX(350);
//        pos11.setY(450);
//        pos11.setText(game.getPosition(1,1));
//
//        pos12 = new Text();
//        pos12.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos12.setFill(Color.rgb(130, 197, 233));
//        pos12.setX(525);
//        pos12.setY(450);
//        pos12.setText(game.getPosition(1,2));
//
//        pos20 = new Text();
//        pos20.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos20.setFill(Color.rgb(130, 197, 233));
//        pos20.setX(150);
//        pos20.setY(650);
//        pos20.setText(game.getPosition(2,0));
//
//        pos21 = new Text();
//        pos21.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos21.setFill(Color.rgb(130, 197, 233));
//        pos21.setX(350);
//        pos21.setY(650);
//        pos21.setText(game.getPosition(2,1));
//
//        pos22 = new Text();
//        pos22.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
//        pos22.setFill(Color.rgb(130, 197, 233));
//        pos22.setX(525);
//        pos22.setY(650);
//        pos22.setText(game.getPosition(2,2));



        GridPane gridPaneFields = new GridPane();
        gridPaneFields.setMinSize(400, 200);
        gridPaneFields.setPadding(new Insets(75, 10, 50, 10));
        gridPaneFields.setVgap(5);
        gridPaneFields.setHgap(15);
        gridPaneFields.setAlignment(Pos.CENTER);

        Rectangle[] fields = new Rectangle[10];
        for (int i = 0; i <fields.length; i++){
            fields[i] = new Rectangle(200, 200);
            fields[i].setFill(Color.rgb(36, 123, 160));
        }

        int x=0;
        for (int i = 0; i < 3; i++){
            for (int n=0; n<3; n++){
                int a = i;
                int b = n;
                fields[x].setOnMouseClicked(t -> {
                    game.playerMove(a,b);
                    drawMove();
                    drawWinnerLine();
                });
                gridPaneFields.add(fields[x],n,i);
                x++;
            }
        }

//        GridPane gridPaneMoves = new GridPane();
//
//        //Setting size for the pane
//        gridPaneMoves.setMinSize(400, 200);
//
//        //Setting the padding
//        gridPaneMoves.setPadding(new Insets(0, 10, 10, 10));
//        gridPaneMoves.setVgap(5);
//        gridPaneMoves.setHgap(5);
//
//        gridPaneMoves.setAlignment(Pos.CENTER);

        moves = new Text[10];
        for (int i = 0; i <moves.length; i++){
            moves[i] = new Text();
            moves[i].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
            moves[i].setFill(Color.rgb(130, 197, 233));
        }

        int y = 0;
        for (int i = 0; i < 3; i++){
            for (int n=0; n<3; n++){
                moves[y].setText(game.getPosition(n,i));
                gridPaneFields.add(moves[y],n,i);
                GridPane.setHalignment(moves[y], HPos.CENTER);
                y++;
            }
        }

        Button buttonNewGame = new Button();
        buttonNewGame.setText("New Game");
        buttonNewGame.setPrefSize(100, 50);
        //buttonNewGame.setLayoutX(150); // Sets the X co-ordinate
        //buttonNewGame.setLayoutY(50); // Sets the Y co-ordinate
        buttonNewGame.setOnAction((e) -> {
            winLines.getChildren().clear();
            game.restartGame();
            drawMove();
        });

        scoreBoard.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        scoreBoard.setFill(Color.rgb(130, 197, 233));
        //scoreBoard.setX(300);
        scoreBoard.setY(50);
        scoreBoard.setText(game.getScoreBoard());

        //EventHandler<MouseEvent>[] click = new EventHandler<>[9];


//
//        EventHandler<MouseEvent> eventHandler00 = e -> {
//            game.playerMove(0,0);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler01 = e -> {
//            game.playerMove(0,1);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler02 = e -> {
//            game.playerMove(0,2);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler10 = e -> {
//            game.playerMove(1,0);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler11 = e -> {
//            game.playerMove(1,1);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler12 = e -> {
//            game.playerMove(1,2);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler20 = e -> {
//            game.playerMove(2,0);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler21 = e -> {
//            game.playerMove(2,1);
//            drawMove();
//            drawWinnerLine();
//        };
//        EventHandler<MouseEvent> eventHandler22 = e -> {
//            game.playerMove(2,2);
//            drawMove();
//            drawWinnerLine();
//        };

//        field1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler00);
//        field2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler01);
//        field3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler02);
//        field4.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler10);
//        field5.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler11);
//        field6.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler12);
//        field7.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler20);
//        field8.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler21);
//        field9.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler22);



//        fields[0].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler00);
//        fields[1].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler01);
//        fields[2].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler02);
//        fields[3].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler10);
//        fields[4].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler11);
//        fields[5].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler12);
//        fields[6].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler20);
//        fields[7].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler21);
//        fields[8].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler22);



        BorderPane mainGrid = new BorderPane();
        mainGrid.setStyle("-fx-background-color: #006594");

        StackPane StackPaneCenter = new StackPane();
        mainGrid.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

//        board = new Group();
//        board.getChildren().addAll(//moves);
////                field1, field2, field3,
////                field4, field5, field6,
////                field7, field8, field9,
////                line1, line2, line3, line4,
//                pos00, pos01, pos02,
//                pos10, pos11, pos12,
//                pos20, pos21, pos22);

        StackPaneCenter.getChildren().addAll(gridPaneFields, winLines);
        mainGrid.setCenter(StackPaneCenter);

        HBox hboxBottom = new HBox();
        hboxBottom.getChildren().addAll(buttonNewGame, scoreBoard);
        hboxBottom.setAlignment(Pos.CENTER);
        //hboxBottom.setSpacing(100);
        HBox.setMargin(buttonNewGame,new Insets(20, 70, 40, 20));
        HBox.setMargin(scoreBoard,new Insets(20, 20, 40, 20));
        mainGrid.setBottom(hboxBottom);
        BorderPane.setAlignment(hboxBottom, Pos.TOP_CENTER);

        Scene scene = new Scene(mainGrid, 800, 800, Color.rgb(0, 100, 148));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
