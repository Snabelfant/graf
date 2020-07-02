package function;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graf extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root,600, 300);
        primaryStage.setTitle("Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
