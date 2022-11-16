package org.kzm.music.jfoenix.animation;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 颜色淡入淡出
 */
public class FillTransitionTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rect=new Rectangle(100,40,100,100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);

        FillTransition ft=new FillTransition(Duration.millis(3000),rect, Color.RED,Color.BLUE);
        
        ft.setCycleCount(-1);
        ft.setAutoReverse(true);
        ft.play();
        Group group=new Group(rect);
        Scene scene=new Scene(group,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
}
