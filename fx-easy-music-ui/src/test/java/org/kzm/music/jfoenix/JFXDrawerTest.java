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

        JFXDrawer leftDrawer = new JFXDrawer();

        StackPane leftDrawerPane = new StackPane();

        leftDrawerPane.getStyleClass().add("red-400");

        leftDrawerPane.getChildren().add(new JFXButton("Left Content"));

        leftDrawer.setSidePane(leftDrawerPane);

        leftDrawer.setDefaultDrawerSize(150);

        leftDrawer.setResizeContent(true);

        leftDrawer.setOverLayVisible(false);

        leftDrawer.setResizableOnDrag(true);

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

/*        JFXDrawer rightDrawer = new JFXDrawer();

        StackPane rightDrawerPane = new StackPane();

        rightDrawerPane.getStyleClass().add("blue-400");

        rightDrawerPane.getChildren().add(new JFXButton("Right Content"));

        rightDrawer.setDirection(JFXDrawer.DrawerDirection.RIGHT);

        rightDrawer.setDefaultDrawerSize(150);

        rightDrawer.setSidePane(rightDrawerPane); 

        rightDrawer.setOverLayVisible(false);

        rightDrawer.setResizableOnDrag(true);*/

/*        JFXDrawer topDrawer = new JFXDrawer();

        StackPane topDrawerPane = new StackPane();

        topDrawerPane.getStyleClass().add("green-400");

        topDrawerPane.getChildren().add(new JFXButton("Top Content"));

        topDrawer.setDirection(JFXDrawer.DrawerDirection.TOP);

        topDrawer.setDefaultDrawerSize(150);

        topDrawer.setSidePane(topDrawerPane);

        topDrawer.setOverLayVisible(false);

        topDrawer.setResizableOnDrag(true);*/

        JFXDrawersStack drawersStack = new JFXDrawersStack();

        drawersStack.setContent(content);

        leftDrawer.setId("left");

      //  rightDrawer.setId("right");

        bottomDrawer.setId("bottom");

     //   topDrawer.setId("top");

    //    leftButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(leftDrawer));

        bottomButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(bottomDrawer));

    //    rightButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(rightDrawer));

    //    topButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(topDrawer));

        final Scene scene = new Scene(drawersStack, 800, 800);
        


        primaryStage.setTitle("JFX Drawer Demo");

        primaryStage.setScene(scene);

        primaryStage.setResizable(true);

        primaryStage.show();
    }
}
