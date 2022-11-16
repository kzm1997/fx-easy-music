package org.kzm.music.jfoenix.animation;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 平移动画
 */
public class TranslateTransitionTest extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rect=new Rectangle(100,40,100,100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);

        TranslateTransition tt=new TranslateTransition(Duration.millis(2000),rect);
        tt.setByX(200f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
        tt.play();

        Group group=new Group(rect);
        Scene scene=new Scene(group,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
