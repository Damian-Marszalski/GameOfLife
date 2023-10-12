package game.gameoflife;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Node extends StackPane {
    public int value;
    public double xPos;
    public double yPos;
    public double size;
    public Rectangle rectangle;

    public Node(int value, double xPos, double yPos, double size) {
        this.value = value;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        rectangle = new Rectangle(this.size, this.size);
    }

    public void displayNode() {

        // set correct colour
        if (this.value == 1) {
            rectangle.setFill(Color.GREEN);
            rectangle.setStroke(Color.BLUE);
            rectangle.setStrokeWidth(0.1);
        }
        if (this.value == 2) {
            rectangle.setFill(Color.DARKRED);
            rectangle.setStroke(Color.DARKRED);
        }
        if (this.value == 0) {
            rectangle.setFill(Color.BLACK);
            rectangle.setStroke(Color.BLUE);
            rectangle.setStrokeWidth(0.1);
        }

        // set position
        rectangle.setTranslateX(this.xPos);
        rectangle.setTranslateY(this.yPos);

        getChildren().addAll(rectangle);
    }

    public void hideNode() {
        getChildren().remove(rectangle);
    }

}