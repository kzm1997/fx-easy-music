package org.kzm.music.ui.main.center;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.kzm.music.pojo.PlayMusic;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.IMainMethod;
import org.kzm.music.utils.AnimationUtil;
import org.kzm.music.utils.LocalMusicUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 播放界面
 */
public class PlayComponent  extends UIObject implements IPlayCenterMethod {
    
    
    private Label singerLabel; //歌手名
    
    private Label songNameLabel; //歌曲名
    
    private Label albumLabel; //专辑名
    
    private Image panDefaultImage; //默认唱片背景
    
    private ImageView panImageView;


    
    private RotateTransition rotateTransition;
    
    private List<BigDecimal> lrcList; //歌曲时间list
    
    private int currentLrcIndex=0;
    
    private ImageView rodImageView;
    
    private HBox h4;//内容容器
    
    private Label sNLab;

    ImageView logolight; // 光晕
    
    private Label siLab;
    
    private VBox lrcVBox;

    private Timeline t1;

    private double min = 0;

    private double max = 0;

    // 频谱矩形条数
    private final int waveNum = 96;
    Rectangle[] wave = new Rectangle[waveNum];
    HBox wavebox = new HBox(5);

    private final Font font = Font.font("楷体", 14);
    private final Font boldFont = Font.font("Timer New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18);
    
    private IMainMethod main;
    
    
    public PlayComponent(IMainMethod main){
        this.main=main;
        initComponent();
    }
    
    
    
    
    
    @Override
    protected void initComponent() {


        t1 = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            lrcVBox.setLayoutY(lrcVBox.getLayoutY() - 9);  //歌词刷新
        }));
        t1.setCycleCount(5);// 执行1次

        
        Circle circle=new Circle();
        circle.setRadius(174);
        circle.setLayoutX(320);
        circle.setCenterX(320);
        circle.setFill(Color.valueOf("#dedfde"));
        
        StackPane musicDIskStack = new StackPane(); //唱片容器
        ImageView musicDisk = new ImageView("/fxml/main/img/pan.png");//圆形唱片
        musicDisk.setFitHeight(320);
        musicDisk.setFitWidth(320);
        //4.碟片的图片
        panImageView = new ImageView("/fxml/main/img/user.jpg"); //唱片的专辑图片
        panImageView.setFitHeight(220);
        panImageView.setFitWidth(220);
        Label panLabel = new Label("", panImageView);
        panLabel.getStyleClass().add("hoverNode");
        Circle circle2 = new Circle();
        circle2.centerXProperty().bind(panLabel.widthProperty().divide(2));
        circle2.centerYProperty().bind(panLabel.heightProperty().divide(2));
        circle2.radiusProperty().bind(panLabel.widthProperty().divide(2));
        panImageView.setClip(circle2); //将唱片的专辑图片改为圆形

        
        musicDIskStack.getChildren().addAll(circle,musicDisk,panLabel); 
        musicDIskStack.setLayoutX(80);
        musicDIskStack.setLayoutY(115);
       // musicDIskStack.setStyle("-fx-background-color: #cdcd37");

        rotateTransition = new RotateTransition(Duration.seconds(40), panImageView); //专辑图片增加旋转效果
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        // 无限循环
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        // 每次旋转后是否改变旋转方向
        rotateTransition.setAutoReverse(false);
        // RotateTransition旋转方式两轮旋转间有间隔

        rodImageView = new ImageView("/fxml/main/img/rodImageView.png");
        rodImageView.setFitWidth(110);
        rodImageView.setFitHeight(165);
        rodImageView.setLayoutX(225);
        rodImageView.setLayoutY(25);

        Rotate rotate2=new Rotate(-35,rodImageView.xProperty().get()+10,rodImageView.yProperty().get()+10);

        rodImageView.getTransforms().add(rotate2);

        sNLab = new Label();
        sNLab.setLayoutX(600);
        sNLab.setLayoutY(55.0);
        sNLab.setPrefWidth(340.0);
        sNLab.setFont(new Font("黑体", 18));
        sNLab.setTextFill(Color.WHITE);
        sNLab.getStyleClass().add("shadowLabel");

        siLab = new Label("歌手");
        siLab.setLayoutX(660);
        siLab.setLayoutY(90);
        siLab.setPrefWidth(140.0);
        siLab.setTextFill(Color.WHITE);
        siLab.getStyleClass().add("shadowLabel");

        albumLabel = new Label("专辑：");
        albumLabel.setLayoutX(520);
        albumLabel.setLayoutY(90);
        albumLabel.setPrefWidth(140.0);
        albumLabel.setTextFill(Color.WHITE);
        albumLabel.getStyleClass().add("shadowLabel");


        lrcVBox = new VBox(15);
        lrcVBox.setPadding(new Insets(20, 20, 20, 20));
        lrcVBox.setLayoutX(-20);
        lrcVBox.setLayoutY(100);
        lrcVBox.setAlignment(Pos.TOP_LEFT);
        //lrcVBox.setBackground(new Background(new BackgroundFill(Color.color(0, 0, 0, 0), new CornerRadii(15), null)));
        
        AnchorPane lrcPane = new AnchorPane();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPadding(new Insets(0, 0, 0, 0));
        scrollPane.setContent(lrcPane);
        scrollPane.setMouseTransparent(true);// 不接收鼠标事件,避免对其他控件的点击造成阻碍

        lrcPane.prefWidthProperty().bind(scrollPane.widthProperty());
        lrcPane.prefHeightProperty().bind(scrollPane.heightProperty());
        lrcPane.setStyle("-fx-background-color: transparent;");
        lrcPane.getChildren().addAll(lrcVBox);
        scrollPane.setPrefSize(520.0, 396.0);
        scrollPane.setLayoutX(500.0);
        scrollPane.setLayoutY(130.0);
        
        
        // 频谱初始化
        for (int i = 1; i < waveNum; i++) {

            wave[i] = new Rectangle();

            wave[i].setWidth(4);
            wave[i].setHeight(0);
            wave[i].setArcHeight(8);

            wave[i].setArcWidth(8);
            wave[i].setFill(Color.BLANCHEDALMOND);
            wavebox.getChildren().addAll(wave[i]);
        }
        wavebox.setMouseTransparent(true);
        wavebox.setOpacity(0.9);
        wavebox.setAlignment(Pos.TOP_LEFT);

        // 光源效果
        wavebox.setEffect(new Lighting());
        wavebox.setLayoutY(400);
        
