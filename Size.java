public enum Size {
    LARGE(50,50,50,2.5),
    MEDIUM(30,30,100,2.75),
    SHIP(20,20,250,4.0),
    SMALL(15,15,200,3.0),
    BULLET(4,4,0,7.0);
    ;
    private int width;
    private int length;
    private int points;
    private double speed;
    Size(int width, int length, int points, double speed) {
        this.width = width;
        this.length = length;
        this.speed = speed;
        this.points = points;
    }
    public int getWidth() {
        return this.width;
    }
    public int getLength() {
        return this.length;
    }
    public int getPoints() {
        return this.points;
    }
    public double getSpeed() {
        return this.speed;
    }
}