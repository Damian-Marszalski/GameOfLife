package game.gameoflife;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;




public class Game extends Application {

    private final double sceneWidth = 1920;
    private final double sceneHeight = 1080;

    private final double gridHeight = 50;

    private final double gridWidth = (int)((sceneWidth / sceneHeight) * gridHeight + 2);

    private final Grid grid = new Grid(gridWidth, gridHeight, sceneWidth, sceneHeight, false);
    private final Timeline line = new Timeline(new KeyFrame(Duration.seconds(0.1), arg0 -> grid.updateGrid()));

    private Group root;
    private Scene scene;

    @Override
    public void start(Stage window) {
        this.root = grid.displayGrid();
        this.scene = new Scene(root, this.sceneWidth*4, this.sceneHeight*4);

        scene.setFill(Color.DARKRED);
        window.setScene(scene);
        window.setMaximized(true);
        window.setFullScreen(true);
        window.show();


        line.setCycleCount(Animation.INDEFINITE);

        scene.setOnKeyReleased(e -> onKeyReleaseProperty(e, line));
        scene.setOnKeyPressed(e -> onKeyPressedProperty(e, line));
        scene.setOnMouseClicked(this::onMouseClickedProperty);
        scene.setOnScroll(this::onScrollProperty);
    }



    private void onScrollProperty(ScrollEvent s) {
        Group tempGroup = new Group();

        for (int row = 0; row < grid.grid.length; row++) {
            for (int column = 0; column < grid.grid[row].length; column++) {
                grid.grid[row][column].hideNode();

                grid.grid[row][column].displayNode();
                tempGroup.getChildren().add(grid.grid[row][column]);
            }
        }

        this.scene.setRoot(tempGroup);
    }

    private void onKeyReleaseProperty(KeyEvent e, Timeline line) {
        if (e.getCode() == KeyCode.S) {
            if (line.getStatus() == Animation.Status.STOPPED || line.getStatus() == Animation.Status.PAUSED) {
                line.play();
            }
            else {
                line.pause();
            }
        }
    }

    private void onKeyPressedProperty(KeyEvent e, Timeline line) {
        if (e.getCode() == KeyCode.N) {
            grid.updateGrid();
        }
    }

    private void onMouseClickedProperty(MouseEvent mouseEvent) {
        double mouseXPos = mouseEvent.getSceneX();
        double mouseYPos = mouseEvent.getSceneY();

        for (int row = 0; row < grid.grid.length; row++) {
            for (int column = 0; column < grid.grid[row].length; column++) {
                if (grid.grid[row][column].value != 2) {
                    double xPosTopLeft = grid.grid[row][column].xPos;
                    double yPosTopLeft = grid.grid[row][column].yPos;
                    double xPosBottomRight = grid.grid[row+1][column+1].xPos;
                    double yPosBottomRight = grid.grid[row+1][column+1].yPos;

                    if ((mouseXPos > xPosTopLeft && mouseXPos < xPosBottomRight) && (mouseYPos > yPosTopLeft && mouseYPos < yPosBottomRight)) {
                        if (grid.grid[row][column].value == 1) {
                            grid.grid[row][column].value = 0;
                            grid.grid[row][column].rectangle.setFill(Color.BLACK);
                        }
                        else {
                            grid.grid[row][column].value = 1;
                            grid.grid[row][column].rectangle.setFill(Color.GREEN);
                        }
                        grid.grid[row][column].rectangle.setStroke(Color.BLUE);
                        grid.grid[row][column].rectangle.setStrokeWidth(0.1);
                    }
                }
            }
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}

