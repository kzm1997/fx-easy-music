package org.kzm.music.ui.side;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.kzm.music.ui.UIObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SideMusicPic extends UIObject {

    private static final int ANIMATION_SECONDS = 4;

    private Random random = new Random();

    private Digit[] digits = new Digit[5];
    private int currentIndex = -1;

    private GraphicsContext g;
    private double time = 0;

    private List<Particle> particles = new ArrayList<>();
    private int maxParticles = 0;

    public SideMusicPic() {
        initComponent();
    }

    private Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(220, 190);
        root.setStyle("-fx-background-color: #e7e5e5");
        Canvas canvas = new Canvas(220, 190);
        g = canvas.getGraphicsContext2D();
        //g.setFill(Color.RED);

        root.getChildren().add(canvas);

        populateDigits();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void onUpdate() {
        g.clearRect(0, 0, 220, 190);

        if (time == 0 || time > ANIMATION_SECONDS) {  // 新的数字开始 
            currentIndex++;
            if (currentIndex == 5) {
                currentIndex = 0;
            }
            particles.clear();
            for (int i = 0; i < digits[currentIndex].positions.size(); i++) {
                List<MyPoint2D> positions = digits[currentIndex].positions;
                //随机初始位置
                int x= (int) (Math.random()*220);
                int y=(int) (Math.random()*190);
                Particle particle = new Particle(i, positions.get(i).color);
                particle.x=x;
                particle.y=-y;
                particles.add(particle);
            }
            
            particles.parallelStream().forEach(p -> {
                if (p.index < digits[currentIndex].positions.size()) {  // 粒子还没有完全
                    Point2D point = digits[currentIndex].positions.get(p.index);

                    // offset to center of screen
                    p.nextX = point.getX() + 110;  // 设置终点位置
                    p.nextY = point.getY() +20;
                }else{

                }

                p.dx = p.nextX - p.x;
                p.dy = p.nextY - p.y;
                p.dx /= ANIMATION_SECONDS * 60 * random.nextDouble();
                p.dy /= ANIMATION_SECONDS * 60 * random.nextDouble();
            });


            time = 0;
        }

        for (Particle p : particles) {
            p.update();
            g.setFill(p.color);
            p.render(g);
        }

        time += 0.015;
    }


    private void populateDigits() {
        for (int i = 0; i < digits.length; i++) {
            digits[i] = new Digit();

            Text text = new Text(i + "");
            text.setFont(Font.font(144));
            text.setFill(Color.BLACK);

            Image image = new Image("/fxml/main/icon/music" + i + ".png");


            Image snapshot = text.snapshot(null, null);  // 把当前text保存为图片 后面将图片像素拆分成粒子 

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (!image.getPixelReader().getColor(x, y).equals(Color.TRANSPARENT)) {  // 是数字的一部分
                        int pixel=image.getPixelReader().getArgb(x,y);
                        int red = ((pixel >> 16) & 0xff);
                        int green = ((pixel >> 8) & 0xff);
                        int blue = (pixel & 0xff);
                        digits[i].positions.add(new MyPoint2D(x, y,Color.rgb(red,green,blue)));
                    }
                }
            }

            if (digits[i].positions.size() > maxParticles) {  // 保持 maxParticle 最大 
                maxParticles = digits[i].positions.size();
            }
        }
    }



    private static class Digit {
        private List<MyPoint2D> positions = new ArrayList<>();
    }

    class MyPoint2D extends Point2D {
        private Color color;

        public MyPoint2D(double x, double y, Color color) {
            super(x, y);
            this.color = color;
        }
    }

    private static class Particle {
        private double x, y; //当前坐标
        private double nextX, nextY;//终点
        private double dx, dy; //步长
        private int index;
        private Color color;

        public Particle(int index, Color color) {
            this.index = index;
            this.color=color;
        }

        void update() {
            // 判断当前坐标距离最终的位置向量  x ---> nextX 
            if (Math.abs(nextX - x) > Math.abs(dx)) {
                x += dx;
            } else {
                x = nextX;
            }

            if (Math.abs(nextY - y) > Math.abs(dy)) {
                y += dy;
            } else {
                y = nextY;
            }
        }

        void render(GraphicsContext g) {
            Point2D center = new Point2D(90, 100);
            double alpha = 1 - new Point2D(x, y).distance(center) / 500;

            g.setGlobalAlpha(alpha);
            g.setFill(color);
            g.fillOval(x, y, 2, 2);
        }
    }
    

    @Override
    protected void initComponent() {
        Parent content = createContent();
        setNode(content);
    }

    @Override
    public void initEventDefine() {

    }
    
    
    
}
