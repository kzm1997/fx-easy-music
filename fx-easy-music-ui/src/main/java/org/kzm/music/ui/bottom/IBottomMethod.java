package org.kzm.music.ui.bottom;

import javafx.scene.Node;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.center.IPlayCenterMethod;

public interface IBottomMethod {
    
    
    public void setPalyView(IPlayCenterMethod play);
    
    
    public UIObject getUIObject();
    
    
    public  void play();
}
