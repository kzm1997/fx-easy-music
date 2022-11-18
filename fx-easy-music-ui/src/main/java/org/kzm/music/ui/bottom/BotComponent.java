package org.kzm.music.ui.bottom;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.kzm.music.ui.UIObject;


public class BotComponent  extends UIObject {
    
    
    private Image panDefaultImage;
    
    private UIObject side;


    Slider soundSlider; //声音滑块

    Label soundNumLabel; //音量数字
    
    
    public BotComponent(){
        initComponent();
        initEventDefine();
    }
    
    
    @Override
    protected void initComponent() {
        HBox hBox=new HBox(92);
       
        
        ImageView stop=new ImageView("/fxml/main/icon/play.png");
        stop.setPreserveRatio(true);
        stop.setPickOnBounds(true);
        stop.setFitWidth(35);
        
        ImageView right=new ImageView("/fxml/main/icon/after.png");
        right.setPreserveRatio(true);
        right.setPickOnBounds(true);
        right.setFitWidth(42);
        
        ImageView left=new ImageView("/fxml/main/icon/before.png");
        left.setPreserveRatio(true);
        left.setPickOnBounds(true);
        left.setFitWidth(42);
        
        ImageView mode=new ImageView("/fxml/main/icon/cycle.png");
        mode.setPreserveRatio(true);
        mode.setPickOnBounds(true);
        mode.setFitWidth(21);
        
        ImageView volume=new ImageView("/fxml/main/icon/word.png");
        volume.setPreserveRatio(true);
        volume.setPickOnBounds(true);
        volume.setFitWidth(18);
        
        ImageView list=new ImageView("/fxml/main/icon/songlist.png");
        list.setPreserveRatio(true);
        list.setPickOnBounds(true);
        list.setFitWidth(25);
        
        ImageView heart=new ImageView("/fxml/main/icon/love.png");
        heart.setPreserveRatio(true);
        heart.setPickOnBounds(true);
        heart.setFitWidth(23);
        
        hBox.getChildren().addAll(stop,right,mode,volume,list,heart);


        soundSlider=new Slider(0,1,0.5);
        soundSlider.setOrientation(Orientation.VERTICAL);
        soundSlider.setMaxHeight(100);
        soundSlider.setTranslateX(-3);
        soundSlider.setTranslateY(-17);
        soundSlider.setOpacity(0.9);
        soundNumLabel=new Label((int)(soundSlider.getValue()*100)+"%");
        soundNumLabel.setTranslateY(42);
        
        
        //声音条
        ImageView soundImage=new ImageView("/fxml/main/img/sound.png");
        soundImage.setFitWidth(40);
        soundImage.setPreserveRatio(true);
        StackPane sound=new StackPane();
        sound.getChildren().addAll(soundImage,soundSlider,soundNumLabel);
        sound.setOpacity(1);
        sound.setTranslateX(303);
        sound.setTranslateY(-520);
        sound.setOpacity(0);
        
        setNode(hBox);
        
    }

    @Override
    public void initEventDefine() {
       soundSlider.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               soundNumLabel.setText((int)newValue.doubleValue()*100+"%");
               
               //todo 根据音量更换图标
           }
       });
    }
}
