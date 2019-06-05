import java.awt.Polygon;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.Math;
class PlayerShip extends GameObject {
    private double SPEED_INCREASE = 1.2;
    private double rotation = 0.0;
    PlayerShip(int x, int y) {
        super(x,y,Size.SHIP);
        this.trajectory = Vector.SOUTH;
    }
    @Override
    public void paint(Graphics2D g2D) {
        shape = constructShape();
        g2D.rotate(Math.toRadians(rotation),x,y);
        g2D.draw(shape);
    }
    @Override
    Polygon constructShape() {
        int width = size.getWidth();
        int length = size.getLength();
        //                            1 2                  3
        return new Polygon(new int[] {x,(x+oneHalf(length)),(x-oneHalf(length))},
                           new int[] {y,(y-width),          (y-width)},
                           3);
    }
    @Override
    void updateHitbox() {
        int width = size.getWidth();
        int length = size.getLength();
        hitbox = new Rectangle(x,y,width,length);
        hitbox.translate((-1*oneHalf(width)),(-1*length));
    }
    void rotateShip(int n) {
        /*IDEA: Make a special paint for PlayerShip that rotates it according
        to it's current trajectory*/
        trajectory = trajectory.changeDirection(trajectory,n);
        rotation += (45.0*n);
        if (rotation > 360) {
            rotation -= 360;
        }
        if (rotation < 0) {
            rotation += 360;
        }
    }
    Vector getDirection() {
        return trajectory;
    }
    private int oneHalf(int n) {
        return n/2;
    }
}
