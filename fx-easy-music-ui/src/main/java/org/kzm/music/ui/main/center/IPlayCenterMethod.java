package org.kzm.music.ui.main.center;

import javafx.scene.Node;
import org.kzm.music.pojo.PlayMusic;
import org.kzm.music.ui.UIObject;

public interface IPlayCenterMethod {


    public UIObject getUIObject();


    /**
     * 加载并播放歌词
     */
    public void  loadLrc(PlayMusic currentMusic);
    


    void setlrcBySlider(double millis);
}
