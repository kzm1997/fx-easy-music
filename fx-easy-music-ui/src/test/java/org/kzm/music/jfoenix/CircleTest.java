package org.kzm.music.jfoenix;

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
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import org.jaudiotagger.Test;
import org.kzm.music.utils.AnimationUtil;


public class CircleTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,1028,800);

        ImageView  rodImageView = new ImageView("/fxml/main/img/rodImageView.png");
        Circle circle1 = new Circle();
        rodImageView.setFitWidth(115);
        rodImageView.setFitHeight(175);
        rodImageView.getTransforms().add(new Translate(0,100));
/*        circle1.setCenterX(90);
        circle1.setCenterY(50);
        
  
        circle1.setRadius(140);//圆的半径
        rodImageView.setClip(circle1);*/

        rodImageView.setLayoutX(200);
        rodImageView.setLayoutY(200);


        Button button=new Button("test");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //  AnimationUtil.rotate(rodImageView, 200, 0.0, 35.0, 1);
                AnimationUtil.rotate(rodImageView, 200, 0, -30, 1);
              
            }
        });
        
        root.setStyle("-fx-background-color: red");
        root.getChildren().addAll(rodImageView);
        root.getChildren().add(button);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
