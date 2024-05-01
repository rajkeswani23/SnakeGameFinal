import java.awt.*;

class Cell {
    private int x;
    private int y;
    private GameViewer view;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.view = view;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g, int cellSize) {
        g.setColor(Color.RED);
        g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }
}