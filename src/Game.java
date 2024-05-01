import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Game implements ActionListener, KeyListener {
    private final int GRID_SIZE = 16;
    private SnakeCell[][] grid;
    private LinkedList<SnakeCell> snake;
    private String direction;
    private Timer gameTimer;
    private boolean isGameOver;
    private GameViewer window;
    private int currentX;
    private int currentY;


    public Game()
    {
        grid = new SnakeCell[GRID_SIZE][GRID_SIZE];
        snake = new LinkedList<>();
        this.currentX = currentX;
        this.currentY = currentY;
        direction = "RIGHT";
        isGameOver = false;
        window = new GameViewer(this);
        window.addKeyListener(this);


        gameTimer = new Timer(250, this);
        gameTimer.start();
        createSnake();
    }

    public SnakeCell[][] getGrid()
    {
        return grid;
    }

    private void createSnake()
    {
        int initialLength = 5;
        int centerX = GRID_SIZE / 2;
        int centerY = GRID_SIZE / 2;
        for (int i = 0; i < initialLength; i++) {
            SnakeCell cell = new SnakeCell(centerX - initialLength + i, centerY);
            snake.add(cell);
            grid[centerY][centerX - initialLength + i] = cell;
            currentX = cell.getX();
            currentY = cell.getY();
        }
        window.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            moveSnake();
            window.repaint();
        }
        else
        {
            window.repaint();
        }

    }

    private void moveSnake() {
        SnakeCell tail = snake.remove();
        grid[tail.getY()][tail.getX()] = null;
        int newX = currentX;
        int newY = currentY;

        if (direction.equals("UP"))
        {
            newY--;
            currentY = newY;
        }
        else if (direction.equals("DOWN"))
        {
            newY++;
            currentY = newY;
        }
        else if (direction.equals("LEFT"))
        {
            newX--;
            currentX = newX;
        }
        else if (direction.equals("RIGHT"))
        {
            newX++;
            currentX = newX;
        }

        if (newX < 0 || newX >= GRID_SIZE || newY < 0 || newY >= GRID_SIZE) {
            isGameOver = true;
            gameTimer.stop();
            return;
        }

        // Move the head to the new position
        SnakeCell head = new SnakeCell(newX,newY);
        snake.add(head);
        if(grid[newY][newX] == null)
        {
            grid[newY][newX] = head;
        }
        else
        {
            isGameOver = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isGameOver)
        {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (!"LEFT".equals(direction) && !"RIGHT".equals(direction)) {
                        direction = "LEFT";
                        moveSnake();
                        gameTimer.restart();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!"LEFT".equals(direction) && !"RIGHT".equals(direction)) {
                        direction = "RIGHT";
                        moveSnake();
                        gameTimer.restart();
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!"UP".equals(direction) && !"DOWN".equals(direction)) {
                        direction = "UP";
                        moveSnake();
                        gameTimer.restart();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!"UP".equals(direction) && !"DOWN".equals(direction)) {
                        direction = "DOWN";
                        moveSnake();
                        gameTimer.restart();
                    }
                    break;
            }
            window.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args)
    {
        Game game = new Game();
    }
}