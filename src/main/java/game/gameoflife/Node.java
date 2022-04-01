package game.gameoflife;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Node extends StackPane {
    public int value;
    public double xPos;
    public double yPos;
    public double width;
    public double height;
    public Rectangle rectangle;

    public Node(int value, double xPos, double yPos, double width, double height) {
        this.value = value;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(this.width, this.height);
    }

    public void displayNode() {

        // set correct colour
        if (this.value == 1) {
            rectangle.setFill(Color.GREEN);
            rectangle.setStroke(Color.GREEN);
        }
        if (this.value == 2) {
            rectangle.setFill(Color.DARKRED);
            rectangle.setStroke(Color.DARKRED);
        }
        if (this.value == 0) {
            rectangle.setFill(Color.BLACK);
            rectangle.setStroke(Color.BLACK);
        }

        // set position
        rectangle.setTranslateX(this.xPos);
        rectangle.setTranslateY(this.yPos);

        getChildren().addAll(rectangle);
    }

}