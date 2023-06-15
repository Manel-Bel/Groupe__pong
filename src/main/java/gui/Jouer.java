package gui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Ball;
import model.Court;
import model.Player;
import model.RacketController;
import model.Son;

public class Jouer {

    private GridPane root;
    private Scene gameScene;
    Player[] players;
    Parametres parametres;
    Court court;
    GameView gameView;
    public int bg;

    public Jouer(Stage primaryStage, Menu menu, Parametres p, boolean v2, boolean infini, boolean bot, double scale,
            int nbrManches) {
        if (infini) {
            Ball.setInfini(true);
            GameView.setInfini(true);
            Player.setInfini(true);
            Court.setInfini(true);
        } else {
            Ball.setInfini(false);
            GameView.setInfini(false);
            Player.setInfini(false);
            Court.setInfini(false);
        }
        Son.playAound();

        root = new GridPane(); // GridPane permet de créer un "canvas " avce un "gridlyout" sur lequel on
                               // pourra dessiner

        gameScene = new Scene(root); // le type scene représente tous les éléments contenus dans l'application
        // pourra ensuite dessiner à l'aide du type Pane
        players = new Player[4];
        players[0] = new Player("Joueur A");
        players[1] = new Player("Joueur B");
        if (v2) {
            players[2] = new Player("Joueur C");
            players[3] = new Player("Joueur D");
        }
        parametres = p;

        gameScene.setOnKeyPressed(ev -> {
            if (!bot) {
                if (ev.getCode() == parametres.getUpJ1()) {
                    if (players[0].getInverse()) {
                        players[0].setState(RacketController.State.GOING_DOWN);
                    } else
                        players[0].setState(RacketController.State.GOING_UP);
                }

                if (ev.getCode() == parametres.getDownJ1()) {
                    if (players[0].getInverse()) {
                        players[0].setState(RacketController.State.GOING_UP);
                    } else
                        players[0].setState(RacketController.State.GOING_DOWN);
                }
            }
            if (ev.getCode() == parametres.getUpJ2()) {
                if (players[1].getInverse()) {
                    players[1].setState(RacketController.State.GOING_DOWN);
                } else
                    players[1].setState(RacketController.State.GOING_UP);
            }
            if (ev.getCode() == parametres.getDownJ2()) {
                if (players[1].getInverse()) {
                    players[1].setState(RacketController.State.GOING_UP);
                } else
                    players[1].setState(RacketController.State.GOING_DOWN);
            }
            if (v2 && ev.getCode() == parametres.getUpJ3()) {
                players[2].setState(RacketController.State.GOING_UP);
            }
            if (v2 && ev.getCode() == parametres.getDownJ3()) {
                players[2].setState(RacketController.State.GOING_DOWN);
            }
            if (v2 && ev.getCode() == parametres.getUpJ4()) {
                players[3].setState(RacketController.State.GOING_UP);
            }
            if (v2 && ev.getCode() == parametres.getDownJ4()) {
                players[3].setState(RacketController.State.GOING_DOWN);
            }
            if (ev.getCode() == parametres.getAttackJ1()) {
                this.attack(players[0]);
                players[0].resetPowers();
            }
            if (ev.getCode() == parametres.getAttackJ2()) {
                this.attack(players[1]);
                players[1].resetPowers();
            }
            if (ev.getCode() == parametres.getDefenseJ1()) {
                this.defend(players[0]);
                players[0].resetPowers();
            }
            if (ev.getCode() == parametres.getDefenseJ2()) {
                this.defend(players[1]);
                players[1].resetPowers();
            }
        });
        gameScene.setOnKeyReleased(ev -> {
            if (!bot) {
                if (ev.getCode() == parametres.getUpJ1()) {
                    if (players[0].getState() == RacketController.State.GOING_UP
                            || players[0].getState() == RacketController.State.GOING_DOWN)
                        players[0].setState(RacketController.State.IDLE);
                }
                if (ev.getCode() == parametres.getDownJ1()) {
                    if (players[0].getState() == RacketController.State.GOING_UP
                            || players[0].getState() == RacketController.State.GOING_DOWN)
                        players[0].setState(RacketController.State.IDLE);
                }
            }
            if (ev.getCode() == parametres.getUpJ2()) {
                if (players[1].getState() == RacketController.State.GOING_UP
                        || players[1].getState() == RacketController.State.GOING_DOWN)
                    players[1].setState(RacketController.State.IDLE);
            }
            if (ev.getCode() == parametres.getDownJ2()) {
                if (players[1].getState() == RacketController.State.GOING_UP
                        || players[1].getState() == RacketController.State.GOING_DOWN)
                    players[1].setState(RacketController.State.IDLE);
            }
            if (v2 && ev.getCode() == parametres.getUpJ3()) {
                if (players[2].getState() == RacketController.State.GOING_UP)
                    players[2].setState(RacketController.State.IDLE);
            }
            if (v2 && ev.getCode() == parametres.getDownJ3()) {
                if (players[2].getState() == RacketController.State.GOING_DOWN)
                    players[2].setState(RacketController.State.IDLE);
            }
            if (v2 && ev.getCode() == parametres.getUpJ4()) {
                if (players[3].getState() == RacketController.State.GOING_UP)
                    players[3].setState(RacketController.State.IDLE);
            }
            if (v2 && ev.getCode() == parametres.getDownJ4()) {
                if (players[3].getState() == RacketController.State.GOING_DOWN)
                    players[3].setState(RacketController.State.IDLE);
            }
        });
        court = new Court(players, 1000, 600, parametres, nbrManches, v2, bot);
        // crée un terrain de 1000*600 pixels contenant les raquettes playerA et playerB

        gameView = new GameView(court, primaryStage, gameScene, menu, 1., menu.getbgD(), v2, bot,
                nbrManches);

        // instancier GameView pour dessiner les elements avec une grandeure de 1*scale
        // primaryStage.setScene(gameScene);
        primaryStage.setTitle("PONG");

        // primaryStage.show(); // affiche à l'écran les éléments contenus dans
        gameView.animate(); // permet d'animer les éléments (la balle et les raquettes)
        root.requestFocus();
    }

