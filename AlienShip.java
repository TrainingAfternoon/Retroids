import java.awt.Shape;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
class AlienShip extends AutonomousObject {
    AlienShip(int x, int y) {
        super(x,y,Size.SHIP);
        trajectory = Vector.getRandomEastWest();
    }
    @Override
    Polygon constructShape() {
        int width = size.getWidth();
        int length = size.getLength();
        //                            1   2                   3           4
        return new Polygon(new int[] {x, (x+twoThirds(width)),(x-width),  (x+width)},
                           new int[] {y, y,                   (y+length), (y+length)},
                           4);
    }
    @Override
    void updateHitbox() {
        hitbox = new Rectangle(x,y,size.getWidth(),size.getLength());
    }
    private int twoThirds(int n) {
        return (2*n)/3;
    }
}