import java.util.Random;
import java.util.Arrays;
public enum Vector {
    NORTH(0,-1),
    NORTHEAST(1,-1),
    EAST(1,0),
    SOUTHEAST(1,1),
    SOUTH(0,1),
    SOUTHWEST(-1,1),
    WEST(-1,0),
    NORTHWEST(-1,-1)
    ;
    private static final Vector[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static Random random = new Random();
    private int changeInX;
    private int changeInY;
    private Vector(int changeInX, int changeInY) {
        this.changeInX = changeInX;
        this.changeInY = changeInY;
    }
    static Vector getRandomVector() {
        int randomSelection = (int) random.nextInt(SIZE);
        return VALUES[randomSelection];
    }
    static Vector getRandomEastWest() {
        int randomSelection = (int) random.nextInt(1);
        switch(randomSelection) {
            case 0:
                return EAST;
            case 1:
                return WEST;
        }
        return null;      
    }
    static Vector changeDirection(Vector currentVector, int n) {
        int index = Arrays.asList(VALUES).indexOf(currentVector);
        index += n;
        if (index == SIZE) {
            index = 0;
        }
        if (index == -1) {
            index = (SIZE-1);
        }
        return VALUES[index];
    }
    int getXTrajectory() {
        return this.changeInX;
    }
    int getYTrajectory() {
        return this.changeInY;
    }
}