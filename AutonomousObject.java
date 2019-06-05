abstract class AutonomousObject extends GameObject {
    AutonomousObject(int x, int y, Size size) {
        super(x,y,size);
    }
    @Override
    public void update() {
        updateTrajectory();
        updateHitbox();
    }
}