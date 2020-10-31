package com.example;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int[][] gameBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        GameEngine game = new GameEngine(gameBoard, 1, 2);

        GridPane gridpane = new GridPane();



        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.rgb(0, 100, 148));

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setFill(Color.rgb(130, 197, 233));
        text.setX(150);
        text.setY(80);
        text.setText("Tic-Tac-Toe Game");
        Reflection reflection = new Reflection();
        reflection.setBottomOpacity(0.0);
        reflection.setTopOpacity(0.4);
        reflection.setTopOffset(0.0);
        reflection.setFraction(0.5);
        text.setEffect(reflection);

//        Rectangle field1 = new Rectangle(100,100,600,600);
//        field1.setFill(Color.rgb(36, 123, 160));

        Rectangle field1 = new Rectangle(100,100,200,200);
        field1.setFill(Color.rgb(36, 123, 160));

        Rectangle field2 = new Rectangle(300,100,200,200);
        field2.setFill(Color.rgb(36, 123, 160));

        Rectangle field3 = new Rectangle(500,100,200,200);
        field3.setFill(Color.rgb(36, 123, 160));

        Rectangle field4 = new Rectangle(100,300,200,200);
        field4.setFill(Color.rgb(36, 123, 160));

        Rectangle field5 = new Rectangle(300,300,200,200);
        field5.setFill(Color.rgb(36, 123, 160));

        Rectangle field6 = new Rectangle(500,300,200,200);
        field6.setFill(Color.rgb(36, 123, 160));

        Rectangle field7 = new Rectangle(100,500,200,200);
        field7.setFill(Color.rgb(36, 123, 160));

        Rectangle field8 = new Rectangle(300,500,200,200);
        field8.setFill(Color.rgb(36, 123, 160));

        Rectangle field9 = new Rectangle(500,500,200,200);
        field9.setFill(Color.rgb(36, 123, 160));


        // #board
        Line line1 = new Line(300, 150, 300, 650);
        line1.setStroke(Color.rgb(10, 71, 105));
        line1.setStrokeWidth(20);
        Line line2 = new Line(500, 150, 500, 650);
        line2.setStroke(Color.rgb(10, 71, 105));
        line2.setStrokeWidth(20);
        Line line3 = new Line(150, 300, 650, 300);
        line3.setStroke(Color.rgb(10, 71, 105));
        line3.setStrokeWidth(20);
        Line line4 = new Line(150, 500, 650, 500);
        line4.setStroke(Color.rgb(10, 71, 105));
        line4.setStrokeWidth(20);

        // line \
        Line lineDiag0 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getDiag0()) == 1 || game.checkLine(game.getDiag0()) == 2) {
            lineDiag0 = new Line(150, 150, 650, 650);
            lineDiag0.setStroke(Color.rgb(27, 152, 224));
            lineDiag0.setStrokeWidth(30);
        }

        // line /
        Line lineDiag1 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getDiag1()) == 1 || game.checkLine(game.getDiag1()) == 2) {
            lineDiag1 = new Line(150, 650, 650, 150);
            lineDiag1.setStroke(Color.rgb(27, 152, 224));
            lineDiag1.setStrokeWidth(30);
        }

        // line |
        Line lineCol0 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getCol0()) == 1 || game.checkLine(game.getCol0()) == 2) {
            lineCol0 = new Line(200, 150, 200, 650);
            lineCol0.setStroke(Color.rgb(27, 152, 224));
            lineCol0.setStrokeWidth(30);
        }

        Line lineCol1 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getCol1()) == 1 || game.checkLine(game.getCol1()) == 2) {
            lineCol1 = new Line(400, 150, 400, 650);
            lineCol1.setStroke(Color.rgb(27, 152, 224));
            lineCol1.setStrokeWidth(30);
        }

        Line lineCol2 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getCol2()) == 1 || game.checkLine(game.getCol2()) == 2) {
            lineCol2 = new Line(600, 150, 600, 650);
            lineCol2.setStroke(Color.rgb(27, 152, 224));
            lineCol2.setStrokeWidth(30);
        }

        //row1 --
        Line lineRow0 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getRow0()) == 1 || game.checkLine(game.getRow0()) == 2) {
            lineRow0 = new Line(150, 200, 650, 200);
            lineRow0.setStroke(Color.rgb(27, 152, 224));
            lineRow0.setStrokeWidth(30);
        }

        Line lineRow1 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getRow1()) == 1 || game.checkLine(game.getRow1()) == 2) {
            lineRow1 = new Line(150, 400, 650, 400);
            lineRow1.setStroke(Color.rgb(27, 152, 224));
            lineRow1.setStrokeWidth(30);
        }

        Line lineRow2 = new Line(0, 0, 0, 0);
        if (game.checkLine(game.getRow2()) == 1 || game.checkLine(game.getRow2()) == 2) {
            lineRow2 = new Line(150, 600, 650, 600);
            lineRow2.setStroke(Color.rgb(27, 152, 224));
            lineRow2.setStrokeWidth(30);
        }

        Text pos00 = new Text();
        pos00.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos00.setFill(Color.rgb(130, 197, 233));
        pos00.setX(150);
        pos00.setY(250);
        pos00.setText(game.getPosition(0,0));
