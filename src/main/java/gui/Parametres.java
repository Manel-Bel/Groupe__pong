package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Son;

public class Parametres {
	private boolean ouvert; // permet de savoir si les paramètres sont ouverts ou non
	private Scene scene, oldScene; // pour créer la scène des paramètres qaund on switch dessus
	private Button reset,touche,retour;
	private Touches t;
	private CheckBox cbSon, cbMusique, cbEffets;
	private Label son, musique, effets;
	private Stage s;

	public Parametres() {
		ouvert = false; // les paramètres sont fermés quand on lance le jeu
		VBox root = new VBox(50); // pour créer la fenêtre des paramètres
		root.setMinWidth(1000 * 1 + 2 * 50);
		root.setMinHeight(600 * 1);
		t = new Touches();
		
		HBox topbar = new HBox();
		retour = new Button();
		Image imageRetour = new Image(getClass().getResource("/image/back.png").toExternalForm());
		ImageView imageviewRetour = new ImageView(imageRetour);
		imageviewRetour.setFitHeight(40);
		imageviewRetour.setFitWidth(40);
		retour.setGraphic(imageviewRetour);
		retour.setPrefSize(40, 40);
		topbar.getChildren().addAll(retour);
		
		HBox sonBox = new HBox(50);
		son = new Label("Son");
		son.setId("text");
		cbSon = new CheckBox();
		cbSon.setId("check");
		cbSon.setSelected(true);
		sonBox.getChildren().addAll(son, cbSon);
		sonBox.setAlignment(Pos.CENTER);
		
		HBox musiqueBox = new HBox(50);
		musique = new Label("Musique");
		musique.setId("text");
		cbMusique = new CheckBox();
		cbMusique.setId("check");
		cbMusique.setSelected(true);
		musiqueBox.getChildren().addAll(musique, cbMusique);
		musiqueBox.setAlignment(Pos.CENTER);

		
		HBox effetsBox = new HBox(50);
		effets = new Label("Effets sonores");
		effets.setId("text");
		cbEffets = new CheckBox();
		cbEffets.setId("check");
		cbEffets.setSelected(true);
		effetsBox.getChildren().addAll(effets, cbEffets);
		effetsBox.setAlignment(Pos.CENTER);

		
		touche = new Button("Modifier   les   touches");

		reset = new Button("Réinitialiser   les   paramètres");
		
		// ---Controlleur---
		
		touche.setOnAction((event) -> {
			t.startTouches();
		});
		
		reset.setOnAction((event) -> {
			reset();
		});
		
		retour.setOnAction((event) -> {
			ouvert = false;
			Parametres.this.s.setScene(oldScene);
		});
		
		cbSon.setOnAction((event) -> {
			if(cbSon.isSelected()) Son.changeSon(true);
			else Son.changeSon(false);
		});
		
		cbMusique.setOnAction((event) -> {
			if(cbMusique.isSelected()) Son.changeMusique(true);
			else Son.changeMusique(false);
		});
		
		cbEffets.setOnAction((event) -> {
			if(cbEffets.isSelected()) Son.changeEffets(true);
			else Son.changeEffets(false);
		});
		
		// --------------------------------------------------------------
		
		root.getChildren().addAll(topbar, sonBox, musiqueBox, effetsBox, touche, reset); // pour mettre les objets crées dans la scene
																											
		scene = new Scene(root); // initialiser la scène paramètre avec la fenêtre qui vient d'être crée
		scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
	}

	public void startParametres(Stage primaryStage, Scene oldScene){
		ouvert = true;
		s = primaryStage;
		this.oldScene = oldScene;
		s.setScene(scene);
	}

	public boolean getOuvert() { // savoir dans les autres classe si les paramètres sont ouverts
		return this.ouvert;
	}

	public KeyCode getUpJ1() {
		return t.keys[0];
	}

	public KeyCode getDownJ1() {
		return t.keys[1];
	}

	public KeyCode getUpJ2() {
		return t.keys[2];
	}

	public KeyCode getDownJ2() {
		return t.keys[3];
	}

	public KeyCode getUpJ3() {
		return t.keys[4];
	}

