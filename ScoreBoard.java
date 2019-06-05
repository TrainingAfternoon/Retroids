import java.awt.Graphics;
import java.awt.Graphics2D;
class ScoreBoard {
    private int x, y;
    private int score;
    private String scoreString;
    ScoreBoard(int x, int y) {
        this.x = x;
        this.y = y;
        score = 0;
        scoreString = "0";
    }
    void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g.create();
        g2D.drawString(scoreString,x,y);
    }
    void updateScore(int n) {
        score += n;
        scoreString = Integer.toString(score);
    }
}