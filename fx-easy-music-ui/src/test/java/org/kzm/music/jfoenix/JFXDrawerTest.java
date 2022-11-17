package org.kzm.music.jfoenix;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class JFXDrawerTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane content = new FlowPane();

     /*   JFXButton leftButton = new JFXButton("left");

        JFXButton topButton = new JFXButton("top");

        JFXButton rightButton = new JFXButton("right");*/

        JFXButton bottomButton = new JFXButton("bottom");

        content.getChildren().addAll(bottomButton);

        content.setMaxSize(200, 200);
        

        JFXDrawer bottomDrawer = new JFXDrawer();

        StackPane bottomDrawerPane = new StackPane();

        bottomDrawerPane.getStyleClass().add("deep-purple-400");

        bottomDrawerPane.getChildren().add(new JFXButton("Bottom Content")); 

        bottomDrawer.setDefaultDrawerSize(150);

        bottomDrawer.setDirection(JFXDrawer.DrawerDirection.BOTTOM); //设置方向

        bottomDrawer.setSidePane(bottomDrawerPane); //设置面板

        bottomDrawer.setResizeContent(true);

        bottomDrawer.setOverLayVisible(false);

        bottomDrawer.setResizableOnDrag(true);


        JFXDrawersStack drawersStack = new JFXDrawersStack();

        drawersStack.setContent(content);

        

        bottomDrawer.setId("bottom");
        

        bottomButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(bottomDrawer));

        
        final Scene scene = new Scene(drawersStack, 800, 800);
        
        
        primaryStage.setTitle("JFX Drawer Demo");

        primaryStage.setScene(scene);

        primaryStage.setResizable(true);

        primaryStage.show();
    }
}
