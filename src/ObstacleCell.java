// Raj Keswani
// May 10 2024
import java.awt.*;

class ObstacleCell extends Cell
{
    private GameViewer view;

    public ObstacleCell(int x, int y)
    {
        super(x,y);
        this.view = view;
    }

    // Draw method for obstacles
    public void draw(Graphics g, int cellSize, int num)
    {
        g.setColor(Color.ORANGE);
        g.fillRect(this.getX() * cellSize + 50, this.getY() * cellSize + 100, cellSize, cellSize);
    }
}