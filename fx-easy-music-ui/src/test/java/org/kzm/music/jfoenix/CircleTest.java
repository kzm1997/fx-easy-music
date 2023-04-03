package org.kzm.music.jfoenix;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jaudiotagger.Test;
import org.kzm.music.utils.AnimationUtil;


public class CircleTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,1028,800);

        ImageView  rodImageView = new ImageView("/fxml/main/img/rodImageView.png");


        
        Rotate rotation=new Rotate();
        
        rodImageView.setFitWidth(115);
        rodImageView.setFitHeight(175);

        rodImageView.setLayoutX(200);
        rodImageView.setLayoutY(200);

        Rotate rotate2=new Rotate(-20,10,0);

        rodImageView.getTransforms().add(rotate2);
        
        rotation.setPivotX(rodImageView.xProperty().get()+10);
        rotation.setPivotY(rodImageView.yProperty().get()+10);
       // rotation.pivotXProperty().bind(rodImageView.xProperty());
        //rotation.pivotYProperty().bind(rodImageView.yProperty());
        
        
        
        rodImageView.getTransforms().add(rotation);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(rotation.angleProperty(), 20)));
        
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 20)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(rotation.angleProperty(), 0)));

        Button button=new Button("test");
        
        Button button2=new Button("test2");
        
        button2.setLayoutX(200);
        
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline2.play();
            }
        });
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //  AnimationUtil.rotate(rodImageView, 200, 0.0, 35.0, 1);
                timeline.play();
              
            }
        });
        
        root.setStyle("-fx-background-color: red");
        root.getChildren().addAll(rodImageView);
        root.getChildren().add(button);
        root.getChildren().add(button2);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
