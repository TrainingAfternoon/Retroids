import java.awt.Polygon;
import java.awt.Rectangle;
class Bullet extends AutonomousObject {
    Bullet(int x, int y, Vector trajectory) {
        super(x,y,Size.BULLET);
        this.trajectory = trajectory;
    }
    @Override
    Rectangle constructShape() {
        int width = size.getWidth();
        int length = size.getLength();
        //                            1 2
        return new Rectangle(x,y,width,length);
    }
    @Override
    void updateHitbox() {
        hitbox = new Rectangle(x,y,size.getWidth(),size.getLength());
    }
}