package org.kzm.music.ui.bottom;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.kzm.music.ui.UIObject;


public class BotComponent  extends UIObject {
    
    
    private Image panDefaultImage;
    
    
    public BotComponent(){
        initComponent();
        initEventDefine();
    }
    
    
    @Override
    protected void initComponent() {
        HBox hBox=new HBox(92);
        ImageView stop=new ImageView("");
        stop.setPreserveRatio(true);
        stop.setPickOnBounds(true);
        stop.setFitWidth(35);
        
        ImageView right=new ImageView("");
        right.setPreserveRatio(true);
        right.setPickOnBounds(true);
        right.setFitWidth(42);
        
        ImageView mode=new ImageView("");
        mode.setPreserveRatio(true);
        mode.setPickOnBounds(true);
        mode.setFitWidth(21);
        
        ImageView volume=new ImageView("");
        volume.setPreserveRatio(true);
        volume.setPickOnBounds(true);
        volume.setFitWidth(18);
        
        ImageView list=new ImageView("");
        list.setPreserveRatio(true);
        list.setPickOnBounds(true);
        list.setFitWidth(25);
        
        ImageView heart=new ImageView("");
        heart.setPreserveRatio(true);
        heart.setPickOnBounds(true);
        heart.setFitWidth(23);
        
        hBox.getChildren().addAll(stop,right,mode,volume,list,heart);
        
        setNode(hBox);
        
    }

    @Override
    public void initEventDefine() {
       
    }
}
