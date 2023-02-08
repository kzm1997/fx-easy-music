package org.kzm.music.ui.main;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.bottom.BotComponent;
import org.kzm.music.ui.main.center.CenterComponent;
import org.kzm.music.ui.main.center.ICenterMethod;
import org.kzm.music.ui.main.center.IPlayCenterMethod;
import org.kzm.music.ui.main.center.PlayComponent;
import org.kzm.music.ui.top.TopComponent;

public class MainController extends MainInit implements IMainMethod {

    UIObject top;

    
    UIObject bottom;

    ICenterMethod center; //主页中心
    
    IPlayCenterMethod palyCenter; //播放页中心
    

    BorderPane borderPane;
    
    public MainController(){
        initView();
    }
    
    
    @Override
    public void initView() {
        top=new TopComponent("网易云音乐",this);
        center=new CenterComponent(this);
        palyCenter=new PlayComponent(this);
        bottom=new BotComponent(this,center,palyCenter);
        Scene scene=new Scene(layoutInit(),1023,666);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        move();
    }

    @Override
    public void initEventDefine() {

    }


    @Override
    public void doShow() {
      super.show();
    }


    @Override
    public void changeCenter(UIObject newCenter) {
        if (newCenter!=null){
            borderPane.setCenter(newCenter.getNode());
        }
  
    }

    
    private Parent layoutInit(){
        borderPane=new BorderPane();
        Image img=new Image("/fxml/main/img/main_back.jpg");
        BackgroundImage bImg=new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        BackgroundFill backgroundFill=new BackgroundFill(Color.rgb(255, 255, 255,1),CornerRadii.EMPTY,null);
        borderPane.setBackground(new Background(backgroundFill));
        borderPane.setTop(top.getNode());
        borderPane.setCenter(center.getUIObject().getNode());
        borderPane.setBottom(bottom.getNode());
        
        return borderPane;
    }


    public void move(){
        top.getNode().setOnMousePressed(event->{
            xOffset=event.getSceneX();
            yOfffset=event.getSceneY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        top.getNode().setOnMouseDragged(event -> {
            setX(event.getScreenX()-xOffset);
            setY(event.getScreenY()-yOfffset);
        });
        top.getNode().setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }
    
}
