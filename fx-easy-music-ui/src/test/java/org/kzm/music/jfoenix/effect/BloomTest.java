package org.kzm.music.jfoenix.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 发光特效
 */
public class BloomTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.1);

        Rectangle rect = new Rectangle();
        rect.setX(10);
        rect.setY(10);
        rect.setWidth(160);
        rect.setHeight(80);
        rect.setFill(Color.DARKSLATEBLUE);

        Text text = new Text();
        text.setText("Bloom!");
        text.setFill(Color.ALICEBLUE);
        text.setFont(Font.font(null, FontWeight.BOLD, 40));
        text.setX(25);
        text.setY(65);
        text.setEffect(bloom);
        
        Group g=new Group();
        g.getChildren().addAll(rect,text);
        Scene sc=new Scene(g,500,500);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
    
}
