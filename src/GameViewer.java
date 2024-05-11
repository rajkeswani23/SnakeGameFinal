// Raj Keswani
// May 10 2024

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {
    private final int WINDOW_WIDTH = 1010;
    private final int WINDOW_HEIGHT = 1010;
    private final int CELL_SIZE = 40;
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

    public void gameOverScreen(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,WINDOW_WIDTH,WINDOW_HEIGHT);
        g.setColor(new Color(12,100,47));
        g.fillRect(225, 300,650,200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 60));
        g.drawString("Score: " + Integer.toString(game.getScore()), 400, 250);
        g.drawString("PRESS R TO RESTART",250,410);
    }

    // Screen while game is going
    public void gamePlaying(Graphics g)
    {
        // Drawing initial board
        g.setColor(new Color(12,100,47));
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        g.fillRect(50,80,150,100);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + Integer.toString(game.getScore()), 50, 80);
        g.setColor(Color.WHITE);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                {
                    g.fillRect(game.getGrid()[j][i].getX() * CELL_SIZE + 50,  game.getGrid()[j][i].getY() * CELL_SIZE + 100, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // Each square on the board draws itself (snake, apple, obstacle, regular cell)
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                {
                    game.getGrid()[j][i].draw(g, CELL_SIZE, j + i);
                }
            }
        }
    }

    // Painting the window method
    public void myPaint(Graphics g)
    {
        if (game.getIsGameOver())
        {
           gameOverScreen(g);
        }
        else
        {
            gamePlaying(g);
        }
    }

    // Double buffer for animation
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