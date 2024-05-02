import java.awt.*;

class AppleCell extends Cell{
    private GameViewer view;

    public AppleCell(int x, int y) {
        super(x,y);
        this.view = view;
    }

    public void draw(Graphics g, int cellSize) {
        g.setColor(Color.RED);
        g.fillRect(getX() * cellSize, getY() * cellSize, cellSize, cellSize);
    }
}