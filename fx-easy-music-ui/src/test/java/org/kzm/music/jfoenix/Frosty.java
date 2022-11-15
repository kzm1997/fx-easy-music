package org.kzm.music.jfoenix;


import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Frosty extends Application {

    private Image backgroundImage;

    private static final double W = 1278;
    private static final double H = 845;

    @Override
    public void init() {
        backgroundImage = new Image("/fxml/main/img/main_back.jpg");
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Drag the mouse to pan the map");
        stage.setResizable(false);

        // make a transparent pale blue overlay with non transparent blue writing on it.
        final Label label = new Label("Map\nof\nNarnia");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-text-fill: midnightblue;  -fx-font: bold italic 40 'serif'; -fx-padding: 0 0 20 0;");

        StackPane glass = new StackPane();
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        glass.getChildren().addAll(label);
        glass.setStyle("-fx-background-color: rgba(0, 100, 100, 0.1);");
        glass.setMaxWidth(W * 1/4);
        glass.setMaxHeight(H);
        StackPane.setAlignment(glass, Pos.CENTER_LEFT);

        // construct a partitioned node with left side blurred.
        ImageView leftMap = new ImageView(backgroundImage);
        ImageView rightMap = new ImageView(backgroundImage);

        // wrap the partitioned node in a pannable scroll pane.
        ScrollPane leftScroll = createScrollPane(leftMap);
        Rectangle leftClip = new Rectangle(W * 1/4, H);
        leftScroll.setClip(leftClip);
        leftScroll.setEffect(new GaussianBlur());

        ScrollPane rightScroll = createScrollPane(rightMap);
        Rectangle rightClip = new Rectangle(W * 1/4, 0, W * 3/4, H);
        rightScroll.setClip(rightClip);

        StackPane composite = new StackPane();
        composite.getChildren().setAll(
                leftScroll,
                rightScroll
        );

        StackPane layout = new StackPane(
                composite,
                glass
        );

        // show the scene.
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        // bind the scroll values together and center the scroll contents.
        leftScroll.hvalueProperty().bind(rightScroll.hvalueProperty());
        leftScroll.vvalueProperty().bind(rightScroll.vvalueProperty());
        rightScroll.setHvalue(rightScroll.getHmin() + (rightScroll.getHmax() - rightScroll.getHmin()) / 2);
        rightScroll.setVvalue(rightScroll.getVmin() + (rightScroll.getVmax() - rightScroll.getVmin()) / 2);
    }

    /**
     * @return a ScrollPane which scrolls the node.
     */
    private ScrollPane createScrollPane(Node node) {
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setPannable(true);
        scroll.setMinSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        scroll.setPrefSize(W, H);
        scroll.setMaxSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        scroll.setContent(node);
        return scroll;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
