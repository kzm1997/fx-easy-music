package org.kzm.music.jfoenix.animation;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 路径动画测试
 */
public class PathTransitionTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rect=new Rectangle(100,40,100,100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);

        Path path=new Path();
        path.getElements().add(new MoveTo(0f,50f));
        path.getElements().add(new CubicCurveTo(40f,10f,390f,240f,1904,50f));

        PathTransition pathTransition=new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setNode(rect);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(4);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        Group group=new Group(rect);

        Scene scene=new Scene(group,400,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
