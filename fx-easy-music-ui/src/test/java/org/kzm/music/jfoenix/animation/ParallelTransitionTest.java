package org.kzm.music.jfoenix.animation;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 并行播放一系列动画
 */
public class ParallelTransitionTest extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rect=new Rectangle(100,40,100,100);  
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);
        
        final Duration SEC_2=Duration.millis(2000);
        final Duration SEC_3=Duration.millis(3000);

        FadeTransition ft=new FadeTransition(SEC_3);
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);
        ft.setAutoReverse(true);

        TranslateTransition tt=new TranslateTransition(SEC_2);
        tt.setFromX(-100f);
        tt.setToX(100f);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);

        RotateTransition rt=new RotateTransition(SEC_3);
        rt.setByAngle(180f);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);

        ScaleTransition st=new ScaleTransition(SEC_2);
        st.setByX(1.5f);
        st.setByY(1.5f);
        st.setCycleCount(2);
        st.setAutoReverse(true);

        ParallelTransition pt=new ParallelTransition(rect,ft,tt,rt,st);
        pt.play();

        Group group=new Group(rect);

        Scene scene=new Scene(group,400,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
    }
}
