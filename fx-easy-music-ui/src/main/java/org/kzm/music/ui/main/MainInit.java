package org.kzm.music.ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.UIStage;
import org.kzm.music.ui.main.center.CenterComponent;
import org.kzm.music.ui.top.TopComponent;


import java.io.IOException;

public abstract class MainInit extends UIStage {
    
    
    private UIObject top;
    
    private JFXDrawer centerJFXDrawer;
    
    private UIObject bottom;
    
    private UIObject center;
    
    
    
    MainInit(){
        top=new TopComponent("网易云音乐",this);
        center=new CenterComponent();
        Scene scene=new Scene(layoutInit(),1023,666);
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

        centerJFXDrawer=new JFXDrawer();
        //弹出的位置,从下往上弹出
        centerJFXDrawer.setDirection(JFXDrawer.DrawerDirection.BOTTOM);
        centerJFXDrawer.setContent(center.getNode()); //设置内容
        
        StackPane buttomDrawerPane=new StackPane();
        BackgroundFill backgroundFill2=new BackgroundFill(Color.rgb(255, 25, 255,1),CornerRadii.EMPTY,null);
        buttomDrawerPane.setBackground(new Background(backgroundFill2));
        buttomDrawerPane.getChildren().add(new JFXButton("Buttoom Content"));
        centerJFXDrawer.setResizeContent(true);
        centerJFXDrawer.setOverLayVisible(false);
        centerJFXDrawer.setResizableOnDrag(true);
        
        
        centerJFXDrawer.setSidePane(buttomDrawerPane);
        borderPane.setCenter(centerJFXDrawer);
       // borderPane.setBottom(bottom.getNode());
        
        
        return borderPane;
    }
    
    
    
    
    
    
    
    
}