    public Scene getScene() {
        return this.gameScene;
    }

    public void setScene(Scene s) {
        this.gameScene = s;
    }

    public GameView getView() {
        return gameView;
    }

    public void defend(Player p) { // Appeler ici les fonction pour activer les bonus
        if (p.getBonus() != null) {
            switch (p.getBonus()) {
                case GRAND:
                    if (p.equals(players[0])) {
                        court.setRacketSize1(125.);
                        players[0].setCompteurTaille();
                    } else {
                        court.setRacketSize2(125.);
                        players[1].setCompteurTaille();
                    }
                    break;
                case BOOST:
                    if (p.equals(players[0])) {
                        court.setRacketSpeed1(400.);
                        players[0].setCompteurVitesse();
                    } else {
                        court.setRacketSpeed2(400.);
                        players[1].setCompteurVitesse();
                    }
                    break;
                case SMASH:
                    p.setSmash(true);
                    break;
                case REBON:
                    court.rebon();
                    break;
            }
        }
    }

    public void attack(Player p) { // Appeler ici les fonction pour activer les malus
        if (p.getMalus() != null) {
            switch (p.getMalus()) {
                case PETIT:
                    if (p.equals(players[0])) {
                        court.setRacketSize2(75.);
                        players[1].setCompteurTaille();
                    } else {
                        court.setRacketSize1(75.);
                        players[0].setCompteurTaille();
                    }
                    break;
                case RALENTI:
                    if (p.equals(players[0])) {
                        court.setRacketSpeed2(200.);
                        players[1].setCompteurVitesse();
                    } else {
                        court.setRacketSpeed1(200.);
                        players[0].setCompteurVitesse();
                    }
                    break;
                case INVERSE:
                    if (p.equals(players[0])) {
                        players[1].setInverse();
                    } else
                        players[0].setInverse();
                    break;
                case INVISIBLE:
                    gameView.invisible();
                    court.invisible();
                    break;
            }
        }
    }

}
