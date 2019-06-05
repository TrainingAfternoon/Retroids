import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.Rectangle;
abstract class GameObject {
    int x;
    int y;
    Shape shape;
    Size size;
    Vector trajectory;
    Rectangle hitbox;
    GameObject(int x, int y, Size size) {
        this.x = x;
        this.y = y;
        this.size = size;
        hitbox = new Rectangle(x,y,size.getWidth(),size.getLength());
    }
    public void update() {
        updateHitbox();
    }
    public void paint(Graphics2D g2D) {
        shape = constructShape();
        g2D.draw(shape);
    }
    void updateTrajectory() {
        double xTrajectory = trajectory.getXTrajectory();
        double yTrajectory = trajectory.getYTrajectory();
        double speed = size.getSpeed();
        //It's the int rounding that's the problem
        xTrajectory = (int)(speed*xTrajectory + (xTrajectory*0.5));
        yTrajectory = (int)(speed*yTrajectory + (yTrajectory*0.5));
        x += xTrajectory;
        y += yTrajectory;
    }
    abstract Shape constructShape();
    abstract void updateHitbox();
}