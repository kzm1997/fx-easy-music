package org.kzm.music.ui.main.side;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.effects.JFXDepthManager;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.CaseNode;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.side.SideMusicPic;


public class SideComponent extends UIObject {
    
    
    private Label myLove; 
    
    private Label localMusic; //本地音乐
    
    private Label recentMusic; //最近播放

    Label userImageLabel; //头像

    ImageView userView; //头像

    ListView leftButtons; 
    
    private UIObject sideMusicPic;
    

    
    public SideComponent(){
        this.sideMusicPic=new SideMusicPic();
        initComponent();
        initEventDefine();
    }
    
    public ListView getLeftButtons(){
        return leftButtons;
    }
    
    
    
    @Override
    protected void initComponent() {
        VBox vBox=new VBox();
        vBox.getStylesheets().add(getClass().getResource("/fxml/main/css/sideComponent.css").toExternalForm());
        AnchorPane anchorPane=new AnchorPane();
        userView=new ImageView(new Image("/fxml/main/img/user.jpg"));
        userView.setFitWidth(60.0);
        userView.setPreserveRatio(true);
        
        userImageLabel=new Label("",userView);

        Circle circle=new Circle();
        circle.centerXProperty().bind(userImageLabel.widthProperty().divide(2));
        circle.centerYProperty().bind(userImageLabel.heightProperty().divide(2));
        circle.radiusProperty().bind(userImageLabel.widthProperty().divide(2));
        userView.setClip(circle);

        
        userImageLabel.setLayoutY(60);
        userImageLabel.setLayoutX(10);
        JFXDepthManager.setDepth(userImageLabel, 4); //?
        
        Label userNameLabel=new Label("红蜻蜓");
        userNameLabel.setLayoutX(10);
        userNameLabel.setLayoutY(130);
        
        userNameLabel.setTextFill(Color.BLACK);
        
        Label mailLable=new Label("1206960056@qq.com");
        mailLable.setLayoutY(150);
        mailLable.setLayoutX(10);
        mailLable.setTextFill(Color.BLACK);
        
        anchorPane.getChildren().addAll(sideMusicPic.getNode(),userImageLabel,userNameLabel,mailLable);


        leftButtons=new ListView<>();
        leftButtons.setId("leftButtons");
        
        leftButtons.setPrefWidth(220);
        
        myLove=new Label("我喜欢的音乐",new ImageView(new Image("/fxml/main/icon/love.png",25,25,true,true)));
        myLove.getStyleClass().add("select-label");
        
        localMusic=new Label("本地音乐",new ImageView(new Image("/fxml/main/icon/local.png",25,25,true,true)));
        localMusic.getStyleClass().add("select-label");
        recentMusic=new Label("最近播放",new ImageView(new Image("/fxml/main/icon/recent.png",25,25,true,true)));
        recentMusic.getStyleClass().add("select-label");
        
        
        
        leftButtons.getItems().addAll(myLove,localMusic,recentMusic);
        
        
        vBox.getChildren().addAll(anchorPane,leftButtons);
        
        setNode(vBox);
        
    }
    
    
    
    
    @Override
    public void initEventDefine() {
        userImageLabel.setOnMouseEntered(event->{
            RotateTransition rotateTransition=new RotateTransition(Duration.millis(600),userView);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setCycleCount(1);
            rotateTransition.setAutoReverse(false);
            rotateTransition.play();
        });
    }
}
