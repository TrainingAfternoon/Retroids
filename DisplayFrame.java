import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.awt.Dimension;
class DisplayFrame extends JFrame {
    private Dimension windowDimensions;
    private final long waitTime = 3000;
    private final long period = 2000;
    public DisplayFrame() {
        super("Retroids v.1.1");
        windowDimensions = new Dimension(700,700);
        setSize(windowDimensions);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePane gamePanel = new GamePane(windowDimensions);
        setContentPane(gamePanel);
        addKeyListener(gamePanel);
        setResizable(false);
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                int randomAsteroids = random.nextInt(3);
                int randomAlienships = random.nextInt(1);
                for (int i = 0; i < randomAsteroids; i++) {
                    gamePanel.asteroids.add(new Asteroid(
                        random.nextInt((int)windowDimensions.getWidth()),
                        random.nextInt((int)windowDimensions.getHeight()),
                        Size.LARGE
                    ));
                }
            }
        },waitTime,period);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gamePanel.repaint();
            }
        },0,600);
        
        setVisible(true);
    }
}