package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Credit {

	protected Scene scene;
	protected Stage st;
	// protected ScrollPane root;

	public Credit(Stage stage, Menu m, double scale) {
		st = stage;

		// ---Modele---
		// --------------------------------------------------------------

		BorderPane root = new BorderPane();
		root.setMinWidth(1000 * 1 + 2 * 50);
		root.setMinHeight(600 * 1);
		root.setMaxWidth(1000 * 1 + 2 * 50);
		root.setMaxHeight(600 * 1);

		HBox topbar = new HBox();

		Label titre = new Label("SHANNON    PONG    VOUS    A    ETE    PRESENTE    PAR :");
		titre.setPadding(new Insets(0, 0, 0, 100));
		titre.setId("titreCredit");

		Button retour = new Button();
		Image imageRetour = new Image(getClass().getResource("/image/back.png").toExternalForm());
		ImageView imageviewRetour = new ImageView(imageRetour);
		imageviewRetour.setFitHeight(40);
		imageviewRetour.setFitWidth(40);
		retour.setGraphic(imageviewRetour);
		retour.setPrefSize(40, 40);

		// titre.setLayoutX(root.getWidth()/2);
		// titre.setAlignment(Pos.CENTER);

		VBox labelbox = new VBox(30);

		Label l1 = new Label("Georges   Lecomte");
		Label l2 = new Label("Lea   Benoiton");
		Label l3 = new Label("Anais   Djoni   Lutala   Mavua");
		Label l4 = new Label("Alec   Martinez");
		Label l5 = new Label("Antoinette   Fourmond");
		Label l6 = new Label("Ronen   Shay");
		Label l7 = new Label("Manel   Belguenbour");
		topbar.getChildren().addAll(retour, titre);

		labelbox.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7);
		root.setCenter(labelbox);
		root.setTop(topbar);
		labelbox.setAlignment(Pos.CENTER);

		// --------------------------------------------------------------

		// ---Controlleur---
		// --------------------------------------------------------------

		retour.setOnAction((event) -> {
			Credit.this.st.setScene(m.getScene());
		});

		// --------------------------------------------------------------

		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
		stage.setScene(scene);
	}

	public void setCredit() {
		st.setScene(scene);
	}

}
