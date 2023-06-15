package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;
import model.Son;

public class MenuJeu {
	
	protected ChoixNBManches cnbmDuel;
	protected ChoixNBManches cnbm2V2;
	protected ChoixNBManches cnbmBot;

	protected Scene scene;
	protected Stage st;

	public MenuJeu(Stage stage, Menu m, double scale) {
		st = stage;

		// ---Modele---
		// --------------------------------------------------------------

		VBox root = new VBox();
		root.setMinWidth(1000 * scale + 2 * 50);
		root.setMinHeight(600 * scale);

		HBox buttonbox = new HBox(40);
		buttonbox.setPadding(new Insets(200, 0, 0, 0));

		Button classique = new Button("1    V   Ordi");
		classique.setMaxSize(300, 300);
		classique.setId("bouttonJeu");

		Button duel = new Button("1   V   1");
		duel.setMaxSize(300, 300);
		duel.setId("bouttonJeu");

		Button infini = new Button("Infini");
		infini.setMaxSize(300, 300);
		infini.setId("bouttonJeu");

		Button equipes = new Button("2   V   2");
		equipes.setMaxSize(300, 300);
		equipes.setId("bouttonJeu");

		Button retour = new Button();
		Image imageRetour = new Image(getClass().getResource("/image/back.png").toExternalForm());
		ImageView imageviewRetour = new ImageView(imageRetour);
		imageviewRetour.setFitHeight(40);
		imageviewRetour.setFitWidth(40);
		retour.setGraphic(imageviewRetour);
		retour.setPrefSize(40, 40);
		HBox ret = new HBox();
		ret.getChildren().add(retour);

		buttonbox.getChildren().addAll(classique,duel, infini, equipes);

		root.getChildren().addAll(ret, buttonbox);
		retour.setAlignment(Pos.CENTER_LEFT);
		buttonbox.setAlignment(Pos.CENTER);
		// root.setCenter(buttonbox);

		// --------------------------------------------------------------

		// ---Controlleur---

		retour.setOnAction((event) -> {
			MenuJeu.this.st.setScene(m.getScene());
		});
	    
		classique.setOnAction((event) -> {
			Player.setActiveJauge(false);
			if (cnbmBot == null) cnbmBot = new ChoixNBManches(stage, MenuJeu.this, "1   V   Ordi", m, scale);
			else {
				cnbmBot.setChoixNBManches();
			}

		});

	    duel.setOnAction( (event) -> {
	    	if(cnbmDuel == null) cnbmDuel = new ChoixNBManches(stage, MenuJeu.this, "1   V   1", m, scale);
	    	else cnbmDuel.setChoixNBManches();
	    });
	    
	    infini.setOnAction((event) -> {
	    	Player.setActiveJauge(false);
			new Jouer(stage, m, m.getP(), false, true, false, scale, 0);
		});
	    
 		equipes.setOnAction( (event) -> {
 			Player.setActiveJauge(false);
 			if(cnbm2V2 == null) cnbm2V2 = new ChoixNBManches(stage, MenuJeu.this, "2   V   2", m, scale);
 			else {
 				cnbm2V2.setChoixNBManches();
 			}
 		});
 		
	  	//--------------------------------------------------------------
	    
	    scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
		stage.setScene(scene);
	}

	public void setMenuJeu() {
		st.setScene(scene);
	}
	
	public Scene getScene() {
		return scene;
	}

}
