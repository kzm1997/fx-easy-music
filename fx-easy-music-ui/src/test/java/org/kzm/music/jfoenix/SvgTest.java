package org.kzm.music.jfoenix;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * svg矢量图测试
 */
public  class SvgTest extends BaseTest {


    @Override
    public Node getTest() {
        SVGGlyph icoSvg=createSVGGlyph("M392.448 483.1744H258.0992c-26.5728 0-48.2304-21.6064-48.2304-48.2304V352.4608c0-8.4992 6.8608-15.36 15.36-15.36s15.36 6.8608 15.36 15.36v82.5344c0 9.6256 7.8336 17.5104 17.5104 17.5104h134.2976c9.6256 0 17.5104-7.8336 17.5104-17.5104V264.1408c0-9.6256-7.8336-17.5104-17.5104-17.5104H258.0992c-9.6256 0-17.5104 7.8336-17.5104 17.5104v36.864c0 8.4992-6.8608 15.36-15.36 15.36s-15.36-6.8608-15.36-15.36v-36.864c0-26.5728 21.6064-48.2304 48.2304-48.2304h134.2976c26.5728 0 48.2304 21.6064 48.2304 48.2304v170.8544c0 26.5728-21.6064 48.1792-48.1792 48.1792zM641.9456 269.9264h-122.5728c-8.4992 0-15.36-6.8608-15.36-15.36s6.8608-15.36 15.36-15.36h122.5728c8.4992 0 15.36 6.8608 15.36 15.36s-6.8608 15.36-15.36 15.36zM641.9456 417.024h-122.5728c-8.4992 0-15.36-6.8608-15.36-15.36s6.8608-15.36 15.36-15.36h122.5728c8.4992 0 15.36 6.8608 15.36 15.36s-6.8608 15.36-15.36 15.36zM641.9456 599.04H225.28c-8.4992 0-15.36-6.8608-15.36-15.36s6.8608-15.36 15.36-15.36h416.6656c8.4992 0 15.36 6.8608 15.36 15.36s-6.8608 15.36-15.36 15.36zM641.9456 768.6656H225.28c-8.4992 0-15.36-6.8608-15.36-15.36s6.8608-15.36 15.36-15.36h416.6656c8.4992 0 15.36 6.8608 15.36 15.36s-6.8608 15.36-15.36 15.36z");
        

        //设置标签
        JFXButton aboutButton = new JFXButton("", icoSvg);
        return icoSvg;
    }

    public static SVGGlyph createSVGGlyph(String glyph){

        return createSVGGlyph(glyph, 24, 24, Color.BLACK);

    }

    public static SVGGlyph createSVGGlyph(String glyph, int width, int height, Color color){

        SVGGlyph svgGlyph = new SVGGlyph(0, "", glyph, color);

         svgGlyph.setFill(color); svgGlyph.setSize(width,height);

        return svgGlyph;

    }
}
