package game.gameoflife;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.Random;

public class Grid {
    private final double sceneWidth;
    private final double sceneHeight;

    private final double gridWidth;
    private final double gridHeight;

    private final double nodeWidth;
    private final double nodeHeight;

    public Node [][] grid;

    public Grid(double gridWidth, double gridHeight, double sceneWidth, double sceneHeight,  boolean empty){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.nodeWidth = sceneWidth / (gridWidth + 2);
        this.nodeHeight = sceneHeight / (gridHeight + 2);
        this.grid = generateGrid(empty);
    }
    private Node [][] generateGrid(boolean empty) {
        int gridWidth = (int)this.gridWidth;
        int gridHeight = (int)this.gridHeight;
        Node [][] grid = new Node[gridWidth + 2][gridHeight + 2];
        Random rand = new Random();
        int upperbound = 2;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++){

                Node node = new Node(3, column * nodeWidth, row * nodeHeight, nodeWidth, nodeHeight);
                grid[row][column] = node;
                if(row == 0 || row == this.gridHeight + 1 || column == 0 || column == this.gridWidth + 1) {
                    grid[row][column].value = 2;
                }
                else {
                    if (empty) {
                        grid[row][column].value = 0;
                    }
                    else {
                        int randInt = rand.nextInt(upperbound);
                        if (randInt == 0) {
                            grid[row][column].value = 1;
                        }
                        else {
                            grid[row][column].value = 0;
                        }

                    }
                }
            }
        }
        return grid;
    }


    public void updateGrid() {
        int[][] tempGrid = tempGridGenerate();
        for (int row = 0; row < tempGrid.length; row++) {
            for (int column = 0; column < tempGrid[row].length; column++) {
                if (tempGrid[row][column] != 2) {
                    int alive = numAlive(row, column, tempGrid);
                    if (tempGrid[row][column] == 1) {
                        if (alive == 2 || alive == 3) {
                            grid[row][column].value = 1;
                            grid[row][column].rectangle.setFill(Color.GREEN);
                            grid[row][column].rectangle.setStroke(Color.GREEN);
                        }
                        else {
                            grid[row][column].value = 0;
                            grid[row][column].rectangle.setFill(Color.BLACK);
                            grid[row][column].rectangle.setStroke(Color.BLACK);
                        }
                    }
                    if (tempGrid[row][column] == 0) {
                        if (alive == 3) {
                            grid[row][column].value = 1;
                            grid[row][column].rectangle.setFill(Color.GREEN);
                            grid[row][column].rectangle.setStroke(Color.GREEN);
                        }
                        else {
                            grid[row][column].value = 0;
                            grid[row][column].rectangle.setFill(Color.BLACK);
                            grid[row][column].rectangle.setStroke(Color.BLACK);
                        }
                    }

                }
            }
        }
    }

    private int [][] tempGridGenerate(){
        int [][] tempGrid = new int[(int) (gridWidth+2)][(int) (gridHeight+2)];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                tempGrid[row][column] = grid[row][column].value;
            }
        }
        return tempGrid;
    }

    private int numAlive(int row, int column, int[][] tempGrid) {
        int count = 0;
        if (tempGrid[row-1][column-1] == 1) {
            count++;
        }
        if (tempGrid[row-1][column] == 1) {
            count++;
        }
        if (tempGrid[row-1][column+1] == 1) {
            count++;
        }
        if (tempGrid[row][column-1] == 1) {
            count++;
        }
        if (tempGrid[row][column+1] == 1) {
            count++;
        }
        if (tempGrid[row+1][column-1] == 1) {
            count++;
        }
        if (tempGrid[row+1][column] == 1) {
            count++;
        }
        if (tempGrid[row+1][column+1] == 1) {
            count++;
        }

        return count;
    }

    public Scene displayGrid() {
        Group root = new Group();

        for (Node[] nodes : grid) {
            for (Node node : nodes) {
                node.displayNode();
                root.getChildren().add(node);
            }
        }

        return new Scene(root, this.sceneWidth, this.sceneHeight);
    }

}
