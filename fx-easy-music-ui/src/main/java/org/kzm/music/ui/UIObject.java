package org.kzm.music.ui;

import javafx.scene.Node;

public abstract class UIObject {

    protected Node root;


    public Node getNode(){
        return root;
    }
    
    
    protected abstract void initComponent();
    
    protected void setNode(Node node){
        if (this.root==null){
            this.root=node;
        }
    }
    

    /**
     * 初始化事件定义
     */
    public abstract void initEventDefine();

}
