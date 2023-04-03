package org.kzm.music.ui.main.center;

import org.kzm.music.pojo.PlayMusic;
import org.kzm.music.ui.UIObject;

public interface IPlayCenterMethod {


    public UIObject getUIObject();


    /**
     * 加载并播放歌词
     */
    public void  loadLrc(PlayMusic currentMusic);
    


    void setlrcBySlider(double millis);
    
    
    public void play(PlayMusic currentMusic);
    
    public void stop(PlayMusic currentMusic);

    void setWave(float[] magnitudes, double currentVolume);
    
}
