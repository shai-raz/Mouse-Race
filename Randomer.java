import java.awt.Graphics;
import java.util.ArrayList;

public class Randomer extends Element {
    private enum Direction {
        TOP, RIGHT, BOTTOM, LEFT,
        TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_LEFT
    }

    private Direction direction;

    public Randomer(Point pos, int size, int speed) {
        super(pos, size, speed);

        direction = Direction.values()[(int) (Math.random() * Direction.values().length)];
    }

    private void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        // square
        g.fillRect(getX(), getY(), getSize(), getSize());
    }

    /**
     * Moves the element in a straight line until a wall is hit,
     * then moves in a random direction.
     */
    public void move(Point... point) {
        int elementX = getX();
        int elementY = getY();

        int elementX2 = elementX + getSize();
        int elementY2 = elementY + getSize();

        // determine moving direction
        switch (direction) {
            case TOP:
                setY(elementY - getSpeed());
                break;
            case RIGHT:
                setX(elementX + getSpeed());
                break;
            case BOTTOM:
                setY(elementY + getSpeed());
                break;
            case LEFT:
                setX(elementX - getSpeed());
                break;
            case TOP_RIGHT:
                setX(elementX + getSpeed());
                setY(elementY - getSpeed());
                break;
            case BOTTOM_RIGHT:
                setX(elementX + getSpeed());
                setY(elementY + getSpeed());
                break;
            case BOTTOM_LEFT:
                setX(elementX - getSpeed());
                setY(elementY + getSpeed());
                break;
            case TOP_LEFT:
                setX(elementX - getSpeed());
                setY(elementY - getSpeed());
                break;
        }

        ArrayList<Direction> directions = new ArrayList<>();

        // check wall collision
        Wall wallHit = hitWall(elementX, elementY, elementX2, elementY2);
        if (wallHit == null)
            return;
        switch (wallHit) {
            case LEFT:
                directions.add(Direction.TOP);
                directions.add(Direction.BOTTOM);
                directions.add(Direction.RIGHT);
                directions.add(Direction.TOP_RIGHT);
                directions.add(Direction.BOTTOM_RIGHT);
                setDirection(directions.get((int) (Math.random() * directions.size())));
                break;
            case RIGHT:
                directions.add(Direction.TOP);
                directions.add(Direction.BOTTOM);
                directions.add(Direction.LEFT);
                directions.add(Direction.TOP_LEFT);
                directions.add(Direction.BOTTOM_LEFT);
                setDirection(directions.get((int) (Math.random() * directions.size())));
                break;
            case TOP:
                directions.add(Direction.LEFT);
                directions.add(Direction.RIGHT);
                directions.add(Direction.BOTTOM);
                directions.add(Direction.BOTTOM_LEFT);
                directions.add(Direction.BOTTOM_RIGHT);
                setDirection(directions.get((int) (Math.random() * directions.size())));
                break;
            case BOTTOM:
                directions.add(Direction.LEFT);
                directions.add(Direction.RIGHT);
                directions.add(Direction.TOP);
                directions.add(Direction.TOP_LEFT);
                directions.add(Direction.TOP_RIGHT);
                setDirection(directions.get((int) (Math.random() * directions.size())));
                break;
        }
    }
}
