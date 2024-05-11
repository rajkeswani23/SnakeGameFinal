// Raj Keswani
// May 10 2024

import javax.swing.*;
import java.awt.*;

class AppleCell extends Cell{
    private GameViewer view;
    private Image apple;

    public AppleCell(int x, int y)
    {
        super(x,y);
        this.view = view;
        this.apple = new ImageIcon("Resources/apple.png").getImage();
    }

    // Draw method for apple
    public void draw(Graphics g, int cellSize, int num)
    {
        // Alternates each block (odd or even) to switch between green shades
        if (num % 2 == 0)
        {
            g.setColor(new Color(34,183,23));
        }
        else
        {
            g.setColor(new Color(22,134,14));
        }

        g.fillRect(this.getX() * cellSize + 50, this.getY() * cellSize + 100, cellSize, cellSize);
        g.drawImage(apple,this.getX() * cellSize + 50, this.getY() * cellSize + 100, cellSize, cellSize, view);
    }
}