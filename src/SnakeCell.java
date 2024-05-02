import java.awt.*;

class SnakeCell extends Cell{
    private GameViewer view;

    public SnakeCell(int x, int y) {
        super(x,y);
        this.view = view;
    }

    public void draw(Graphics g, int cellSize) {
        g.setColor(Color.RED);
        g.fillRect(this.getX() * cellSize, this.getY() * cellSize, cellSize, cellSize);
    }
}