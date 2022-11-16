package org.kzm.music.jfoenix;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,1028,800);
        root.setCenter(getTest());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public abstract Node getTest();
}
