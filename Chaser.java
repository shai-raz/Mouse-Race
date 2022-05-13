import java.awt.Graphics;

public class Chaser extends Element {
    public Chaser(Point pos, int size, int speed) {
        super(pos, size, speed);
    }

    @Override
    public int getWidth() {
        return super.getWidth() * 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        // rectangle
        g.fillRect(getX(), getY(), 2 * getSize(), getSize());
    }

    /**
     * Moves the element towards the mouse position.
     * 
     * @param Point the x,y coordinates of the mouse
     */
    public void move(Point... points) {
        int mouseX = points[0].getX();
        int mouseY = points[0].getY();

        int elementX = getX();
        int elementY = getY();

        int elementCenterX = elementX + getSize();
        int elementCenterY = elementY + getSize() / 2;

        int elementX2 = getX() + getSize() * 2;
        int elementY2 = getY() + getSize();

        // check wall collision
        Wall wallHit = hitWall(elementX, elementY, elementX2, elementY2);

        /* Chase the mouse */
        if (mouseX > elementCenterX && wallHit != Wall.RIGHT) { // moving right
            setX(elementX + getSpeed());
        } else if (mouseX < elementCenterX && wallHit != Wall.LEFT) { // moving left
            setX(elementX - getSpeed());
        }

        if (mouseY > elementCenterY && wallHit != Wall.BOTTOM) { // moving down
            setY(elementY + getSpeed());
        } else if (mouseY < elementCenterY && wallHit != Wall.TOP) { // moving up
            setY(elementY - getSpeed());
        }
    }
}
