package org.kzm.music.ui.top;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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


        SVGGlyph resizeMax = new SVGGlyph(0,
                "RESIZE_MAX",
                "M726 810v-596h-428v596h428zM726 44q34 0 59 25t25 59v768q0 34-25 60t-59 26h-428q-34 0-59-26t-25-60v-768q0-34 25-60t59-26z",
                Color.WHITE);

        SVGGlyph close = new SVGGlyph(0,
                "CLOSE",
                "M810 274l-238 238 238 238-60 60-238-238-238 238-60-60 238-238-238-238 60-60 238 238 238-238z",
                Color.WHITE);

        SVGGlyph minus = new SVGGlyph(0,
                "MINUS",
                "M804.571 420.571v109.714q0 22.857-16 38.857t-38.857 16h-694.857q-22.857 0-38.857-16t-16-38.857v-109.714q0-22.857 16-38.857t38.857-16h694.857q22.857 0 38.857 16t16 38.857z",
                Color.WHITE);
        
        maxBtn=new JFXButton();
        maxBtn.getStyleClass().addAll("jfx-decorator-button","top-btn");
        maxBtn.setCursor(Cursor.HAND);
        maxBtn.setRipplerFill(Color.WHITE);
        maxBtn.setGraphic(resizeMax);
        
        
        closeBtn=new JFXButton();
        closeBtn.getStyleClass().addAll("jfx-decorator-button","top-btn");
        closeBtn.setCursor(Cursor.HAND);
        closeBtn.setGraphic(close);
        
        minBtn=new JFXButton();
        minBtn.getStyleClass().addAll("jfx-decorator-button","top-btn");
        minBtn.setCursor(Cursor.HAND);
        minBtn.setGraphic(minus);
        minBtn.setRipplerFill(Color.WHITE);
        
        HBox hBox=new HBox();
        hBox.getStylesheets().addAll(getClass().getResource("/fxml/main/css/top.css").toExternalForm());
        hBox.getChildren().addAll(minBtn,maxBtn,closeBtn);
        hBox.getStyleClass().add("jfx-decorator-buttons-container");
    /*    hBox.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));*/
        hBox.setPadding(new Insets(4));
        hBox.setAlignment(Pos.CENTER_RIGHT);
        
        HBox container=new HBox();
        container.getChildren().addAll(getTopLeft(),hBox);
        container.setStyle("-fx-background-color: rgba(184,207,207,0.5)");
        setNode(container);
        
        
    }
    
    private Node getTopLeft(){
        HBox hbox=new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        HBox.setMargin(hbox, new Insets(0, 8, 0, 8));
        
        //包含logo,名称 搜索
        Image img=new Image("/fxml/..");
        icoImageView=new ImageView(img);
        icoImageView.setFitHeight(24);
        icoImageView.setPreserveRatio(true);

        //标题
        labTitle=new Label("title");
        labTitle.setTextFill(Paint.valueOf("#ffffff"));
        labTitle.setFont(new Font("FangSong",14));
        
        //搜索input
        searchTextField=new TextField();
        searchTextField.setPromptText("搜索单曲音乐");
        searchTextField.setStyle("-fx-background-radius: 15 15 15 15");

        //搜索按钮
        SVGGlyph searchSVGGlyph = new SVGGlyph("M160.021999 409.536004C160.021999 254.345703 286.286107 128.081595 441.476408 128.081595 596.730704 128.081595 722.994813 254.345703 722.994813 409.536004 722.994813 564.726305 596.730704 690.990413 441.476408 690.990413 286.286107 690.990413 160.021999 564.726305 160.021999 409.536004M973.219174 864.867546 766.320105 657.904481C819.180801 588.916793 850.986813 502.970164 850.986813 409.536004 850.986813 183.758115 667.318293 0.089594 441.476408 0.089594 215.698519 0.089594 32.029998 183.758115 32.029998 409.536004 32.029998 635.313893 215.698519 818.982414 441.476408 818.982414 527.743016 818.982414 607.738016 792.104093 673.781889 746.410949L882.728829 955.35789C895.208049 967.83711 911.591026 974.108718 927.974002 974.108718 944.356978 974.108718 960.739954 967.83711 973.219174 955.35789 998.24161 930.335454 998.24161 889.825986 973.219174 864.867546");
        searchSVGGlyph.setSize(16.0);
        searchSVGGlyph.setFill(Color.WHITE);
        
        searchBtn=new JFXButton("",searchSVGGlyph);
        
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
        
        closeBtn.setOnAction(event -> {  //关闭程序
            stage.close();
            System.exit(0);
        });
        
        
    }
    
    private void maximize(){
        
    }
    
    
    
}
