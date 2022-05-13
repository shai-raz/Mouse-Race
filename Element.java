import java.awt.Graphics;
import java.awt.Color;

public abstract class Element {
    protected enum Wall {
        LEFT, RIGHT, TOP, BOTTOM
    }

    protected final Color color = Color.BLACK;

    private Point pos;
    private int size;
    private int speed;

    public Element(Point pos, int size, int speed) {
        this.pos = pos;
        this.size = size;
        this.speed = speed;
    }

    public void setX(int x) {
        this.pos.setX(x);
    }

    public void setY(int y) {
        this.pos.setY(y);
    }

    public int getX() {
        return this.pos.getX();
    }

    public int getY() {
        return this.pos.getY();
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return size;
    }

    public int getHeight() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Checks if the element is touching one of the screen edges (walls).
     * 
     * @param elementX1 top left x coordinate of the element
     * @param elementY1 top left y coordinate of the element
     * @param elementX2 bottom right x coordinate of the element
     * @param elementY2 bottom right y coordinate of the element
     * @return The Wall that the element is touching,
     *         or null if it is not touching any walls
     */
    protected Wall hitWall(int elementX1, int elementY1, int elementX2, int elementY2) {
        int panelWidth = GamePanel.WIDTH;
        int panelHeight = GamePanel.HEIGHT;

        if (elementX1 <= 0) {
            return Wall.LEFT;
        } else if (elementX2 >= panelWidth) {
            return Wall.RIGHT;
        }

        if (elementY1 <= 0) {
            return Wall.TOP;
        } else if (elementY2 >= panelHeight) {
            return Wall.BOTTOM;
        }

        return null;
    }

    /**
     * Checks if the element is touching the mouse.
     * 
     * @return true if the element is touching the mouse
     */
    protected boolean hitTarget(Point mousePos) {
        return mousePos.getX() >= getX() && mousePos.getX() <= getX() + getWidth() &&
                mousePos.getY() >= getY() && mousePos.getY() <= getY() + getHeight();
    }

    public abstract void move(Point... points);

    public abstract void draw(Graphics g);
}
