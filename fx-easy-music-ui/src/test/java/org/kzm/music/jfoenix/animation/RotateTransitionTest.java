package org.kzm.music.jfoenix.animation;

import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.kzm.music.jfoenix.BaseTest;

public class RotateTransitionTest extends BaseTest {
    @Override
    public Node getTest() {

        ImageView userView = new ImageView(new Image("/fxml/main/img/user.jpg"));
        userView.setFitWidth(60.0);
        userView.setPreserveRatio(true);

        Label userImageLabel = new Label("", userView);

        Circle circle = new Circle();
        circle.centerXProperty().bind(userImageLabel.widthProperty().divide(2));
        circle.centerYProperty().bind(userImageLabel.heightProperty().divide(2));
        circle.radiusProperty().bind(userImageLabel.widthProperty().divide(2));
        userView.setClip(circle);


            RotateTransition rotateTransition=new RotateTransition(Duration.millis(600),userView);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setCycleCount(1);
            rotateTransition.setAutoReverse(false);
            rotateTransition.play();
        

        return userImageLabel;
    }
}
