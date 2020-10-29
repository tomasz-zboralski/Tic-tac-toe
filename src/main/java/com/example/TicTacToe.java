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
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.DARKGRAY);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setFill(Color.WHITE);
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
        r.setFill(Color.GRAY);

        // line \
        Line lineA = new Line(150, 150, 650, 650);
        lineA.setStrokeWidth(30);
        // line /
        Line lineB = new Line(150, 650, 650, 150);
        lineB.setStrokeWidth(30);
        // line |
        Line lineC = new Line(400, 150, 400, 650);
        lineC.setStrokeWidth(30);
        //line --
        Line lineD = new Line(150, 400, 650, 400);
        lineD.setStrokeWidth(30);

        // #board
        Line line1 = new Line(300, 150, 300, 650);
        line1.setStrokeWidth(20);
        Line line2 = new Line(500, 150, 500, 650);
        line2.setStrokeWidth(20);
        Line line3 = new Line(150, 300, 650, 300);
        line3.setStrokeWidth(20);
        Line line4 = new Line(150, 500, 650, 500);
        line4.setStrokeWidth(20);


        ObservableList<Node> list = root.getChildren();
        list.addAll(r, text, lineA,  lineB, lineC, lineD, line1, line2, line3, line4);



        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
