package org.kzm.music;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kzm.music.ui.main.IMainMethod;
import org.kzm.music.ui.main.MainController;

public class Main extends Application {
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        IMainMethod main=new MainController();
        main.doShow();
    }
    
    
}
 
