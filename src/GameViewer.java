import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {
    private final int WINDOW_WIDTH = 1010;
    private final int WINDOW_HEIGHT = 810;
    private final int CELL_SIZE = 50; // Size of each cell in the grid
    private Image[] fishImages;
    private Game game;


    public GameViewer(Game game)
    {
        this.fishImages = fishImages;
        this.game = game;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public void myPaint(Graphics g) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                g.setColor(Color.WHITE);
                g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        drawSnake(g);
    }

    private void drawSnake(Graphics g) {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (game.getGrid()[j][i] != null) {
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