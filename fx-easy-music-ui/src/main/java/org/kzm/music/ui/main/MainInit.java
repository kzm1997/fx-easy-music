package org.kzm.music.ui.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.UIStage;
import org.kzm.music.ui.top.TopComponent;


import java.io.IOException;

public abstract class MainInit extends UIStage {
    
    
    private UIObject top;
    
    
    
    MainInit(){
        top=new TopComponent("网易云音乐");
        Scene scene=new Scene(layoutInit(),1278,845);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);

    }
    
    private Parent layoutInit(){
        BorderPane borderPane=new BorderPane();
        
        Image img=new Image("/fxml/main/img/main_back.jpg");
        BackgroundImage bImg=new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(bImg));
        borderPane.setTop(top.getNode());

        return borderPane;
    }
    
    
    
    
    
    
    
    
}
