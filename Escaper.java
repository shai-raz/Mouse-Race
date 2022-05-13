import java.awt.Graphics;
import java.util.Random;

public class Escaper extends Element {
    public Escaper(Point pos, int size, int speed) {
        super(pos, size, speed);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        // circle
        g.fillOval(getX(), getY(), getSize(), getSize());
    }

    /**
     * Checks if the element is touching the mouse,
     * if it does, set a random new position to it.
     * 
     * @return true if the element is touching the mouse
     */
    @Override
    public boolean hitTarget(Point mousePos) {
        boolean isHit = super.hitTarget(mousePos);
        if (isHit) {
            Random rand = new Random();
            int size = getSize();
            int x = rand.nextInt(GamePanel.WIDTH - size - 30) + 10 + size;
            int y = rand.nextInt(GamePanel.HEIGHT - size - 30) + 10 + size;
            setX(x);
            setY(y);
        }

        return isHit;
    }

    /**
     * Moves the element away from the mouse position.
     * 
     * @param Point the x,y coordinates of the mouse
     */
    public void move(Point... point) {
        int elementX = getX();
        int elementY = getY();
        int elementX2 = elementX + getSize();
        int elementY2 = elementY + getSize();

        // check wall collision
        Wall wallHit = hitWall(elementX, elementY, elementX2, elementY2);
        if (wallHit != null) {
            switch (wallHit) {
                case LEFT:
                    setX(elementX + getSpeed());
                    return;
                case RIGHT:
                    setX(elementX - getSpeed());
                    return;
                case TOP:
                    setY(elementY + getSpeed());
                    return;
                case BOTTOM:
                    setY(elementY - getSpeed());
                    return;
            }
        }

        int mouseX = point[0].getX();
        int mouseY = point[0].getY();

        // determine movement based on mouse position
        if (mouseX > elementX) {
            setX(elementX - getSpeed());
        } else if (mouseX < elementX) {
            setX(elementX + getSpeed());
        }

        if (mouseY > elementY) {
            setY(elementY - getSpeed());
        } else if (mouseY < elementY) {
            setY(elementY + getSpeed());
        }
    }
}
