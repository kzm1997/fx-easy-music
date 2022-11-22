package org.kzm.music.ui;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class UIStage  extends Stage {
    
    protected Node root;


    

    

    
    
    public Node getNode(){
        return root;
    }
    
    
    public double x(){
        return getX();
    }
    
    public double y(){
        return getY();
    }
    
    public double width(){
        return getWidth();
    }
    
    public double height(){
        return getHeight();
    }

    /**
     * 初始化页面
     */
    public abstract  void initView();

    /**
     * 初始化事件定义
     */
    public abstract void initEventDefine();
    
    
}
