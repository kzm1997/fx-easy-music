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
import javafx.util.Duration;
import org.kzm.music.pojo.PlayMusic;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.component.RXAudioSpectrum;
import org.kzm.music.ui.component.RXMediaProgressBar;
import org.kzm.music.ui.main.IMainMethod;
import org.kzm.music.ui.main.MainController;
import org.kzm.music.ui.main.center.ICenterMethod;
import org.kzm.music.ui.main.center.IPlayCenterMethod;
import org.kzm.music.utils.AnimationUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class BotComponent extends UIObject implements IBottomMethod {


    private UIObject side;

    private JFXButton soundButton;

    private int soundImageFlag = 1; //记录声音按钮是否点击

    private PlayMusic currentPlayMusic; //当前播放音乐

    private StackPane sound;

    private JFXPopup popup;

    private double millis;

    ImageView stop; //播放按钮

    JFXButton button; //底部专辑封面

    private MediaPlayer mediaPlayer;

    private Label playTime;//已经播放时间
    

    private int prevSecond;  //当前播放的时间的前一秒

    private Label totalTime; //歌曲总时间

    private Label musicName; //歌曲名称
    
    private int currentSecond;



    private ArrayList<BigDecimal> lrcList; //存储歌词时间的list

    private Label musicAuthor; //歌手

    private ChangeListener<Duration> changeListener;//播放进度监听器

    private Slider soundSlider; //声音滑块
    
    private RXMediaProgressBar mediaBar=new RXMediaProgressBar();

    private Date date;

    ImageView soundImage; //声音按钮图片

    Label soundNumLabel; //音量数字

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    private IMainMethod main; //主菜单

    private IPlayCenterMethod playView;
    
    private Runnable valueRunnable; //歌曲播放完后的操作
    

    private boolean isPalyViewOpen = false;

    ICenterMethod center;

    public BotComponent(MainController main, ICenterMethod center, IPlayCenterMethod palyCenter) {
        this.main = main;
        this.playView = palyCenter;
        this.center = center;
        initComponent();
        initEventDefine();
    }


    @Override
    public void setPalyView(IPlayCenterMethod play) {
        this.playView = play;
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
        soundImage = new ImageView("/fxml/main/img/sound.png");
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


        HBox downBpx = new HBox(10); //放置播放时间,进度条,歌曲时间
        downBpx.setAlignment(Pos.CENTER);
        mediaBar.setMinWidth(500);
        downBpx.getChildren().addAll(playTime, mediaBar, totalTime);
        vBox.getChildren().addAll(upBpx, downBpx);
        vBox.setStyle("-fx-background-color: green");

        borderPane.setLeft(leftBox);
        borderPane.setCenter(vBox);
         
        date=new Date();
        

        setNode(borderPane);

    }
    
    
    private ChangeListener<Duration> initChangeListener(){
        return new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                currentSecond=(int)newValue.toSeconds();
                if (currentSecond==prevSecond+1){
                    prevSecond++;
                   // date.setTime(mediaPlayer);   
                }
            }
        };
    }

    @Override
    public UIObject getUIObject() {
        return this;
    }

    @Override
    public void initEventDefine() {
        soundSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {;
                soundNumLabel.setText((int)( newValue.doubleValue() * 100) + "%");
                ImageView imageView;
                if ((int)( newValue.doubleValue() * 100) >50){
                     imageView = new ImageView(new Image("/fxml/main/icon/sound_max_black.png", 26, 26, true, true));
                }else if ((int)( newValue.doubleValue() * 100) <50&&(int)( newValue.doubleValue() * 100)!=0){
                     imageView = new ImageView(new Image("/fxml/main/icon/sound_low_black.png", 26, 26, true, true));
                }else{
                     imageView = new ImageView(new Image("/fxml/main/icon/sound_close_black.png", 26, 26, true, true));
                }
                soundButton.setGraphic(imageView);
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
                if (isPalyViewOpen == false) {
                    main.changeCenter(playView.getUIObject());
                } else {
                    main.changeCenter(center.getUIObject());
                }
                isPalyViewOpen = !isPalyViewOpen;
            }
        });
        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                play();
            }
        });
        


    }
    
    
    private void test(){
        PlayMusic playMusic=new PlayMusic();
        playMusic.setMusicName("兰亭序 - 周杰伦");
        playMusic.setMp3Url("file:/d:/CloudMusic/兰亭序%20-%20周杰伦.mp3");
        playMusic.setArtistName("寇政民");
        playMusic.setLocalLrcPath("file:/d:/CloudMusic/兰亭序.lrc");
        this.currentPlayMusic=playMusic;
        
    }

    /**
     * 播放
     */
    @Override
    public void play() {
        test();
        //更换播放图标
        stop.setImage(new Image("/fxml/main/icon/pause_black.png"));

        if (currentPlayMusic == null) {
            return;
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop(); //播放暂停
            try {
                
                mediaPlayer.setOnEndOfMedia(null);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mediaPlayer.dispose();
                mediaPlayer = null;
            }
        }
        //playTime.setText("00:00");
       // totalTime.setText("00:00");
        musicName.setText(currentPlayMusic.getMusicName());
        musicAuthor.setText(currentPlayMusic.getArtistName());

        //todo 歌曲详情页更换歌名歌曲
       

        // todo 歌曲详情页旋转停止
        


        String mp3Url = currentPlayMusic.getMp3Url();

        Media media = new Media(mp3Url);
        mediaPlayer = new MediaPlayer(media);
        
        
        //初始化播放进度条
        initProgressBar(mediaPlayer);
        mediaPlayer.currentTimeProperty().addListener(changeListener);
        

        //todo 歌曲详情旋转开始,详情页磁头旋转
        
        
        
        playView.loadLrc(currentPlayMusic);

      

       
        mediaPlayer.volumeProperty().bind(soundSlider.valueProperty());


        mediaPlayer.setOnEndOfMedia(valueRunnable);

        mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {
                double total_second = Math.floor(mediaPlayer.getTotalDuration().toSeconds());
                date.setTime((long) (total_second * 1000));
                totalTime.setText(simpleDateFormat.format(date));
                new Thread(() -> mediaPlayer.play()).start(); //播放音乐
            }
        });
        
        
    }
    
    
    private Runnable initRunnable(){
        return ()->{
            //核心就是找到下一首歌曲并播放
        };
    }
    

    /**
     * 下一首歌曲
     */
    public void nextMusic(){
        
    }


    private void initProgressBar(MediaPlayer player) {
        mediaBar.setCurrentTime(Duration.ZERO);
        mediaBar.durationProperty().bind(player.getMedia().durationProperty());
        mediaBar.bufferProgressTimeProperty().bind(player.bufferProgressTimeProperty());
        player.currentTimeProperty().addListener((ob1, ov1, nv1) -> {
            mediaBar.setCurrentTime(nv1);
            System.out.println(nv1);
        });

        mediaBar.setOnMouseDragged(event1 -> {
            if ( player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.seek(mediaBar.getCurrentTime());
            }
        });

        mediaBar.setOnMouseClicked(event1 -> {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.seek(mediaBar.getCurrentTime());

            }
        });
    }
    
    
}
