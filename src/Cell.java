// Raj Keswani
// May 10 2024

import java.awt.*;
import java.awt.Color;

class Cell
{
    private int x;
    private int y;
    private GameViewer view;

    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.view = view;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    // Method for regular cell to draw itself
    public void draw(Graphics g, int cellSize, int num)
    {
        // Alternates between different green colors for each square
        if (num % 2 == 0)
        {
            g.setColor(new Color(34,183,23));
        }
        else
        {
            g.setColor(new Color(22,134,14));
        }
        g.fillRect(x * cellSize + 50, y * cellSize + 100, cellSize, cellSize);
    }
}