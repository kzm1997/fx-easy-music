package org.kzm.music.jfoenix.animation;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 淡入淡出效果
 */
public class FadeTransitionTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circl=new Circle();
        circl.setCenterX(300.0f);
        circl.setCenterY(135.0f);
        circl.setRadius(100.0f);
        circl.setStrokeWidth(20);

        FadeTransition fadeTransition=new FadeTransition(Duration.millis(1000));
        
        fadeTransition.setNode(circl);
        fadeTransition.setFromValue(1.0); //淡入淡出,通过调节透明度
        fadeTransition.setToValue(0.3);
        
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();

        Group root=new Group(circl);
        Scene scene=new Scene(root,600,300);
        primaryStage.setTitle("My Title");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
