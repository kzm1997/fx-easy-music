package org.kzm.music.ui.main.center;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.IMainMethod;

/**
 * 播放界面
 */
public class PlayComponent  extends UIObject implements IPlayCenterMethod {
    
    
    private Label singerLabel; //歌手名
    
    private Label songNameLabel; //歌曲名
    
    private Label albumLabel; //专辑名
    
    private Image panDefaultImage; //默认唱片背景
    
    
    
    private IMainMethod main;
    
    
    public PlayComponent(IMainMethod main){
        this.main=main;
    }
    
    
    @Override
    protected void initComponent() {
        
    }

    @Override
    public void initEventDefine() {
          
    }

    @Override
    public UIObject getUIObject() {
        return this;
    }
}