	public KeyCode getDownJ3() {
		return t.keys[5];
	}

	public KeyCode getUpJ4() {
		return t.keys[6];
	}

	public KeyCode getDownJ4() {
		return t.keys[7];
	}

	public KeyCode getAttackJ1() {
		return t.keys[8];
	}

	public KeyCode getDefenseJ1() {
		return t.keys[9];
	}

	public KeyCode getAttackJ2() {
		return t.keys[10];
	}

	public KeyCode getDefenseJ2() {
		return t.keys[11];
	}

	private void reset() {
		t.reset();
		cbSon.setSelected(true);
		cbMusique.setSelected(true);
		cbEffets.setSelected(true);
		Son.reset();
	}

	// j'ai ajouté dans App une variable paramètres, et dans GameView et Court j'ai
	// ajouté un attribut paramètres et changé les contructeurs


	public class Touches {
		private Scene scene;
		protected KeyCode[] keys;
		private Button haut1, bas1, haut2, bas2, haut3, bas3, haut4, bas4, attaque1, defense1, attaque2, defense2;

		public Touches(){
			keys = new KeyCode[12];
			keys[0] = KeyCode.Z;
			keys[1] = KeyCode.S;
			keys[2] = KeyCode.UP;
			keys[3] = KeyCode.DOWN;
			keys[4] = KeyCode.CONTROL;
			keys[5] = KeyCode.ALT;
			keys[6] = KeyCode.O;
			keys[7] = KeyCode.L;
			keys[8] = KeyCode.D;
			keys[9] = KeyCode.Q;
			keys[10] = KeyCode.LEFT;
			keys[11] = KeyCode.RIGHT;
			
			Label key1 = new Label("Touche   haut   joueur 1 : ");
			Label key2 = new Label("Touche   bas   joueur 1 : ");
			Label key3 = new Label("Touche   haut   joueur 2 : ");
			Label key4 = new Label("Touche   bas   joueur 2 : ");
			Label key5 = new Label("Touche   haut   joueur 3 : ");
			Label key6 = new Label("Touche   bas   joueur 3 : ");
			Label key7 = new Label("Touche   haut   joueur 4 : ");
			Label key8 = new Label("Touche   bas   joueur 4 : ");
			Label key9 = new Label("Touche   attaque   joueur 1 : ");
			Label key10 = new Label("Touche   défense   joueur 1 : ");
			Label key11 = new Label("Touche   attaque   joueur 2 : ");
			Label key12 = new Label("Touche   défense   joueur 2 : ");
			
			HBox topbar = new HBox();
			Button retour = new Button();
			Image imageRetour = new Image(getClass().getResource("/image/back.png").toExternalForm());
			ImageView imageviewRetour = new ImageView(imageRetour);
			imageviewRetour.setFitHeight(40);
			imageviewRetour.setFitWidth(40);
			retour.setGraphic(imageviewRetour);
			retour.setPrefSize(40, 40);
			topbar.getChildren().addAll(retour);
			
			haut1 = new Button(keys[0].getName());
			bas1 = new Button(keys[1].getName());
			haut2 = new Button(keys[2].getName());
			bas2 = new Button(keys[3].getName());
			haut3 = new Button(keys[4].getName());
			bas3 = new Button(keys[5].getName());
			haut4 = new Button(keys[6].getName());
			bas4 = new Button(keys[7].getName());
			attaque1 = new Button(keys[8].getName());
			defense1 = new Button(keys[9].getName());
			attaque2 = new Button(keys[10].getName());
			defense2 = new Button(keys[11].getName());
			
			BorderPane root = new BorderPane();
			root.setMinWidth(1000 * 1 + 2 * 50);
			root.setMinHeight(600 * 1);
			root.setMaxWidth(1000 * 1 + 2 * 50);
			root.setMaxHeight(600 * 1);
			ScrollPane scrollpane = new ScrollPane();
			scrollpane.setVbarPolicy(ScrollBarPolicy.NEVER);
			
			VBox content = new VBox();
			content.getChildren().addAll(key1, haut1, key2, bas1, key3, haut2, key4, bas2, key5, haut3, key6, bas3, key7, haut4, key8, bas4, 
					key9, attaque1, key10, defense1, key11, attaque2, key12, defense2);
			content.setPadding(new Insets(0, 0, 0, 150));
			
			scrollpane.setContent(content);
			scrollpane.setPannable(true);
			
			root.setTop(topbar);
			root.setCenter(scrollpane);

			
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
			
			// ---Controlleur---

			retour.setOnAction((event) -> {
				Parametres.this.s.setScene(Parametres.this.scene);
			});

			haut1.setOnAction((event) -> {
				haut1.setText("appuyez sur la nouvelles touche");
				modifTouche(haut1, 0);
			});
			bas1.setOnAction((event) -> {
				bas1.setText("appuyez sur la nouvelles touche");
				modifTouche(bas1, 1);
			});
			haut2.setOnAction((event) -> {
				haut2.setText("appuyez sur la nouvelles touche");
				modifTouche(haut2, 2);
			});
			bas2.setOnAction((event) -> {
				bas2.setText("appuyez sur la nouvelles touche");
				modifTouche(bas2, 3);
			});
			haut3.setOnAction((event) -> {
				haut3.setText("appuyez sur la nouvelles touche");
				modifTouche(haut3, 4);
			});
			bas3.setOnAction((event) -> {
				bas3.setText("appuyez sur la nouvelles touche");
				modifTouche(bas3, 5);
			});
			haut4.setOnAction((event) -> {
				haut4.setText("appuyez sur la nouvelles touche");
				modifTouche(haut4, 6);
			});
			bas4.setOnAction((event) -> {
				bas4.setText("appuyez sur la nouvelles touche");
				modifTouche(bas4, 7);
			});
			attaque1.setOnAction((event) -> {
				attaque1.setText("appuyez sur la nouvelles touche");
				modifTouche(attaque1, 8);
			});
			defense1.setOnAction((event) -> {
				defense1.setText("appuyez sur la nouvelles touche");
				modifTouche(defense1, 9);
			});
			attaque2.setOnAction((event) -> {
				attaque2.setText("appuyez sur la nouvelles touche");
				modifTouche(attaque2, 10);
			});
			defense2.setOnAction((event) -> {
				defense2.setText("appuyez sur la nouvelles touche");
				modifTouche(defense2, 11);
			});
			
			// --------------------------------------------------------------
			
		}

