import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {
    private final int WINDOW_WIDTH = 1010;
    private final int WINDOW_HEIGHT = 810;
    private final int CELL_SIZE = 50; // Size of each cell in the grid
    private Image[] appleImages;
    private Game game;


    public GameViewer(Game game)
    {
        this.appleImages = appleImages;
        this.game = game;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public void myPaint(Graphics g) {
        draw(g);
    }

    private void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                {
                    g.fillRect( game.getGrid()[j][i].getX() * CELL_SIZE,  game.getGrid()[j][i].getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                {
                    game.getGrid()[j][i].draw(g, CELL_SIZE);
                }
            }
        }
    }

    //I think I can remove buffer because no animation
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }
}