package org.kzm.music.ui.top;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.kzm.music.ui.UIObject;

import javax.swing.text.html.ImageView;

public class TopComponent extends UIObject{
    
    private JFXButton simpleModeButton; //简单模式
    
    private JFXButton maxBtn; //最大化
    
    private JFXButton closeBtn; //关闭
    
    private JFXButton minBtn; //最小化
    
    private JFXButton searchBtn; //搜索
    
    private ImageView icoImageView; //左侧logo
    
    private String appName;//名称
    
    private HBox hBox;


    public TopComponent(String appName) {
        this.appName = appName;
        initComponent();
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
        maxBtn.getStyleClass().add("jfx-decorator-button");
        maxBtn.setCursor(Cursor.HAND);
        maxBtn.setRipplerFill(Color.WHITE);
        maxBtn.setGraphic(resizeMax);
        
        
        closeBtn=new JFXButton();
        closeBtn.getStyleClass().add("jfx-decorator-button");
        closeBtn.setCursor(Cursor.HAND);
        closeBtn.setGraphic(close);
        
        minBtn=new JFXButton();
        minBtn.getStyleClass().add("jfx-decorator-button");
        minBtn.setCursor(Cursor.HAND);
        minBtn.setGraphic(minus);
        minBtn.setRipplerFill(Color.WHITE);
        
        hBox=new HBox();
        hBox.getChildren().addAll(minBtn,maxBtn,closeBtn);
        
        
        setNode(hBox);
        
        
        
    }

    @Override
    public void initEventDefine() {
        
        maxBtn.setOnAction(event -> {}); //设置最大化操作
    }
    
    private void maximize(){
        
    }
    
    
    
}
