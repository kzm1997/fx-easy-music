package org.kzm.music.jfoenix;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class SliderTest extends BaseTest {
    @Override
    public Node getTest() {
        Slider slider=new Slider(0,1,0.5);
        Label label=new Label((int)(slider.getValue()*100)+"%");
        slider.setOrientation(Orientation.VERTICAL);
        slider.setMaxHeight(100);
        slider.setOpacity(0.9);
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label.setText((int)(newValue.doubleValue()*100)+"%");
            }
        });
        Group group=new Group();
        group.getChildren().addAll(slider,label);
        
        return group;
    }
}
