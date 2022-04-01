package game.gameoflife;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;


import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;




public class Game extends Application {

    private final double sceneWidth = 1000;
    private final double sceneHeight = 1000;

    private final double gridWidth = 200;
    private final double gridHeight = 200;

    Grid grid = new Grid(gridWidth, gridHeight, sceneWidth, sceneHeight, false);

    @Override
    public void start(Stage window) {
        Scene scene = grid.displayGrid();
        window.setScene(scene);
        window.show();
        Timeline line = new Timeline(new KeyFrame(Duration.seconds(0.008), arg0 -> grid.updateGrid()));
        line.setCycleCount(Animation.INDEFINITE);
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.S) {
                line.play();
            }
            if (e.getCode() == KeyCode.P) {
                line.pause();
            }
            if (e.getCode() == KeyCode.N) {
                grid.updateGrid();
            }


        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

