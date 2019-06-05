import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.Rectangle;
public class Asteroid extends AutonomousObject {
    public Asteroid(int x, int y, Size size) {
        super(x,y,size);
        this.size = size;
        trajectory = Vector.getRandomVector();
        hitbox.translate(0,(-1*twoThirds(size.getLength())));
    }
    @Override
    Polygon constructShape() {
        int width = size.getWidth();
        int length = size.getLength();
        return new Polygon(new int[] {x,(x+oneThird(width)),(x+twoThirds(width)),(x+width),(x+width),           (x+twoThirds(width)), (x+oneThird(width)),  x},
            new int[] {y,(y+oneThird(length)),(y+oneThird(length)),y,       (y-oneThird(length)),(y-twoThirds(length)),(y-twoThirds(length)),(y-oneThird(length))},
            8);
    }
    @Override
    void updateHitbox() {
        hitbox = new Rectangle(x,y,size.getWidth(),size.getLength());
        hitbox.translate(0,(-1*twoThirds(size.getLength())));
    }
    private int oneThird(int n) {
        return (n/3);
    }
    private int twoThirds(int n) {
        return ((2*n)/3);
    }
}