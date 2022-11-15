package org.kzm.music.ui.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import org.kzm.music.ui.UIObject;

import java.io.IOException;

public abstract class MainInit extends UIObject {
    
    private static final String VIEW_NAME="/fxml/main/main.fxml";
    
    
    MainInit(){
        try {
            root= FXMLLoader.load(getClass().getResource(VIEW_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);

        
    }
    
    
    
    
}
