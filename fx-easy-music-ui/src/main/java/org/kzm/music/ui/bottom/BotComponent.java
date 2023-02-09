package org.kzm.music.ui.bottom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXSlider;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import org.kzm.music.pojo.PlayMusic;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.IMainMethod;
import org.kzm.music.ui.main.MainController;
import org.kzm.music.ui.main.center.ICenterMethod;
import org.kzm.music.ui.main.center.IPlayCenterMethod;


public class BotComponent extends UIObject implements IBottomMethod{

    

    private UIObject side;

    private JFXButton soundButton;

    private int soundImageFlag = 1; //记录声音按钮是否点击
    
    private PlayMusic currentPlayMusic; //当前播放音乐

    private StackPane sound;

    private JFXPopup popup;

    ImageView stop; //播放按钮

    JFXButton button; //底部专辑封面

    private MediaPlayer mediaPlayer;
    
    private Label playTime;//已经播放时间
    
    private Label totalTime; //歌曲总时间
    
    private Label musicName; //歌曲名称
    
    private Label musicAuthor; //歌手
    


    Slider soundSlider; //声音滑块

    Label soundNumLabel; //音量数字

    private IMainMethod main; //主菜单
    
    private IPlayCenterMethod playView;
    
    private boolean isPalyViewOpen=false;

    ICenterMethod center;

    public BotComponent(MainController main, ICenterMethod center, IPlayCenterMethod palyCenter) {
        this.main=main;
        this.playView=palyCenter;
        this.center=center;
        initComponent();
        initEventDefine();
    }


    @Override
    public void setPalyView(IPlayCenterMethod play) {
        this.playView=play;
    }

    
    @Override
    protected void initComponent() {

        BorderPane borderPane;
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-width:1,0,0,0;-fx-border-style:solid;-fx-border-color: #e7e5e5");
        borderPane.setPrefHeight(75);


        HBox leftBox = new HBox();
        HBox.setMargin(leftBox, new Insets(0, 10, 0, 10));
    
        
        StackPane stackPane;
        stackPane = new StackPane();
        stackPane.setPrefWidth(80);
        stackPane.setCursor(Cursor.HAND);
        
        StackPane.setMargin(stackPane, new Insets(5, 5, 5, 5));

        ImageView coverImg = new ImageView(new Image("/fxml/main/img/default.png", 50, 50, true, true));

         button = new JFXButton();
        button.setStyle("-fx-border-style:solid;-fx-border-width: 1;-fx-border-radius: 4;-fx-background-color: rgba(0,0,0,0,0.4)");
        button.setPrefSize(51, 51);

        VBox musicInfoVBox = new VBox();


        musicName = new Label("歌曲名称");
        musicName.setFont(Font.font(18));
        musicName.setStyle("-fx-padding: 5,10,2,30");
        musicName.setPrefWidth(140);
        musicAuthor = new Label("作者");
        musicAuthor.setStyle("-fx-padding: 5,10,2,30");
        musicAuthor.setPrefWidth(140);

        musicInfoVBox.getChildren().addAll(musicName, musicAuthor);
        // VBox.setMargin(musicInfoVBox,new Insets(5,5,5,50));

        leftBox.getChildren().addAll(stackPane, musicInfoVBox);


        stackPane.getChildren().addAll(coverImg, button);

        VBox vBox = new VBox();


        stop = new ImageView("/fxml/main/icon/play_black.png");
        stop.setPreserveRatio(true);
        stop.setPickOnBounds(true);
        stop.setFitWidth(42);
        stop.getStyleClass().addAll("musicButton");

        ImageView right = new ImageView("/fxml/main/icon/after_black.png");
        right.setPreserveRatio(true);
        right.setPickOnBounds(true);
        right.setFitWidth(30);
        right.getStyleClass().addAll("musicButton");


        ImageView left = new ImageView("/fxml/main/icon/before_black.png");
        left.setPreserveRatio(true);
        left.setPickOnBounds(true);
        left.setFitWidth(30);
        left.getStyleClass().addAll("musicButton");

        ImageView mode = new ImageView("/fxml/main/icon/cycle_black.png");
        mode.setPreserveRatio(true);
        mode.setPickOnBounds(true);
        mode.setFitWidth(30);
        mode.getStyleClass().addAll("musicButton");

        ImageView volume = new ImageView("/fxml/main/icon/word_black.png");
        volume.setPreserveRatio(true);
        volume.setPickOnBounds(true);
        volume.setFitWidth(26);
        volume.getStyleClass().addAll("musicButton");

        soundButton = new JFXButton();
        soundButton.setPickOnBounds(true);
        ImageView imageView = new ImageView(new Image("/fxml/main/icon/sound_low_black.png", 26, 26, true, true));
        soundButton.setGraphic(imageView);
        soundButton.getStyleClass().addAll("musicButton");

        ImageView list = new ImageView("/fxml/main/icon/songlist.png");
        list.setPreserveRatio(true);
        list.setPickOnBounds(true);
        list.setFitWidth(30);
        list.getStyleClass().addAll("musicButton");

        ImageView heart = new ImageView("/fxml/main/icon/love.png");
        heart.setPreserveRatio(true);
        heart.setPickOnBounds(true);
        heart.setFitWidth(30);
        heart.getStyleClass().addAll("musicButton");

        //声音滚动条
        soundSlider = new Slider(0, 1, 0.5);
        soundSlider.setOrientation(Orientation.VERTICAL);
        soundSlider.setTranslateX(-3);
        soundSlider.setMaxHeight(100);
        soundSlider.setTranslateY(-17);
        soundSlider.setOpacity(0.9);
        soundNumLabel = new Label((int) (soundSlider.getValue() * 100) + "%");
        soundNumLabel.setTranslateY(42);

        
        //声音按键


        //声音条
        ImageView soundImage = new ImageView("/fxml/main/img/sound.png");
        soundImage.setFitWidth(40);
        soundImage.setPreserveRatio(true);
        sound = new StackPane();
        sound.getChildren().addAll(soundImage, soundSlider, soundNumLabel);
        popup = new JFXPopup();
        popup.setAutoHide(true);
        popup.getScene().setRoot(sound);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        
        HBox upBpx = new HBox(45); //放置上一首,播放,下一首,列表,歌词,音量
        upBpx.getChildren().addAll(mode, left, stop, right, volume, soundButton);
        upBpx.setAlignment(Pos.CENTER);
       

         playTime = new Label("00:00");
         totalTime = new Label("00:00");
        Slider songSlider = new Slider();
        songSlider.setId("songSlider");

        songSlider.setMaxWidth(Double.MAX_VALUE);

        HBox downBpx = new HBox(10); //放置播放时间,进度条,歌曲时间
        downBpx.setAlignment(Pos.CENTER);
        downBpx.getChildren().addAll(playTime, songSlider, totalTime);
        downBpx.setHgrow(songSlider, Priority.ALWAYS);
        vBox.getChildren().addAll(upBpx, downBpx);

        borderPane.setLeft(leftBox);
        borderPane.getStylesheets().addAll(getClass().getResource("/fxml/main/css/bottom.css").toExternalForm());
        borderPane.setCenter(vBox);


        setNode(borderPane);

    }