//        GridPane.setConstraints(pos00, 4, 4);

        Text pos01 = new Text();
        pos01.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos01.setFill(Color.rgb(130, 197, 233));
        pos01.setX(350);
        pos01.setY(250);
        pos01.setText(game.getPosition(0,1));

        Text pos02 = new Text();
        pos02.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos02.setFill(Color.rgb(130, 197, 233));
        pos02.setX(525);
        pos02.setY(250);
        pos02.setText(game.getPosition(0,2));

        Text pos10 = new Text();
        pos10.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos10.setFill(Color.rgb(130, 197, 233));
        pos10.setX(150);
        pos10.setY(450);
        pos10.setText(game.getPosition(1,0));

        Text pos11 = new Text();
        pos11.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos11.setFill(Color.rgb(130, 197, 233));
        pos11.setX(350);
        pos11.setY(450);
        pos11.setText(game.getPosition(1,1));

        Text pos12 = new Text();
        pos12.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos12.setFill(Color.rgb(130, 197, 233));
        pos12.setX(525);
        pos12.setY(450);
        pos12.setText(game.getPosition(1,2));

        Text pos20 = new Text();
        pos20.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos20.setFill(Color.rgb(130, 197, 233));
        pos20.setX(150);
        pos20.setY(650);
        pos20.setText(game.getPosition(2,0));

        Text pos21 = new Text();
        pos21.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos21.setFill(Color.rgb(130, 197, 233));
        pos21.setX(350);
        pos21.setY(650);
        pos21.setText(game.getPosition(2,1));

        Text pos22 = new Text();
        pos22.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 150));
        pos22.setFill(Color.rgb(130, 197, 233));
        pos22.setX(525);
        pos22.setY(650);
        pos22.setText(game.getPosition(2,2));


        ObservableList<Node> list = root.getChildren();
        list.addAll(field1, field2, field3,
                field4, field5, field6,
                field7, field8, field9,
                text, line1, line2, line3, line4,
                pos00, pos01, pos02,
                pos10, pos11, pos12,
                pos20, pos21, pos22,
                lineDiag0, lineDiag1,
                lineCol0, lineCol1, lineCol2,
                lineRow0, lineRow1, lineRow2);

//        gridpane.getChildren().addAll(pos00, pos01, pos02,
//                pos10, pos11, pos12,
//               pos20, pos21, pos22);


        EventHandler<MouseEvent> eventHandler00 = e -> {
            if (game.getGameBoard()[0][0] == 0){
                game.playerMove(0,0);
                pos00.setText(game.getPosition(0,0));
            }
        };

        EventHandler<MouseEvent> eventHandler01 = e -> {
            if (game.getGameBoard()[0][1] == 0){
                game.playerMove(0,1);
                pos01.setText(game.getPosition(0,1));
            }
        };
        EventHandler<MouseEvent> eventHandler02 = e -> {
            if (game.getGameBoard()[0][2] == 0){
                game.playerMove(0,2);
                pos02.setText(game.getPosition(0,2));
            }
        };
        EventHandler<MouseEvent> eventHandler10 = e -> {
            if (game.getGameBoard()[1][0] == 0){
                game.playerMove(1,0);
                pos10.setText(game.getPosition(1,0));
            }
        };
        EventHandler<MouseEvent> eventHandler11 = e -> {
            if (game.getGameBoard()[1][1] == 0){
                game.playerMove(1,1);
                pos11.setText(game.getPosition(1,1));
            }
        };
        EventHandler<MouseEvent> eventHandler12 = e -> {
            if (game.getGameBoard()[1][2] == 0){
                game.playerMove(1,2);
                pos12.setText(game.getPosition(1,2));
            }
        };
        EventHandler<MouseEvent> eventHandler20 = e -> {
            if (game.getGameBoard()[2][0] == 0){
                game.playerMove(2,0);
                pos20.setText(game.getPosition(2,0));
            }
        };
        EventHandler<MouseEvent> eventHandler21 = e -> {
            if (game.getGameBoard()[2][1] == 0){
                game.playerMove(2,1);
                pos21.setText(game.getPosition(2,1));
            }
        };
        EventHandler<MouseEvent> eventHandler22 = e -> {
            if (game.getGameBoard()[2][2] == 0){
                game.playerMove(2,2);
                pos22.setText(game.getPosition(2,2));
            }
        };

        field1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler00);
        field2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler01);
        field3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler02);
        field4.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler10);
        field5.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler11);
        field6.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler12);
        field7.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler20);
        field8.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler21);
        field9.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler22);

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
