// Raj Keswani
// May 10 2024

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.lang.Math;

public class Game implements ActionListener, KeyListener
{
    private final int GRID_SIZE = 16;
    private Cell[][] grid;
    private LinkedList<SnakeCell> snake;
    private String direction;
    private int score;
    private Timer gameTimer;
    private boolean isGameOver;
    private GameViewer window;
    private int currentX;
    private int currentY;
    private int numObstacles;

    public Game()
    {
        grid = new Cell[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++)
        {
            for (int j = 0; j < GRID_SIZE; j++)
            {
                grid[i][j] = new Cell(j, i);
            }
        }
        snake = new LinkedList<>();
        this.currentX = currentX;
        this.currentY = currentY;
        direction = "RIGHT";
        score = 5;
        isGameOver = false;
        numObstacles = 0;
        window = new GameViewer(this);
        window.addKeyListener(this);
        gameTimer = new Timer(100, this);
        gameTimer.start();
        createSnake();
        generateApple();
    }

    public Cell[][] getGrid()
    {
        return grid;
    }

    public int getScore()
    {
        return score;
    }

    public String getDirection()
    {
        return direction;
    }

    public boolean getIsGameOver()
    {
        return isGameOver;
    }

    // Method for creating a snake at beginning of game
    public void createSnake()
    {
        int initialLength = 5;
        int centerX = GRID_SIZE / 2;
        int centerY = GRID_SIZE / 2;
        // For loop that makes 5 snake cells in the middle of grid
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
    // Every time timer hits zero (100 ms)
    public void actionPerformed(ActionEvent e)
    {
        // As long as game isn't over the snake will move
        if (!isGameOver)
        {
            moveSnake();
        }
        window.repaint();
    }

    // Method for moving snake
    public void moveSnake()
    {
        int newX = currentX;
        int newY = currentY;
        // If else that helps determine where the snake head should be depending on direction
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

        // If the next place is outside the grid game is over (snake hit wall)
        if (newX < 0 || newX >= GRID_SIZE || newY < 0 || newY >= GRID_SIZE) {
            isGameOver = true;
            gameTimer.stop();
            return;
        }

        // Move the head to the new position
        SnakeHead head = new SnakeHead(newX,newY,this);
        snake.add(head);
        // If it is an apple, score increases and new apple generated
        if ((grid[newY][newX] instanceof AppleCell))
        {
            generateApple();
            score++;
        }
        // Otherwise, the tail is removed as the snake moved
        else
        {
            SnakeCell tail = snake.remove();
            grid[tail.getY()][tail.getX()] = new Cell(tail.getX(), tail.getY());
        }

        // If the score is a multiple of 10, two new obstacles will come onto grid
        if (score % 10 == 0 && numObstacles / 2 < score / 10)
        {
            generateObtacle();
            generateObtacle();
            window.repaint();
        }

        // As long as the next spot on grid isn't part of the snake or an obstacle
        if (!(grid[newY][newX] instanceof SnakeCell || grid[newY][newX] instanceof ObstacleCell))
        {
            // Next spot is the head
            grid[newY][newX] = head;
            // The previous head spot goes from head to body
            // If else used direction to determine previous spot
            if (direction.equals("RIGHT"))
            {
                grid[newY][--newX] = new SnakeCell(newX,newY);
            }
            else if (direction.equals("LEFT"))
            {
                grid[newY][++newX] = new SnakeCell(newX,newY);
            }
            else if (direction.equals("UP"))
            {
                grid[++newY][newX] = new SnakeCell(newX,newY);
            }
            else if (direction.equals("DOWN"))
            {
                grid[--newY][newX] = new SnakeCell(newX,newY);
            }
        }
        else
        {
            isGameOver = true;
        }
        window.repaint();
    }

    // Method for generating apple
    public void generateApple()
    {
        int x = (int) (Math.random() * GRID_SIZE);
        int y = (int) (Math.random() * GRID_SIZE);
        // Makes sure apple is placed on a spot that isn't occupied by snake or obstacle
        while (grid[y][x] instanceof SnakeCell || grid[y][x] instanceof ObstacleCell)
        {
            x = (int) (Math.random() * GRID_SIZE);
            y = (int) (Math.random() * GRID_SIZE);
        }
        grid[y][x] = new AppleCell(x,y);
        window.repaint();
    }

    // Method for generating obstacle
    public void generateObtacle()
    {
        int x = (int) (Math.random() * GRID_SIZE);
        int y = (int) (Math.random() * GRID_SIZE);
        // Makes sure apple is placed on a spot that isn't occupied by snake or apple
        while (grid[y][x] instanceof SnakeCell || grid[y][x] instanceof AppleCell )
        {
            x = (int) (Math.random() * GRID_SIZE);
            y = (int) (Math.random() * GRID_SIZE);
        }
        grid[y][x] = new ObstacleCell(x,y);
        numObstacles++;
    }

    // What occurs when different keys are pressed
    @Override
    public void keyPressed(KeyEvent e)
    {
        // As long as game is not over, these keys will work
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
        else
        {
            // Otherwise only r works to restart game
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R:
                    Game game = new Game();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public static void main(String[] args)
    {
        Game game = new Game();
    }

}