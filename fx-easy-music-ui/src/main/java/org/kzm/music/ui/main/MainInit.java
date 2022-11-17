package org.kzm.music.ui.main;

import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.UIStage;
import org.kzm.music.ui.top.TopComponent;


import java.io.IOException;

public abstract class MainInit extends UIStage {
    
    
    private UIObject top;
    
    private JFXDrawer center;
    
    private UIObject bottom;
    
    
    
    MainInit(){
        top=new TopComponent("网易云音乐",this);
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
        BackgroundFill backgroundFill=new BackgroundFill(Color.rgb(255, 255, 255,1),CornerRadii.EMPTY,null);
        borderPane.setBackground(new Background(backgroundFill));
        borderPane.setTop(top.getNode());
        return borderPane;
    }
    
    
    
    
    
    
    
    
}
