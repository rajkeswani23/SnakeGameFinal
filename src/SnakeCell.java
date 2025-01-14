// Raj Keswani
// May 10 2024

import java.awt.*;

class SnakeCell extends Cell
{
    private GameViewer view;

    public SnakeCell(int x, int y)
    {
        super(x,y);
        this.view = view;
    }

    // Draw method for making snake body blue
    public void draw(Graphics g, int cellSize, int num)
    {
        g.setColor(Color.BLUE);
        g.fillRect(this.getX() * cellSize + 50, this.getY() * cellSize + 100, cellSize, cellSize);
    }
}