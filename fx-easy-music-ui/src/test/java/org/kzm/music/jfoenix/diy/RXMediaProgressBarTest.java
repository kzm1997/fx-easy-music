package org.kzm.music.jfoenix.diy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RXMediaProgressBarTest  extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane=new BorderPane();
        Scene scene=new Scene(borderPane,700,700);
        RXMediaProgressBar rxMediaProgressBar=new RXMediaProgressBar();
        
        
        borderPane.setCenter(rxMediaProgressBar);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void initProgressBar(MediaPlayer player,RXMediaProgressBar progressBar) {
        progressBar.setCurrentTime(Duration.ZERO);
        progressBar.durationProperty().bind(player.getMedia().durationProperty());
        progressBar.bufferProgressTimeProperty().bind(player.bufferProgressTimeProperty());
        player.currentTimeProperty().addListener((ob1, ov1, nv1) -> {
            progressBar.setCurrentTime(nv1);
        });

        progressBar.setOnMouseDragged(event1 -> {
            if ( player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.seek(progressBar.getCurrentTime());
            }
        });

        progressBar.setOnMouseClicked(event1 -> {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.seek(progressBar.getCurrentTime());

            }
        });
    }
}
