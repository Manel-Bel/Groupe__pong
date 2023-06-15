package gui;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.Court;
import model.Raquette;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Ball;
import model.Son;

public class GameView {
    private final Court court;
    private final Pane gameRoot; // main node of the game
    private GridPane root;
    private GridPane nav;
    private final double scale;
    private final double xMargin = 50.0; // pixels
    // children of the game main node
    private Raquette racketA, racketB, racketC, racketD;
    private Ball ball;
    private Pausep pause;
    private HeaderGame header;
    private Line delem;
    private Scene scene;
    private Menu menu;
    private Stage gameStage;
    private ImageView bg;

    private Animation timer;
    private Button pauseBtn;
    private boolean v2;
    private static boolean infini = false;
    private Jauge jauge1, jauge2;
    private boolean bot;
    private int nbrManches;

    /**
     * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout
     *              ce qu'il y a dessus)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera
     *              affiché
     * @param scale le facteur d'échelle entre les distances du modèle et le nombre
     *              de pixels correspondants dans la vue
     */
    public GameView(Court court, Stage stage, Scene sc, Menu m, double scale, ImageView bg, boolean v2,
            boolean bot, int nbrManches) {
        this.v2 = v2;
        this.court = court;
        this.scale = scale;
        this.scene = sc;
        this.menu = m;
        this.gameStage = stage;
        this.bg = bg;
        this.bot = bot;
        this.nbrManches = nbrManches;

        /*
         * permettre aux joueurs de faire une pause à la partie et accéder aux
         * parametres
         * 
         * @autor:Manel
         */

        pauseBtn = new Button();
        pauseBtn.setPrefSize(20, 20);
        Image i = new Image(getClass().getResource("/image/pause.png").toExternalForm());
        ImageView view = new ImageView(i);
        view.setFitHeight(35);
        view.setFitWidth(35);
        pauseBtn.setGraphic(view);
        pauseBtn.setStyle("-fx-background-color: black");
        pauseBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            /*
             * clique sur pause
             * il ouvre la fenetre de pause front
             * il arrte l'animation de gameview
             */
            public void handle(ActionEvent event) {
                if (event.getSource() == pauseBtn) {
                    timer.stop();
                    pause = new Pausep();
                    Son.pauseMusic();
                    pauseBtn.setDisable(true);
                }
            }
        });
        // (court.getWidth() / 2 + 25)
        this.delem = new Line((court.getWidth() / 2 + 25), 10, (court.getWidth() / 2 + 25), court.getHeight() + 30);
        delem.getStrokeDashArray().addAll(30.0);
        delem.setStrokeWidth(10);
        delem.setStroke(Color.WHITE);

        this.racketA = new Raquette(scale * ((infini) ? court.getHeight() + 35 : court.getRacketSize1())); // changercourt.getWidth()
                                                                                                           // si on veut
                                                                                                           // pas
                                                                                                           // qu'elle
                                                                                                           // fasse
        // toute la taille du terrain
        racketA.getRacket().setX(xMargin - racketA.getThickness());
        racketA.getRacket().setY(court.getRacketA() * scale);
        racketA.setCouleur(this.menu.getRD1());
        this.racketB = new Raquette(court.getRacketSize2() * scale);
        racketB.getRacket().setX(court.getWidth() * scale + xMargin);
        racketB.getRacket().setY(court.getRacketB() * scale);
        racketB.setCouleur(this.menu.getRD2());

        if (v2) {
            this.racketC = new Raquette(court.getRacketSize1() * scale);
            racketC.getRacket().setX(xMargin - racketC.getThickness() + 100);
            racketC.getRacket().setY(court.getRacketC() * scale);
            racketC.setCouleur(this.menu.getRD3());

            this.racketD = new Raquette(court.getRacketSize1() * scale);
            racketD.getRacket().setX(court.getWidth() * scale + xMargin - 100);
            racketD.getRacket().setY(court.getRacketD() * scale);
            racketD.setCouleur(this.menu.getRD4());
        }

        ball = court.getBall();
        ball.setColor(menu.getbB());

        header = new HeaderGame(court.getPlayerA().getName(), court.getPlayerB().getName(), this.nbrManches);

        jauge1 = new Jauge(court.getWidth(), court.getHeight(), true);
        jauge2 = new Jauge(court.getWidth(), court.getHeight(), false);

        // on init notre root du jeu avce la balle et les raquettes
        this.gameRoot = new Pane();
        gameRoot.setMinWidth(court.getWidth() * scale + 2 * xMargin);
        gameRoot.setMinHeight(court.getHeight() * scale);
        gameRoot.getChildren().addAll(racketA.getRacket(), racketB.getRacket(), ball.getBall(), delem);
        setRootGameBg();
        if (v2) {
            gameRoot.getChildren().addAll(racketC.getRacket(), racketD.getRacket());
            header.setNameA("Team A");
            header.setNameB("Team B");

        }
        // la partie affichage du header et les noms des deux parties
        // avec gridpane pour les repartir en 4 colonnes
        nav = new GridPane();
        nav.add(pauseBtn, 0, 0);
        nav.add(header.getNameA(), 3, 0);
        nav.add(header.getScoreText(), 4, 0);
        nav.add(header.getNameB(), 5, 0);

        nav.add(header.getMancheA(), 0, 1);
        nav.add(header.getBonusA(), 1, 1);
        nav.add(header.getMalusA(), 2, 1);
        nav.add(jauge1.getJauge(), 3, 1);
        nav.add(jauge2.getJauge(), 5, 1);
        nav.add(header.getMalusB(), 6, 1);
        nav.add(header.getBonusB(), 7, 1);
        nav.add(header.getMancheB(), 8, 1);
        nav.setMinHeight(70);
        nav.setMinWidth(court.getWidth() * scale + 2 * xMargin);
        nav.setHgap(62 * scale);
        nav.setStyle("-fx-background-color: black");

        // le root de la fenetre du jeu
        root = (GridPane) scene.getRoot();
        root.setMinHeight(nav.getHeight() + gameRoot.getHeight());
        root.add(nav, 0, 0);
        root.add(gameRoot, 0, 1);
        root.setVgap(2);
        this.scene.setRoot(root);
        scene.getStylesheets().add(getClass().getResource("/css/GameView.css").toExternalForm());
        root.setStyle("-fx-background-color: white");

        root.requestFocus();
        stage.setScene(sc);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() { // si on ferme la fenetre non par le bouton
            // quitter on reprendra le jeu
            @Override
            public void handle(WindowEvent e) {
                Son.stopMusic();
                if (pause != null) {
                    pause.pauseStage.close();
                }
            }
        });
        // System.out.println("pausebtn wi " + pauseBtn.getWidth() + "\npausebtn layoutX
        // " + pauseBtn.getLayoutX()
        // + "\njauge1+width "
        // + jauge1.getJauge().getWidth() + "\nscoretext textAlig"
        // + header.getScoreText().getTextAlignment() + "\nscoretext layoutX "
        // + header.getScoreText().getLayoutX() + " "
        // + header.getNameB().getStrokeWidth() + " " +
        // header.getMancheB().getStrokeWidth());

    }

    public void animate() { // cree une nv animation
        this.timer = new Animation();
        this.timer.start();
    }

    public void setRootGameBg() {
        if (bg == null)
            gameRoot.setStyle("-fx-background-color: black");
        else {
            // gameRoot.getChildren().get(3).setVisible(false); // la ligne
            BackgroundImage backgroundimage;
            Background background;
            backgroundimage = new BackgroundImage(bg.getImage(),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            background = new Background(backgroundimage);
            gameRoot.setBackground(background);
        }

    }

    public static void setInfini(boolean b) {
        infini = b;
    }

    public void invisible() {
        gameRoot.getChildren().get(2).setVisible(false);
    }

    class Animation extends AnimationTimer {
        long last = 0;

        @Override
        public void handle(long now) {
            root.requestFocus();
            if (last == 0) { // ignore the first tick, just compute the first deltaT
                last = now;
                return;
            }
            court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
            last = now;
            racketA.getRacket().setY(court.getRacketA() * scale);
            racketB.getRacket().setY(court.getRacketB() * scale);
            if (v2) {
                racketC.getRacket().setY(court.getRacketC() * scale);
                racketD.getRacket().setY(court.getRacketD() * scale);
            }
            if (!infini) {
                racketA.setSize(court.getRacketSize1());
            }
            racketB.setSize(court.getRacketSize2());
            ball.getBall().setCenterX(ball.getBallX() * scale + xMargin);
            ball.getBall().setCenterY(ball.getBallY() * scale);
            if (!court.getInvisible()) {
                gameRoot.getChildren().get(2).setVisible(true);
            }
            header.updateScore(court.getPlayerA().getScore(), court.getPlayerB().getScore());
            header.updateNbManchA(court.getPlayerA().getScoreTour(), nbrManches);
            header.updateNbManchB(court.getPlayerB().getScoreTour(), nbrManches);
            header.updateBonus(court.getPlayerA().getNameBonus(), court.getPlayerB().getNameBonus());
            header.updateMalus(court.getPlayerA().getNameMalus(), court.getPlayerB().getNameMalus());
            jauge1.update(court.getPlayerA().getJauge());
            jauge2.update(court.getPlayerB().getJauge());
            int resu = court.finJeu();
            resu = (infini) ? -1 : resu;
            if (resu != -1) { // la fin de la partie
                gameRoot.getChildren().get(3).setVisible(false); // la ligne
                gameRoot.getChildren().get(2).setVisible(false);
                pauseBtn.setDisable(true);
                Animation.this.stop(); // pause
                finJeu(resu);
            }
        }

        // reset le jeu
        public void reset() {
            this.stop();
            this.last = 0;
            court.resetJeu();
        }
    }

    public Scene getScene() {
        return this.scene;
    }

    public void finJeu(int resu) {
        Son.stopMusic();
        Text messageFin = new Text();
        messageFin.setId("text");
        // " \u00E0" + " gagn\u00E9"
        switch (resu) {
            case 0:
                messageFin.setText(header.getNameA().getText() + " a gagne");
                messageFin.setFont(new Font("ARCADECLASSIC", 30));
                break;
            case 1:
                messageFin.setText("Oups! Ex aequo");
                break;
            case 2:
                messageFin.setText(header.getNameB().getText() + " a gagne");
                break;
        }
        messageFin.setLayoutX(header.getScoreText().getLayoutX() - 70);
        messageFin.setLayoutY(court.getHeight() / 2 - 50);
        messageFin.setFill(Color.WHITE);

        Button reprendreBtn = new Button("Nouvelle partie");
        reprendreBtn.setId("button");
        reprendreBtn.setLayoutX(messageFin.getLayoutX() + 30);
        reprendreBtn.setLayoutY(messageFin.getLayoutY() + 25);

        Button quitBtn = new Button("Quitter");
        quitBtn.setId("button");
        reprendreBtn.setId("button");
        quitBtn.setLayoutX(header.getScoreText().getLayoutX());
        quitBtn.setLayoutY(reprendreBtn.getLayoutY() + 50);

        // controleurs
        reprendreBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Son.playAound();
                if (event.getSource() == reprendreBtn) {// which objet causes the event
                    court.getPlayerA().resetJauge();
                    court.getPlayerB().resetJauge();
                    court.getPlayerA().resetPowers();
                    court.getPlayerB().resetPowers();
                    gameRoot.getChildren().removeAll(reprendreBtn, messageFin, quitBtn);
                    if (bg == null)
                        gameRoot.getChildren().get(3).setVisible(true);
                    gameRoot.getChildren().get(2).setVisible(true);
                    animate();
                    pauseBtn.setDisable(false);
                }
            }
        });

        quitBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == quitBtn) {
                    GameView.this.gameStage.setScene(GameView.this.menu.getScene());
                }
            }
        });
        gameRoot.getChildren().addAll(reprendreBtn, messageFin, quitBtn);
        quitBtn.setAlignment(Pos.CENTER);
    }

    class Pausep {
        private Stage pauseStage;
        private Scene pauseSc;
        private VBox root;

        // la classe paune nous crée un nv stage avec sa scène et ses boutons
        public Pausep() {
            this.root = new VBox(10);
            this.root.setMinWidth(250);
            this.root.setMinHeight(250);
            this.root.setAlignment(Pos.CENTER);

            Label titre = new Label("Pause");
            titre.setId("titrelabel");

            Button nvBtn = new Button("Nouvelle Partie");
            nvBtn.setMaxSize(170, 170);
            nvBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                /*
                 * clique sur Nouvelle partie
                 * il ferme la fenetre de pause
                 * il lance une nouvelle partie du jeu
                 */
                public void handle(ActionEvent event) {
                    Son.playAound();
                    if (event.getSource() == nvBtn) {
                        GameView.this.court.getPlayerA().resetJauge();
                        GameView.this.court.getPlayerB().resetJauge();
                        GameView.this.court.getPlayerA().resetPowers();
                        GameView.this.court.getPlayerB().resetPowers();
                        Pausep.this.pauseStage.close();
                        GameView.this.timer.reset();
                        pauseBtn.setDisable(false);
                        GameView.this.animate();

                    }
                }
            });

            Button reBtn = new Button("Reprendre");
            reBtn.setMaxSize(170, 170);
            reBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                /*
                 * clique sur reprendre
                 * il ferme la fenetre de pause
                 * il reprend l'animation de gameview
                 */
                public void handle(ActionEvent event) {
                    Son.play();
                    if (event.getSource() == reBtn) {
                        pauseBtn.setDisable(false);
                        Pausep.this.pauseStage.close();
                        GameView.this.animate();
                    }
                }
            });

            Button quitBtn = new Button("Quitter");
            quitBtn.setMaxSize(170, 170);
            quitBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                /*
                 * quand on clique sur quitter il met l'animation en pause pourle moment
                 * il ferme la fenetre de pause
                 * il switch les scenes de gameview avec menu
                 */
                public void handle(ActionEvent event) {
                    if (event.getSource() == quitBtn) {
                        GameView.this.court.getPlayerA().resetJauge();
                        GameView.this.court.getPlayerB().resetJauge();
                        GameView.this.court.getPlayerA().resetPowers();
                        GameView.this.court.getPlayerB().resetPowers();
                        Pausep.this.pauseStage.close();
                        GameView.this.timer.stop();
                        GameView.this.gameStage.setScene(GameView.this.menu.getScene());
                    }
                }
            });

            this.root.getChildren().addAll(titre, nvBtn, reBtn, quitBtn);
            this.pauseSc = new Scene(this.root);
            pauseSc.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
            this.pauseStage = new Stage();
            pauseStage.setTitle("Pause");
            pauseStage.setScene(pauseSc);
            pauseStage.setResizable(false);
            pauseStage.centerOnScreen();
            pauseStage.toFront();
            pauseStage.show();
            pauseStage.setOnCloseRequest(new EventHandler<WindowEvent>() { // si on ferme la fenetre non par le bouton
                                                                           // quitter on reprendra le jeu
                @Override
                public void handle(WindowEvent e) {
                    Son.play();
                    pauseBtn.setDisable(false);
                    GameView.this.animate();
                }
            });
        }

    }
}
