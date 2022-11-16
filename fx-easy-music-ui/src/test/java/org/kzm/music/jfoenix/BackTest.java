package org.kzm.music.jfoenix;

import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class BackTest extends BaseTest {
    @Override
    public Node getTest() {
        ImageView imageView=new ImageView("/fxml/main/img/main_back.jpg");
        imageView.setFitHeight(1024);
        imageView.setFitHeight(800);
        imageView.setSmooth(true);
        Rectangle rec=new Rectangle(imageView.prefWidth(-1),imageView.prefHeight(-1));
        rec.setArcHeight(40);
        rec.setArcWidth(40);
        imageView.setClip(rec);
        imageView.setEffect(new GaussianBlur(20));
      //  imageView.setOpacity(0);
        
        return imageView;
    }
}
