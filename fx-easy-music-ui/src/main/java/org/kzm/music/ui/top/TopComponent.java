package org.kzm.music.ui.top;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.MainInit;


public class TopComponent extends UIObject{
    
    private JFXButton simpleModeButton; //简单模式
    
    private JFXButton maxBtn; //最大化
    
    private JFXButton closeBtn; //关闭
    
    private JFXButton minBtn; //最小化
    
    private JFXButton searchBtn; //搜索
    
    private ImageView icoImageView; //左侧logo
    
    private String appName;//名称
    
    private Label labTitle;  //标题
    
    private TextField searchTextField;//搜索input
    
    private Label searchTiplabel; //搜索提示


    
    
    
    private Stage stage;
    

    public TopComponent(String appName, Stage stage) {
        this.appName = appName;
        this.stage=stage;
        initComponent();
        initEventDefine();
        
    }

    @Override
    protected void initComponent() {


        maxBtn=new JFXButton();
        maxBtn.getStyleClass().addAll("jfx-decorator-button","top-btn");
        maxBtn.setCursor(Cursor.HAND);
        maxBtn.setRipplerFill(Color.WHITE);
        maxBtn.setGraphic(new ImageView(new Image("/fxml/main/icon/max.png",25,25,true,true)));
        
        
        closeBtn=new JFXButton();
        closeBtn.getStyleClass().addAll("jfx-decorator-button","top-btn");
        closeBtn.setCursor(Cursor.HAND);
        closeBtn.setRipplerFill(Color.WHITE);
        closeBtn.setGraphic(new ImageView(new Image("/fxml/main/icon/close.png",25,25,true,true)));
        
        minBtn=new JFXButton();
        minBtn.getStyleClass().addAll("jfx-decorator-button","top-btn");
        minBtn.setCursor(Cursor.HAND);
        minBtn.setGraphic(new ImageView(new Image("/fxml/main/icon/min.png",25,25,true,true)));
        minBtn.setRipplerFill(Color.WHITE);

        simpleModeButton=new JFXButton();
        simpleModeButton.getStyleClass().addAll("jfx-decorator-button","top-btn");
        simpleModeButton.setCursor(Cursor.HAND);
        simpleModeButton.setGraphic(new ImageView(new Image("/fxml/main/icon/mini.png",25,25,true,true)));
        simpleModeButton.setRipplerFill(Color.WHITE);
        
        HBox hBox=new HBox();
        //hBox.getStylesheets().addAll(getClass().getResource("/fxml/main/css/top.css").toExternalForm());
        hBox.getChildren().addAll(simpleModeButton,minBtn,maxBtn,closeBtn);
        hBox.getStyleClass().add("jfx-decorator-buttons-container");
        hBox.setPadding(new Insets(4));
        hBox.setAlignment(Pos.CENTER_RIGHT);
        
        HBox container=new HBox();
        
        StackPane stackPane=new StackPane();
        
        container.getChildren().addAll(getTopLeft(),hBox);
        BackgroundFill backgroundFill=new BackgroundFill(Color.rgb(236, 65, 65,1),CornerRadii.EMPTY,null);
        container.setBackground(new Background(backgroundFill));
        setNode(container);
        
        
    }
    
    private Node getTopLeft(){
        HBox hbox=new HBox();
        
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        HBox.setMargin(hbox, new Insets(0, 30, 0, 30));
        hbox.setStyle("-fx-spacing:20");
        
        //包含logo,名称 搜索
        Image img=new Image("/fxml/main/icon/logo.png");
        icoImageView=new ImageView(img);
        icoImageView.setFitHeight(30);
        icoImageView.setPreserveRatio(true);
        
        
        
        //标题
        labTitle=new Label("网易云音乐");
        labTitle.setTextFill(Paint.valueOf("#ffffff"));
        labTitle.setFont(new Font("FangSong",20));
        labTitle.setMinWidth(100);
        
        
        //搜索input
        searchTextField=new TextField();
        searchTextField.setPromptText("搜索单曲音乐");
        searchTextField.setStyle("-fx-background-radius: 15 15 15 15");
        searchTextField.setMinWidth(200);

        //搜索按钮
        SVGGlyph searchSVGGlyph = new SVGGlyph("M160.021999 409.536004C160.021999 254.345703 286.286107 128.081595 441.476408 128.081595 596.730704 128.081595 722.994813 254.345703 722.994813 409.536004 722.994813 564.726305 596.730704 690.990413 441.476408 690.990413 286.286107 690.990413 160.021999 564.726305 160.021999 409.536004M973.219174 864.867546 766.320105 657.904481C819.180801 588.916793 850.986813 502.970164 850.986813 409.536004 850.986813 183.758115 667.318293 0.089594 441.476408 0.089594 215.698519 0.089594 32.029998 183.758115 32.029998 409.536004 32.029998 635.313893 215.698519 818.982414 441.476408 818.982414 527.743016 818.982414 607.738016 792.104093 673.781889 746.410949L882.728829 955.35789C895.208049 967.83711 911.591026 974.108718 927.974002 974.108718 944.356978 974.108718 960.739954 967.83711 973.219174 955.35789 998.24161 930.335454 998.24161 889.825986 973.219174 864.867546");
        searchSVGGlyph.setSize(16.0);
        searchSVGGlyph.setFill(Color.WHITE);
        
        searchBtn=new JFXButton("",searchSVGGlyph);
        searchBtn.setCursor(Cursor.HAND);
       
     
        
        searchTiplabel=new Label("");
        
        searchTiplabel.setFont(Font.font("Timer New Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));
        searchTiplabel.setAlignment(Pos.CENTER);
        searchTiplabel.setTextFill(Color.WHITE);
        
        
        
        hbox.getChildren().addAll(icoImageView,labTitle,searchTextField,searchBtn,searchTiplabel);
        
        return hbox;
        
    }
    
    

    @Override
    public void initEventDefine() {
        
        maxBtn.setOnAction(event -> {}); //设置最大化操作
        
        searchBtn.setOnAction(event -> {
            
        });
        minBtn.setOnAction(event -> {
            stage.setIconified(true);
        });
        
        closeBtn.setOnAction(event -> {  //关闭程序
            stage.close();
            System.exit(0);
        });
        
        
    }
    
    private void maximize(){
        minBtn.setOnMouseClicked(event -> {
            stage.setIconified(true);
        });
    }
    
    
    
}
