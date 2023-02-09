package org.kzm.music.pojo;

/**
 * 音乐播放实体类
 */
public class PlayMusic {
    
    private String musicName; //歌曲名称

    private String mp3Url; //地址
    
    private String artistName; //歌手
    
    private String album; //专辑
    
    private String localLrcPath; //歌词地址
    
    private String lrc;


    public PlayMusic() {
        
    }

    public PlayMusic(String musicName, String mp3Url, String artistName, String album, String localLrcPath, String lrc) {
        this.musicName = musicName;
        this.mp3Url = mp3Url;
        this.artistName = artistName;
        this.album = album;
        this.localLrcPath = localLrcPath;
        this.lrc = lrc;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLocalLrcPath() {
        return localLrcPath;
    }

    public void setLocalLrcPath(String localLrcPath) {
        this.localLrcPath = localLrcPath;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }
}