/*        wavebox.setRotationAxis(new Point3D(1,0,0));
        wavebox.setRotate(-180);*/
        
        lrcList = new ArrayList<>();



        h4 = new HBox();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinWidth(1025);
        anchorPane.getChildren().addAll(musicDIskStack, rodImageView, scrollPane, sNLab, siLab, albumLabel,wavebox);

        h4.getChildren().addAll( anchorPane);
        /*把自定义布局放到hbox里面，设置居中对齐，让自定义布局在整体上始终展示在正中央*/
        h4.setAlignment(Pos.CENTER);
        h4.getStyleClass().add("bag2Node");
        
        setNode(h4);
        h4.getStylesheets().addAll(getClass().getResource("/fxml/main/css/layComonent.css").toExternalForm());
    }

    @Override
    public void initEventDefine() {
          
    }

    @Override
    public UIObject getUIObject() {
        
        
        return this;
    }

    @Override
    public void loadLrc(PlayMusic currentMusic) {
        if (currentMusic==null){
            return;
        }
        //初始化
        this.lrcVBox.getChildren().clear();
        this.lrcVBox.setLayoutY(60);
        this.lrcList.clear();
        this.currentLrcIndex=0;
        String[] musicLrcList;
        String lrcString;

        lrcString=currentMusic.getLrc();
        //歌词文件
        if (currentMusic.getLrc()==null){
            String localLrcPath = currentMusic.getLocalLrcPath();
            lrcString= LocalMusicUtils.getLrc(localLrcPath);
        }
        musicLrcList=lrcString.split("\n");

        for (String row : musicLrcList) {
            if (!row.contains("[")||!row.contains("]")){
                continue;
            }
            if (row.charAt(1)<'0'||row.charAt(1)>'9'){
                continue;
            }
            String strTime=row.substring(1,row.indexOf("]"));
            String strMinute = strTime.substring(0, strTime.indexOf(":"));
            String strSecond = strTime.substring(strTime.indexOf(":") + 1);
            
            BigDecimal totalMilli=null;

            int intMinute = Integer.parseInt(strMinute);
            
            // 换算成毫秒
            totalMilli = new BigDecimal(intMinute * 60).add(new BigDecimal(strSecond))
                    .multiply(new BigDecimal("1000"));

            this.lrcList.add(totalMilli);
            Label lab = new Label(row.trim().substring(row.indexOf("]") + 1));
            lab.setPrefWidth(380);
            lab.setPrefHeight(30);
            lab.setTextFill(Color.BLACK);
            lab.setAlignment(Pos.TOP_LEFT);
            this.lrcVBox.getChildren().add(lab);
        }
        
    }

    @Override
    public void setlrcBySlider(double millis) {
        
        //判断此次是否在正常的播放区间
        min = 0;
        max = 0;

        if (lrcList.size() == 0) {
            return;
        }
        // 判断当前时间是否在正常区间
        if (currentLrcIndex == 0) {
            min = 0;
        } else {
            min = lrcList.get(currentLrcIndex).doubleValue();
        }
        if (currentLrcIndex != lrcList.size() - 1) {
            max = lrcList.get(currentLrcIndex + 1).doubleValue();
        } else {
            max = lrcList.get(currentLrcIndex).doubleValue();
        }

        if (millis >= min && millis < max) {
            return;
        }
        if (currentLrcIndex < lrcList.size() - 1 && millis >= lrcList.get(currentLrcIndex+ 1).doubleValue()) {
            currentLrcIndex++;

            // lrcvbox上移
            // 如果是正常播放下去,就展示动画
            if (millis - lrcList.get(currentLrcIndex).doubleValue() < 100) {
                t1.play();
            }
            // 如果是拖动导致的时间推后,直接下一行
            else {
                lrcVBox.setLayoutY(lrcVBox.getLayoutY() - 45);
            }

            Label lab_current = (Label) lrcVBox.getChildren().get(currentLrcIndex);
            lab_current.setFont(boldFont);
            //lab_current.getStyleClass().add("shadowLabel");

            Label lab_Pre_1 = (Label) lrcVBox.getChildren().get(currentLrcIndex - 1);
            if (lab_Pre_1 != null) {
                lab_Pre_1.setFont(font);
                lab_Pre_1.getStyleClass().removeAll("shadowLabel");
            }
            if (currentLrcIndex + 1 < lrcList.size()) {
                Label lab_next_1 = (Label) lrcVBox.getChildren().get(currentLrcIndex + 1);
                lab_next_1.setFont(font);
                lab_next_1.getStyleClass().removeAll("shadowLabel");
            }
        } else if (currentLrcIndex > 0 && millis < lrcList.get(currentLrcIndex).doubleValue()) {
            // 进度条回拉
            currentLrcIndex--;

            lrcVBox.setLayoutY(lrcVBox.getLayoutY() + 45);

            Label lab_current = (Label) lrcVBox.getChildren().get(currentLrcIndex);
            lab_current.setFont(boldFont);
          //  lab_current.getStyleClass().add("shadowLabel");

            if (currentLrcIndex - 1 >= 0) {
                Label lab_Pre_1 = (Label) lrcVBox.getChildren().get(currentLrcIndex - 1);
                lab_Pre_1.setFont(font);
                lab_Pre_1.getStyleClass().removeAll("shadowLabel");
            }

            if (currentLrcIndex+ 1 < lrcVBox.getChildren().size()) {
                Label lab_next_1 = (Label) lrcVBox.getChildren().get(currentLrcIndex+ 1);
                lab_next_1.setFont(font);
                lab_next_1.getStyleClass().removeAll("shadowLabel");
            }
        }
    }

    @Override
    public void play(PlayMusic currentMusic) {
        //填写歌曲信息
        sNLab.setText(currentMusic.getMusicName());
        siLab.setText(currentMusic.getArtistName());
        loadLrc(currentMusic);
        
        //图片旋转
        if (rotateTransition.getStatus() != Animation.Status.RUNNING) {
            rotateTransition.play();
        }
        //详情页磁头旋转
        AnimationUtil.playRotate(rodImageView, 0,30);
     
    }

    @Override
    public void stop(PlayMusic currentMusic) {
        //详情页磁头旋转
        rotateTransition.stop();
        AnimationUtil.playRotate(rodImageView, 30,0);
    }

    @Override
    public void setWave(float[] magnitudes, double currentVolume) {

        for (int i = 1; i < waveNum; i++) {
            float f= Math.abs(magnitudes[i]);
            double currentHight=currentVolume - (currentVolume / (60 / f));
            double oldHigth=wave[i].getHeight();
            wave[i].setLayoutY(wave[i].getHeight()-(currentHight-oldHigth));
            wave[i].setHeight(currentHight);
        }
    }
}
