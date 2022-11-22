package org.kzm.music.pojo;

import javafx.beans.property.SimpleStringProperty;

public class LocalMusic {

    private SimpleStringProperty id;
    
    private  SimpleStringProperty musicName;
    
    private SimpleStringProperty musicAuthor;
    
    private SimpleStringProperty musicTime;
    
    private SimpleStringProperty musicSize;

    public LocalMusic(SimpleStringProperty id, SimpleStringProperty musicName, SimpleStringProperty musicAuthor, SimpleStringProperty musicTime, SimpleStringProperty musicSize) {
        this.id = id;
        this.musicName = musicName;
        this.musicAuthor = musicAuthor;
        this.musicTime = musicTime;
        this.musicSize = musicSize;
    }

    public String getMusicName() {
        return musicName.get();
    }

    public SimpleStringProperty musicNameProperty() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName.set(musicName);
    }

    public String getMusicAuthor() {
        return musicAuthor.get();
    }

    public SimpleStringProperty musicAuthorProperty() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor.set(musicAuthor);
    }

    public String getMusicTime() {
        return musicTime.get();
    }

    public SimpleStringProperty musicTimeProperty() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime.set(musicTime);
    }

    public String getMusicSize() {
        return musicSize.get();
    }

    public SimpleStringProperty musicSizeProperty() {
        return musicSize;
    }

    public void setMusicSize(String musicSize) {
        this.musicSize.set(musicSize);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
