
package gui;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;






public class SnowAnimationFrame extends JFrame {

    private final SnowPanel snowPanel;

    public SnowAnimationFrame() {
        setTitle("Hello Winter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        snowPanel = new SnowPanel();
        add(snowPanel);

        Timer timer = new Timer(16, e -> snowPanel.updateSnowflakes());
        timer.start();
    }

    private static class SnowPanel extends JPanel {

        private final List<Snowflake> snowflakes = new ArrayList<>();
        private final Random random = new Random();

        public SnowPanel() {
            setBackground(new Color(10, 10, 50));
            generateSnowflakes(50);
        }

        private void generateSnowflakes(int count) {
            for (int i = 0; i < count; i++) {
                snowflakes.add(new Snowflake(random.nextInt(800), random.nextInt(600), random.nextInt(3) + 1));
            }
        }

        public void updateSnowflakes() {
            for (Snowflake snowflake : snowflakes) {
                snowflake.y += snowflake.speed;
                snowflake.rotation += snowflake.rotationSpeed;
                if (snowflake.y > getHeight()) {
                    snowflake.y = -50;
                    snowflake.x = random.nextInt(getWidth());
                }
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            
            GradientPaint gradient = new GradientPaint(0, 0, new Color(20, 20, 60), 0, getHeight(), new Color(0, 0, 30));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            for (Snowflake snowflake : snowflakes) {
                AffineTransform oldTransform = g2d.getTransform();
                g2d.translate(snowflake.x, snowflake.y);
                g2d.rotate(Math.toRadians(snowflake.rotation));
                drawSnowflake(g2d);
                g2d.setTransform(oldTransform);
            }

            g2d.setFont(new Font("Serif", Font.BOLD, 48));
            g2d.setColor(new Color(255, 215, 0));
            g2d.drawString("Hello Winter!", 250, 300);
        }

        private void drawSnowflake(Graphics2D g2d) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2));
            for (int i = 0; i < 6; i++) {
                g2d.drawLine(0, 0, 10, 30);
                g2d.drawLine(10, 30, 5, 20);
                g2d.drawLine(10, 30, 15, 20);
                g2d.rotate(Math.toRadians(60));
            }
        }

        private static class Snowflake {
            int x, y, speed;
            double rotation, rotationSpeed;

            public Snowflake(int x, int y, int speed) {
                this.x = x;
                this.y = y;
                this.speed = speed;
                this.rotation = 0;
                this.rotationSpeed = Math.random() * 2 - 1;
            }
        }
    }
}
