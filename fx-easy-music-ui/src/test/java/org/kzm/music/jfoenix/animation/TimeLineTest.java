package org.kzm.music.jfoenix.animation;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimeLineTest extends Application {


    //main timeline
    private Timeline timeline;
    private AnimationTimer timer;

    //variable for storing actual frame

    private Integer i = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group p = new Group();
        Scene scene = new Scene(p, 500, 500);
        primaryStage.setScene(scene);
        p.setTranslateX(80);
        p.setTranslateY(80);

        final Circle circle = new Circle(20, Color.rgb(156, 216, 255));
        circle.setEffect(new Lighting());
        final Text text = new Text(i.toString());
        text.setStroke(Color.BLACK);
        //create a layout for circle with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(30);
        stack.setLayoutY(30);

        p.getChildren().add(stack);
        primaryStage.show();

        //create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        //You can add a specific action when each frame is started.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text.setText(i.toString());
                i++;
            }
        };

        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 2);
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 2);

        Duration duration = Duration.millis(2000);
        //one can add a specific action when the keyframe is reached
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stack.setTranslateX(java.lang.Math.random() * 200 - 100);
                //reset counter
                i = 0;
            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timer.start();
    }
}