    @Override
    public UIObject getUIObject() {
        return this;
    }

    @Override
    public void initEventDefine() {
        soundSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                soundNumLabel.setText((int) newValue.doubleValue() * 100 + "%");

                //todo 根据音量更换图标
            }
        });

        soundButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                popup.show(soundButton);
            }
        });
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPalyViewOpen==false){
                    main.changeCenter(playView.getUIObject());
                }else{
                    main.changeCenter(center.getUIObject());
                }
                isPalyViewOpen=!isPalyViewOpen;
            }
        });
        
        

    }

    /**
     * 播放
     */
    @Override
    public void play() {
        
        //更换播放图标
        stop.setImage(new Image("/fxml/main/icon/stop.png"));
        
        if (currentPlayMusic==null){
            return;
        }
        
        if (mediaPlayer!=null){
            mediaPlayer.stop(); //播放暂停
            try {
                mediaPlayer.currentCountProperty().removeListener();
                mediaPlayer.setOnEndOfMedia(null);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                mediaPlayer.dispose();
                mediaPlayer=null;
            }
        }
        playTime.setText("00:00");
        totalTime.setText("00:00");
        musicName.setText(currentPlayMusic.getMusicName());
        musicAuthor.setText(currentPlayMusic.getArtistName());
        
        //todo 歌曲详情页更换歌名歌曲
        
        
        // todo 歌曲详情页旋转停止
        
        
        String mp3Url=currentPlayMusic.getMp3Url();
        
        mediaPlayer=new MediaPlayer(new Media(mp3Url));
        
        //todo 歌曲详情旋转开始
        new Thread(() -> mediaPlayer.play()).start(); //播放音乐
        
        //todo 详情页加载并播放歌词
        
        
        
        
    }
}
