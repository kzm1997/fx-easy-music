package org.kzm.music.jfoenix.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BlendTest extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Blend blend=new Blend();
        blend.setMode(BlendMode.COLOR_BURN);

        ColorInput colorInpu=new ColorInput();
        colorInpu.setPaint(Color.STEELBLUE);
        colorInpu.setX(10);
        colorInpu.setY(10);
        colorInpu.setWidth(100);
        colorInpu.setHeight(180);
        
        blend.setTopInput(colorInpu);

        Rectangle rect=new Rectangle();
        rect.setWidth(220);
        rect.setHeight(100);
        Stop[] stops=new Stop[]{new Stop(0,Color.LIGHTBLUE),new Stop(1,Color.PALEGREEN)};

        LinearGradient lg=new LinearGradient(0,0,0.25,0.25,true, CycleMethod.REFLECT,stops);
        rect.setFill(lg);
        Text text=new Text();
        text.setX(15);
        text.setY(65);
        text.setFill(Color.PALEGOLDENROD);
        text.setText("COLOR_BURN");
        text.setFont(Font.font(null, FontWeight.BOLD,30));

        Group g=new Group();
        g.setEffect(blend);
        g.getChildren().addAll(rect,text);

        Scene scene=new Scene(g,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
