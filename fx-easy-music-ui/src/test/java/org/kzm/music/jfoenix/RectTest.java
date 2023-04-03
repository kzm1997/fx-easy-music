package org.kzm.music.jfoenix;

import com.sun.deploy.security.MozillaJSSNONEwithRSASignature;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane=new AnchorPane();
        Scene scene=new Scene(anchorPane,800,600);

        Rectangle rectangle= new Rectangle();
        
        rectangle.setWidth(20);
        rectangle.setHeight(60);
        
        rectangle.setLayoutY(100);

        Button button=new Button("222");
        
        button.setLayoutX(300);
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (rectangle.getHeight()==60){
                    rectangle.setHeight(100);
                    rectangle.setLayoutY(rectangle.getLayoutY()-40);
                }else {
                    rectangle.setLayoutY(rectangle.getLayoutY()+40);
                    rectangle.setHeight(60);
                }
            }
        });
        
        rectangle.setFill(Color.RED);
        
        anchorPane.getChildren().addAll(rectangle,button);
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
