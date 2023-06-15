package gui;

import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class Chargement {
    public Chargement(Stage stage, double scale) {
        stage.getIcons().add(new Image(getClass().getResource("/image/logoo.png").toExternalForm()));
        // La tâche à exécuter
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                // Avancement total
                int totalProgress = 100;

                for (int i = 0; i < totalProgress + 1; i += 3) {
                    Thread.sleep(50);
                    // Mise à jour de l'avancement
                    updateProgress(i, totalProgress);
                }
                return null;
            }

        };

        // La barre de progression
        var progressBar = new ProgressBar();

        // Binding sur l'avancement
        progressBar.progressProperty().bind(task.progressProperty());

        // Démarrage de la tâche
        new Thread(task).start();

        VBox root = new VBox(20, progressBar);
        root.setMinWidth(1000 * scale + 2 * 50);
        root.setMinHeight(600 * scale);
        var scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ShannonPong");
        stage.getScene().getRoot().requestFocus();
        stage.show();
        task.setOnRunning(e -> {
            // peut etre une loading class
        });
        task.setOnSucceeded(e -> {
            try {
                new Menu(stage, scale);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

}
