package org.kzm.music.ui.main.center;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.side.SideComponent;

public class CenterComponent extends UIObject implements ICenterMethod {
    
    
    
    
    private UIObject side;

    private BorderPane borderPane;
    
    private UIObject center;
    
    private Stage stage;


    @Override
    public UIObject getUIObject() {
        return this;
    }

    public CenterComponent(Stage stage){
        this.side=new SideComponent();
        this.center=new LocalMusicComponent(stage);
        initComponent();
        initEventDefine();
    }
    
    
    @Override
    protected void initComponent() {
        borderPane=new BorderPane();
        borderPane.setLeft(side.getNode());
        borderPane.setCenter(center.getNode());
        setNode(borderPane);
    }

    @Override
    public void initEventDefine() {
        //侧边栏按钮点击
        ListView leftButtons = ((SideComponent) side).getLeftButtons();
        leftButtons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Label label= (Label) newValue;
                switch (label.getText()){
                    case "本地音乐":
                       // borderPane.setCenter();
                }
            }
        });

    }
    
 
}
