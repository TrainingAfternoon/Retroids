import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;
import java.util.ConcurrentModificationException;
class GamePane extends JPanel implements KeyListener {
    private Dimension windowDimensions;
    static ArrayList<ArrayList<GameObject>> gameObjects;
    static ArrayList<GameObject> ships;
    static ArrayList<GameObject> asteroids;
    static ArrayList<GameObject> bullets;
    ScoreBoard scoreboard;
    PlayerShip playerShip;
    GamePane(Dimension windowDimensions) {
        super();
        setBackground(new Color(0,0,0));
        this.windowDimensions = windowDimensions;
        scoreboard = new ScoreBoard(5,10);
        ships = new ArrayList<GameObject>() {{
            Random random = new Random();
            int height = (int) windowDimensions.getHeight();
            int width = (int) windowDimensions.getWidth();
            add(new PlayerShip(random.nextInt(width),random.nextInt(height)));
            add(new AlienShip(random.nextInt(width),random.nextInt(height)));
        }};
        asteroids = new ArrayList<GameObject>() {{
            Random random = new Random();
            int height = (int) windowDimensions.getHeight();
            int width = (int) windowDimensions.getWidth();
            add(new Asteroid(random.nextInt(width),random.nextInt(height),Size.LARGE));
            add(new Asteroid(random.nextInt(width),random.nextInt(height),Size.MEDIUM));
            add(new Asteroid(random.nextInt(width),random.nextInt(height),Size.SMALL));
        }};
        bullets = new ArrayList<GameObject>();
        gameObjects = new ArrayList<ArrayList<GameObject>>() {{
            add(ships);
            add(asteroids);
            add(bullets);
        }};
        playerShip = (PlayerShip)gameObjects.get(0).get(0);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Random random = new Random();
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        scoreboard.paint(g);
        try {
            for(GameObject asteroid : asteroids) {
                for(GameObject bullet : bullets) {
                    if(bullet.hitbox.intersects(asteroid.hitbox)) {
                        scoreboard.updateScore(asteroid.size.getPoints());
                        if(asteroid.size != Size.SMALL) {
                            Size newSize = (asteroid.size == Size.LARGE) ? Size.MEDIUM : Size.SMALL;
                            int numCreated = random.nextInt(1)+2;
                            for(int i = 0; i < numCreated; i++) {
                                asteroids.add(new Asteroid(asteroid.x,asteroid.y,newSize));
                            }
                            asteroids.remove(asteroid);
                        } else {
                            asteroids.remove(asteroid);
                        }
                        bullets.remove(bullet);
                    }
                }
                if(asteroid.hitbox.intersects(playerShip.hitbox)) {
                    ships.remove(playerShip);
                    System.exit(0);
                }
            } 
        } catch(ConcurrentModificationException cme) {
            
        }
        for (GameObject ship : ships) {
            if(ship instanceof AlienShip) {
                for (GameObject bullet : bullets) {
                    if(bullet.hitbox.intersects(ship.hitbox)) {
                        scoreboard.updateScore(ship.size.getPoints());
                        ships.remove(ship);
                        bullets.remove(bullets);
                    }
                }
                if(ship.hitbox.intersects(playerShip.hitbox)) {
                    ships.remove(playerShip);
                    System.exit(0);
                }
            }
        }
        for (ArrayList<GameObject> objectList : gameObjects) {
            for (GameObject gObj : objectList) {
                Graphics2D g2D = (Graphics2D) g.create();
                gObj.update();
                gObj.paint(g2D);
                g2D.dispose();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                playerShip.updateTrajectory();
                repaint();
                break;
            case KeyEvent.VK_B:
                bullets.add(new Bullet(playerShip.x,playerShip.y,playerShip.trajectory));
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                playerShip.rotateShip(-1);
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                playerShip.rotateShip(1);
                repaint();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        //NOT USED
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        //NOT USED
    }
}
