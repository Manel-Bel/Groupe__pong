package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

public class ChoixNBManches {

	protected Scene scene;
	protected Stage st;
	private CheckBox pouv;

	public ChoixNBManches(Stage stage, MenuJeu mj, String jeu, Menu m, double scale) {
		st = stage;

		// ---Modele---

		VBox root = new VBox(50);
		root.setMinWidth(1000 * 1 + 2 * 50);
		root.setMinHeight(600 * 1);
		root.setMaxWidth(1000 * 1 + 2 * 50);
		root.setMaxHeight(600 * 1);

		Button retour = new Button();
		Image imageRetour = new Image(getClass().getResource("/image/back.png").toExternalForm());
		ImageView imageviewRetour = new ImageView(imageRetour);
		imageviewRetour.setFitHeight(40);
		imageviewRetour.setFitWidth(40);
		retour.setGraphic(imageviewRetour);
		retour.setPrefSize(40, 40);

		HBox buttonbox = new HBox(250);
		buttonbox.setPadding(new Insets(200, 0, 0, 0));

		Label vide = new Label();
		Label label = new Label("NOMBRES   DE   MANCHES   : ");
		label.setId("titrelabel");
		label.setStyle("-fx-font-size: 50");
		ComboBox<Integer> choix = new ComboBox<Integer>();
		choix.setId("comboBox");
		ObservableList<Integer> listchoix = FXCollections.observableArrayList(1, 3, 5, 7);
		choix.setItems(listchoix);
		choix.getSelectionModel().selectFirst();
		
		HBox pouvoirs = new HBox();
		Label pouvoir = new Label("POUVOIRS   :   ");
		pouv = new CheckBox();
		pouv.setSelected(false);
		pouv.setOnAction((event) -> {
			if(pouv.isSelected()) {
				Player.setActiveJauge(true);
			}
			else Player.setActiveJauge(false);
		});
		pouv.setId("check");
		pouvoirs.getChildren().addAll(pouvoir,pouv);
		
		HBox ret = new HBox();
		ret.getChildren().add(retour);
		Button valider = new Button("Jouer");
		valider.setStyle("-fx-font-size: 30");
		buttonbox.getChildren().addAll(vide,choix, valider);

		if(jeu.equals("1   V   1")) {
			root.getChildren().addAll(ret,label,buttonbox,pouvoirs);
		}
		else root.getChildren().addAll(ret,label,buttonbox);
		

		// --------------------------------------------------------------

		// ---Controlleur---

		retour.setOnAction((event) -> {
			ChoixNBManches.this.st.setScene(mj.getScene());
		});

		valider.setOnAction((event) -> {
			if (jeu.equals("1   V   1")) {
				System.out.println("1   V   1 : " + choix.getValue());
				new Jouer(stage, m, m.getP(), false, false, false, scale, (Integer) choix.getValue());
			}

			else if (jeu.equals("2   V   2")) {
				System.out.println("2   V   2 :" + choix.getValue());
				new Jouer(stage, m, m.getP(), true, false, false, scale, (Integer) choix.getValue());
			}

			else {
				System.out.println("1   V   Ordi :" + choix.getValue());
				new Jouer(stage, m, m.getP(), false, false, true, scale, (Integer) choix.getValue());
			}
		});

		// --------------------------------------------------------------

		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
		stage.setScene(scene);

	}

	public void setChoixNBManches() {
		st.setScene(scene);
		pouv.setSelected(false);
		Player.setActiveJauge(false);
	}

}
