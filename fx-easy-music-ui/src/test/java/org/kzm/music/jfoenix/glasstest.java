package org.kzm.music.jfoenix;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 毛玻璃
 */
public class glasstest  extends Application {

    ImageView iv_blur;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button bt1=new Button("button1");
        Button bt2=new Button("button2");

        ImageView imageView=new ImageView("/fxml/main/img/main_back.jpg");
        
        AnchorPane pane=new AnchorPane();
        AnchorPane.setLeftAnchor(bt1,180.0);
        AnchorPane.setLeftAnchor(bt2,300.0);
        pane.getChildren().addAll(imageView,bt1,bt2);
        
        AnchorPane root=new AnchorPane();
        Node view = getView(primaryStage);
        root.getChildren().addAll(pane,view);

        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(845);
        primaryStage.show();
        
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());

        TranslateTransition tt=new TranslateTransition(Duration.seconds(1),view);
        
        tt.setInterpolator(Interpolator.EASE_OUT);
        
        bt1.setOnAction(event -> {
            tt.setFromX(-200);
            tt.setToX(0);
            tt.play();
            
        });
        
        bt2.setOnAction(event -> {
            tt.setFromX(0);
            tt.setToX(-200);
            tt.play();
        });
        
        view.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int w=200-(-newValue.intValue());
                
                int h=(int)pane.getHeight();
                
                if(w>0){
                    WritableImage writableImage=new WritableImage(w,h);
                    pane.snapshot(new SnapshotParameters(),writableImage);  //截图
                    iv_blur.setImage(writableImage);
                }

        
                
            }
        });
        
    }
    
    
    public Node getView(Stage stage){
        
        StackPane sp=new StackPane();
        
        AnchorPane ap=new AnchorPane();
        iv_blur=new ImageView();
        AnchorPane.setRightAnchor(iv_blur,0.0);
        iv_blur.setEffect(new GaussianBlur(20));
        ap.getChildren().add(iv_blur);

        VBox vbox=new VBox(20);
        vbox.setStyle("-fx-background-color: #ffffff55");
        vbox.setPrefWidth(200);
        for (int i = 0; i < 5; i++) {
            vbox.getChildren().add(new Text("hello javafx "+i));
        }
        sp.setTranslateX(-200);
        sp.getChildren().addAll(ap,vbox);
        vbox.prefHeightProperty().bind(stage.heightProperty());
        
        return sp;
        
    }
}
