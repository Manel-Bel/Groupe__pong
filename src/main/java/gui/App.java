package gui;

import javafx.application.Application;

import javafx.stage.Stage;

public class App extends Application { // hérite de la classe aplication et donc des types Stage, Scene, State et Node
                                       // ainsi que des méthodes launch, init, start et stop

    @Override // surcharge la fonction de base
    public void start(Stage primaryStage) throws Exception { // appelé par la méthode launch pour démarrer l'application
        new Chargement(primaryStage, 1);
    }
}