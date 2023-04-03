package org.kzm.music.ui.main.center;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.kzm.music.pojo.PlayMusic;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.IMainMethod;
import org.kzm.music.utils.LocalMusicUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
    
    private Label siLab;
    
    private VBox lrcVBox;

    private Timeline t1;

    private double min = 0;

    private double max = 0;



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

        rotateTransition = new RotateTransition(Duration.seconds(60), panImageView); //专辑图片增加旋转效果
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
        rodImageView.getTransforms().add(new Translate(0,100));
        rodImageView.setLayoutX(210);
        rodImageView.setLayoutY(-90);

        sNLab = new Label();
        sNLab.setLayoutX(300.0);
        sNLab.setLayoutY(35.0);
        sNLab.setPrefWidth(160.0);
        sNLab.setFont(new Font("黑体", 18));
        sNLab.setTextFill(Color.WHITE);
        sNLab.getStyleClass().add("shadowLabel");

        siLab = new Label("歌手");
        siLab.setLayoutX(480);
        siLab.setLayoutY(70);
        siLab.setPrefWidth(140.0);
        siLab.setTextFill(Color.WHITE);
        siLab.getStyleClass().add("shadowLabel");

        albumLabel = new Label("专辑：");
        albumLabel.setLayoutX(300);
        albumLabel.setLayoutY(70);
        albumLabel.setPrefWidth(140.0);
        albumLabel.setTextFill(Color.WHITE);
        albumLabel.getStyleClass().add("shadowLabel");

    
        lrcVBox = new VBox(15);  //歌词的listview容器
        lrcVBox.setPadding(new Insets(20, 20, 20, 20));
        lrcVBox.setLayoutX(10);
        lrcVBox.setLayoutY(60);
        lrcVBox.setBackground(new Background(new BackgroundFill(Color.color(0.5, 0.5, 0.5, 0.3), new CornerRadii(15), null)));

        AnchorPane lrcPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        /*隐藏水平和垂直滚动条*/
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPadding(new Insets(0, 0, 0, 0));
        scrollPane.setContent(lrcPane);
        scrollPane.setMouseTransparent(true);/*使ScrollPane不接收鼠标事件*/

        lrcPane.prefWidthProperty().bind(scrollPane.widthProperty());
        lrcPane.prefHeightProperty().bind(scrollPane.heightProperty());
        lrcPane.setStyle("-fx-background-color: transparent;");
        lrcPane.getChildren().addAll(lrcVBox);
        scrollPane.setPrefSize(400.0, 400.0);
        scrollPane.setLayoutX(500.0);
        scrollPane.setLayoutY(100.0);

        Paint paint = Paint.valueOf("#2B2B2B");

        SVGGlyph svgGlyph = new SVGGlyph("M-45.3,472l5.2-5.2c0.1-0.1,0.2-0.1,0.3,0l1,1c0.1,0.1,0.1,0.2,0,0.3l-5.2,5.2h3.8c0.2,0,0.4,0.2,0.4,0.4v1.2c0,0.1-0.1,0.2-0.2,0.2h-6.3c-0.4,0-0.8-0.4-0.8-0.8V468c0-0.1,0.1-0.2,0.2-0.2h1.2c0.2,0,0.4,0.2,0.4,0.4V472z M-28.7,458l-5.2,5.2c-0.1,0.1-0.2,0.1-0.3,0c0,0,0,0,0,0l-1-1c-0.1-0.1-0.1-0.2,0-0.3c0,0,0,0,0,0l5.2-5.2h-3.8c-0.2,0-0.4-0.2-0.4-0.4v-1.2c0-0.1,0.1-0.2,0.2-0.2h6.3c0.4,0,0.8,0.4,0.8,0.8v6.3c0,0.1-0.1,0.2-0.2,0.2h-1.2c-0.2,0-0.4-0.2-0.4-0.4C-28.7,461.8-28.7,458-28.7,458z", paint);
        svgGlyph.setSize(20.0);

        JFXButton button = new JFXButton("2222");
     /*   button.setOnAction(event -> {
            if (jfxDrawer.getDefaultDrawerSize() < mainStage.getHeight()) {
                jfxDrawer.setDefaultDrawerSize(mainStage.getHeight());
            }
            if (jfxDrawer.isClosed()) {
                jfxDrawer.open();
            } else {
                jfxDrawer.close();
            }
        });*/

        button.setLayoutX(640);
        button.setLayoutY(20);
        Border border = new Border(new BorderStroke(
                paint, paint, paint, paint,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                new CornerRadii(1), new BorderWidths(2),
                new Insets(1, 1, 1, 1)
        ));
        button.setBorder(border);



        AnchorPane anchorPane = new AnchorPane();
       // anchorPane.setStyle("-fx-background-color: pink");
        anchorPane.setMinWidth(1025);
        anchorPane.getChildren().addAll(button, musicDIskStack, rodImageView, scrollPane, sNLab, siLab, albumLabel);

         h4 = new HBox();

        h4.getChildren().addAll( anchorPane);
        /*把自定义布局放到hbox里面，设置居中对齐，让自定义布局在整体上始终展示在正中央*/
        h4.setAlignment(Pos.CENTER);


        
        
        
        setNode(h4);
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
            lab_current.getStyleClass().add("shadowLabel");

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
            lab_current.getStyleClass().add("shadowLabel");

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
        
    }

    @Override
    public void stop(PlayMusic currentMusic) {

    }
}
