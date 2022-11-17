package org.kzm.music.ui.main.side;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.effects.JFXDepthManager;
import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kzm.music.ui.UIObject;



public class SideComponent extends UIObject {
    
    
    private Label myLove; 
    
    private Label localMusic; //本地音乐
    
    private Label recentMusic; //最近播放
    
    
    public SideComponent(){
        initComponent();
    }
    
    
    
    @Override
    protected void initComponent() {
        VBox vBox=new VBox();

        AnchorPane anchorPane=new AnchorPane();
        ImageView imageView=new ImageView(new Image("/fxml/main/icon/love.png"));
        imageView.setFitWidth(180);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        anchorPane.setPrefHeight(imageView.getFitHeight()); //?
        
        ImageView userView=new ImageView(new Image("/fxml/main/icon/love.png"));
        userView.setFitWidth(60.0);
        userView.setPreserveRatio(true);

        Label userImageLabel=new Label("",userView);
        userImageLabel.setOnMouseEntered(event->{
            RotateTransition rotateTransition=new RotateTransition(Duration.millis(0.6),userView);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setCycleCount(1);
            rotateTransition.setAutoReverse(false);
            rotateTransition.play();
        });
        
        userImageLabel.setLayoutY(10);
        userImageLabel.setLayoutX(10);
        JFXDepthManager.setDepth(userImageLabel, 4); //?
        
        Label userNameLabel=new Label("作者");
        userNameLabel.setLayoutX(10);
        userNameLabel.setLayoutY(80);
        userNameLabel.setTextFill(Color.WHITE);
        
        Label mailLable=new Label("xxxx@qq.com");
        mailLable.setLayoutY(100);
        mailLable.setLayoutX(10);
        mailLable.setTextFill(Color.WHITE);
        
        anchorPane.getChildren().addAll(imageView,userImageLabel,userNameLabel,mailLable);
        
        
        JFXListView leftButtons=new JFXListView();
        leftButtons.setPrefWidth(180);
        
        myLove=new Label("我喜欢的音乐",new ImageView(new Image("/fxml/main/icon/love.png",25,25,true,true)));
        
        localMusic=new Label("本地音乐",new ImageView(new Image("/fxml/main/icon/local.png",25,25,true,true)));
        
        recentMusic=new Label("最近播放",new ImageView(new Image("/fxml/main/icon/recent.png",25,25,true,true)));
        
        leftButtons.getItems().addAll(myLove,localMusic,recentMusic);
        
        
        vBox.getChildren().addAll(anchorPane,leftButtons);
        
        setNode(vBox);
        
        
        
    }

    @Override
    public void initEventDefine() {

    }
}