		public void startTouches() {
			Parametres.this.s.setScene(scene);			
			scene.setOnKeyPressed(ev -> {});
		}

		private void modifTouche(Button b, int i) {
			scene.setOnKeyPressed(ev -> {
				KeyCode tmp = ev.getCode();
				if (!(dejaPris(tmp))) {
					keys[i] = tmp;
				}
				b.setText(keys[i].getName());
				startTouches();
			});
		}

		private boolean dejaPris(KeyCode k) {
			for (int i = 0; i < keys.length; i++) {
				if (k == keys[i]) {
					return true;
				}
			}
			return false;
		}

		public void reset(){
			keys[0] = KeyCode.Z;
			haut1.setText(keys[0].getName());
			keys[1] = KeyCode.S;
			bas1.setText(keys[1].getName());
			keys[2] = KeyCode.UP;
			haut2.setText(keys[2].getName());
			keys[3] = KeyCode.DOWN;
			bas2.setText(keys[3].getName());
			keys[4] = KeyCode.CONTROL;
			haut3.setText(keys[4].getName());
			keys[5] = KeyCode.ALT;
			bas3.setText(keys[5].getName());
			keys[6] = KeyCode.O;
			haut4.setText(keys[6].getName());
			keys[7] = KeyCode.L;
			bas4.setText(keys[7].getName());
			keys[8] = KeyCode.D;
			attaque1.setText(keys[8].getName());
			keys[9] = KeyCode.Q;
			defense1.setText(keys[9].getName());
			keys[10] = KeyCode.LEFT;
			attaque2.setText(keys[10].getName());
			keys[11] = KeyCode.RIGHT;
			defense2.setText(keys[11].getName());
		}
	}

}
