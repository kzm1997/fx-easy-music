package org.kzm.music.ui.bottom;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import org.kzm.music.ui.UIObject;


public class BotComponent  extends UIObject {
    
    
    private Image panDefaultImage;
    
    
    @Override
    protected void initComponent() {
        StackPane stackPane=new StackPane();
        panDefaultImage = new Image("/images/topandbottom/pandefault.png");
        
    }

    @Override
    public void initEventDefine() {
       
    }
}
