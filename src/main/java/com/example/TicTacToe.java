package com.example;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
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
                {1, 1, 2},
                {2, 1, 1},
                {2, 1, 1}
        };

        GameEngine game = new GameEngine(gameBoard);


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

        Rectangle r = new Rectangle(100,100,600,600);
        r.setFill(Color.rgb(36, 123, 160));


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
        list.addAll(r, text, line1, line2, line3, line4,
                pos00, pos01, pos02,
                pos10, pos11, pos12,
                pos20, pos21, pos22,
                lineDiag0, lineDiag1,
                lineCol0, lineCol1, lineCol2,
                lineRow0, lineRow1, lineRow2);



        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
