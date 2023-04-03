package org.kzm.music.jfoenix;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.kzm.music.pojo.LrcDoc;
import org.kzm.music.ui.component.RXAudioSpectrum;
import org.kzm.music.ui.component.RXLrcView;
import org.kzm.music.ui.component.RXMediaProgressBar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CircleTst2 extends Application {

    MediaPlayer player = null;

    RXAudioSpectrum spectrum = new RXAudioSpectrum();

    RXLrcView lrcPane=new RXLrcView();

    RXMediaProgressBar progressBar=new RXMediaProgressBar();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        
        VBox hBox=new VBox();
        
        hBox.getChildren().addAll(lrcPane);
        
        

        Button button = new Button("play");
        root.setBottom(spectrum);
        button.setOnAction(action -> {
            FileChooser fileChooser = new FileChooser();
            Window window = primaryStage.getScene().getWindow();
            File mp3File = fileChooser.showOpenDialog(window);
            if (mp3File != null) {

                File dir = new File(mp3File.getParent());
                if (dir.isDirectory()) {
                    fileChooser.setInitialDirectory(dir);
                }
                player = new MediaPlayer(new Media(mp3File.toURI().toString()));
                initSpectrum(player);
                initLrc(mp3File);
                initProgressBar(player);
                player.play();
                
            }
        });
        hBox.getChildren().add(button);
        root.setCenter(hBox);
        
        root.setBottom(spectrum);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void initSpectrum(MediaPlayer player) {
        player.setAudioSpectrumThreshold(-65);
        spectrum.audioSpectrumNumBandsProperty().bind(player.audioSpectrumNumBandsProperty());
        spectrum.audioSpectrumThresholdProperty().bind(player.audioSpectrumThresholdProperty());
        player.setAudioSpectrumListener((timestamp, duration, magnitudes, phases) -> {
            spectrum.setMagnitudes(magnitudes);
        });
    }

    private void initLrc(File file) {
        String lrcPath = file.getAbsolutePath().replaceAll("mp3$","lrc");
        File lrcFile = new File(lrcPath);
        if (lrcFile.exists()) {
            String lrc = "";
            try {

                lrc = new String(Files.readAllBytes(Paths.get(lrcPath)), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            lrcPane.setLrcDoc(LrcDoc.parseLrcDoc(lrc));
            lrcPane.currentTimeProperty().bind(player.currentTimeProperty());
        }
    }

    private void initProgressBar(MediaPlayer player) {
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
