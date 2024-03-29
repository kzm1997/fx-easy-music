package org.kzm.music.utils;

import javafx.animation.*;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class AnimationUtil {

    /**
     * 淡入淡出动画
     *
     * @param node  节点
     * @param time  持续时间
     * @param delay 延时
     * @param from  开始透明度
     * @param to    结束透明度
     */
    public static void fade(Node node, double time, double delay, double from, double to) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(time), node);
        fadeTransition.setInterpolator(Interpolator.LINEAR);
        fadeTransition.setDelay(Duration.seconds(delay));
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.play();
    }

    /**
     * 旋转动画
     *
     * @param node 节点
     * @param time 持续时间为
     * @param fromAngle 起始角度
     * @param toAngle 终点角度
     * @param count 动画次数
     */
    public static void rotate(Node node, double time, double fromAngle, double toAngle, int count) {
        RotateTransition rt = new RotateTransition(Duration.millis(time), node);
        rt.setFromAngle(fromAngle);
        rt.setToAngle(toAngle);
        rt.setCycleCount(count);
        rt.play();
    }
    
    public static void playRotate(ImageView node,int from,int to){
        Rotate rotation=new Rotate();
        rotation.setPivotX(node.xProperty().get()+10);
        rotation.setPivotY(node.yProperty().get()+10);
        node.getTransforms().add(rotation);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), from)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rotation.angleProperty(), to)));
        timeline.play();
        
    }
    
    
    
}
