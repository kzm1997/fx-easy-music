package org.kzm.music.ui.main.center;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.kzm.music.ui.UIObject;
import org.kzm.music.ui.main.side.SideComponent;

public class CenterComponent extends UIObject {
    
    
    private UIObject side;
    
    
    public CenterComponent(){
        this.side=new SideComponent();
        initComponent();
    }
    
    
    @Override
    protected void initComponent() {
        BorderPane borderPane=new BorderPane();
        borderPane.setLeft(side.getNode());
        borderPane.setCenter(new Button("center"));
        setNode(borderPane);
    }

    @Override
    public void initEventDefine() {

    }
}
