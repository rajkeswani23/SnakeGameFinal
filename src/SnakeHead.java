// Raj Keswani
// May 10 2024

import javax.swing.*;
import java.awt.*;

public class SnakeHead extends SnakeCell
{
    private GameViewer view;
    private Game game;

    public SnakeHead(int x, int y, Game game)
    {
        super(x,y);
        this.view = view;
        this.game = game;
    }

    // Draw method for snake head
    // Head image is drawn depending on the direction
    public void draw(Graphics g, int cellSize, int num)
    {
        g.setColor(Color.BLUE);
        g.fillRect(this.getX() * cellSize + 50, this.getY() * cellSize + 100, cellSize, cellSize);
        g.drawImage(new ImageIcon("Resources/" + game.getDirection() + ".png").getImage(),+ this.getX() *
                cellSize + 50, this.getY() * cellSize + 100, cellSize, cellSize, view);
    }
}
