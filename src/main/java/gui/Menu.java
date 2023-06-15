package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
	// les attributs de la classe menu
	double scale;
	private Label titre;
	// private Button jouer;
	// private Button equipes;
	private VBox root;
	private Button dressing;
	private Button parametre;
	private Button credit;
	private Button jeu;
	private Scene scene;
	private Stage menuStage;

	Jouer jouerC;
	Dressing d;
	Parametres p;
	MenuJeu mj;
	Credit cred;

	public Menu(Stage stage, double scal) throws Exception {
		this.scale = scal;
		this.menuStage = stage;
		p = new Parametres();

		this.root = new VBox(20);
		root.setMinWidth(1000 * scale + 2 * 50);
		root.setMinHeight(600 * scale);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.TOP_CENTER);

		titre = new Label("Shannon Pong");
		titre.setId("titrelabel");
		titre.setPadding(new Insets(0, 0, 100, 0));

		jeu = new Button("Jeu");
		jeu.setMaxSize(300, 300);
		jeu.setOnAction((event) -> {
			if (mj == null)
				mj = new MenuJeu(menuStage, Menu.this, 1);
			else {
				mj.setMenuJeu();
			}
		});

		this.dressing = new Button("Dressing");
		dressing.setMaxSize(300 * scale, 300 * scale);
		dressing.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// if (d == null) {
				d = new Dressing(menuStage, Menu.this, 1);
				d.setMenu(Menu.this);
				// } else {
				// System.out.println("d getscene");
				// Menu.this.setScene(d.getScene());
				// }

			}
		});

		this.parametre = new Button("Parametres");
		parametre.setMaxSize(300, 300);
		parametre.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				p.startParametres(menuStage, scene);
			}
		});

		this.credit = new Button("Credit");
		this.credit.setMaxSize(300, 300);
		credit.setOnAction((event) -> {
			if (cred == null)
				cred = new Credit(menuStage, Menu.this, 1);
			else {
				cred.setCredit();
			}
		});

		root.getChildren().addAll(titre, jeu, dressing, parametre, credit);

		this.scene = new Scene(root);

		menuStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());

	}

	public Scene getScene() {
		return this.scene;
	}

	public Parametres getP() {
		return p;
	}

	public void setScene(Scene s) {
		this.scene = s;
	}

	public ImageView getbgD() {
		return (d != null) ? d.getBg() : null;
	}

	public Color getbB() {
		return (d != null) ? d.getCB() : Color.WHITE;
	}

	public Color getRD1() {
		return (d != null) ? d.getCR1() : Color.WHITE;
	}

	public Color getRD2() {
		return (d != null) ? d.getCR2() : Color.WHITE;
	}

	public Color getRD3() {
		return (d != null) ? d.getCR3() : Color.WHITE;
	}

	public Color getRD4() {
		return (d != null) ? d.getCR3() : Color.WHITE;
	}
}
