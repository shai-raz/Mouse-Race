import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import java.util.Random;

public class GamePanel extends JPanel {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 650;

    private final Element[] elements;
    private final Point mousePos;
    private boolean gameOver;

    public GamePanel() {
        setBackground(Color.WHITE);

        mousePos = new Point(0, 0);
        elements = new Element[3];
        gameOver = false;

        MouseHandler mouseHandler = new MouseHandler();
        addMouseMotionListener(mouseHandler);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        repaint();
    }

    public Point getMousePos() {
        return mousePos;
    }

    public Element[] getElements() {
        return elements;
    }

    public void initElements() {
        Random rand = new Random();

        for (int i = 0; i < elements.length; i++) {
            int size = rand.nextInt(50) + 50;
            int speed = rand.nextInt(4) + 2;

            // random position between 10 and h-10 based on size
            int x = rand.nextInt(WIDTH - size - 30) + 10 + size;
            int y = rand.nextInt(HEIGHT - size - 30) + 10 + size;
            Point pos = new Point(x, y);

            // random type
            switch (i % 3) {
                case 0:
                    elements[i] = new Chaser(pos, size, speed);
                    break;
                case 1:
                    elements[i] = new Escaper(pos, size, speed);
                    break;
                case 2:
                    elements[i] = new Randomer(pos, size, speed);
                    break;
            }
        }

        repaint();
    }

    public void moveElements() {
        for (Element element : elements) {
            if (element != null)
                element.move(mousePos);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 72));
            g.drawString("Game Over", WIDTH / 2 - 172, HEIGHT / 2);
            return;
        }

        // draw each element using its unique draw method
        for (Element element : elements) {
            if (element != null)
                element.draw(g);
        }
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            mousePos.setX(e.getX());
            mousePos.setY(e.getY());
        }
    }
}
