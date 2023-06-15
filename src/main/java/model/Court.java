package model;

import java.util.Random;

import gui.Parametres;

public class Court { // représente le "terrain" du jeu
    // instance parameters
    private final Player[] players;
    private final double width, height; // m
    private double racketSpeed1, racketSpeed2; // m/s
    private double racketSize1, racketSize2; // m
    private final Ball ball;
    // instance state
    private double racketA, racketB, racketC, racketD; // m
    private Parametres parametres;
    private final int nbTour;
    private int nbTourCourant;
    private boolean v2;
    private static boolean infini = false;
    private boolean bot; // true = 1 vs bot
    private double tempsReaction = 1;

    public Court(Player[] players, double width, double height,
            Parametres parametres, int nbTour, boolean v2, boolean bot) {
        // on rend les variables, de types declarees player
        this.v2 = v2;
        this.bot = bot;
        this.players = new Player[4];
        this.players[0] = players[0];
        this.players[1] = players[1];
        if (v2) {
            this.players[2] = players[2];
            this.players[3] = players[3];
        }
        this.width = width;
        this.height = height;
        this.parametres = parametres;
        this.ball = new Ball(10.0, 1.0, 50.0);
        this.nbTour = nbTour;
        this.nbTourCourant = 0;
        this.racketSpeed1 = v2 ? 225.0 : 300.0;
        this.racketSpeed2 = v2 ? 225.0 : 300.0;
        this.racketSize1 = v2 ? 75.0 : 100.0;
        this.racketSize2 = v2 ? 75.0 : 100.0;
        reset();
    }

    public static void setInfini(boolean b) {
        infini = b;
    }

    public double getTempsReaction() {
        return tempsReaction;
    }

    public void setTempsReaction() {
        double d = new Random().nextDouble();
        this.tempsReaction = d < 0.5 ? 0.5 : d;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRacketSize1() {
        return racketSize1;
    }

    public double getRacketSize2() {
        return racketSize2;
    }

    public void setRacketSize1(double d) {
        racketSize1 = d;
    }

    public void setRacketSize2(double d) {
        racketSize2 = d;
    }

    public void setRacketSpeed1(double d) {
        racketSpeed1 = d;
    }

    public void setRacketSpeed2(double d) {
        racketSpeed2 = d;
    }

    public double getRacketA() {
        return racketA;
    }

    public double getRacketB() {
        return racketB;
    }

    public double getRacketC() {
        return racketC;
    }

    public double getRacketD() {
        return racketD;
    }

    public Player getPlayerA() {
        return this.players[0];
    }

    public Player getPlayerB() {
        return this.players[1];
    }

    public Player getPlayerC() {
        return this.players[2];
    }

    public Player getPlayerD() {
        return this.players[3];
    }

    public Ball getBall() {
        return this.ball;
    }

    public int getNbtour() {
        return this.nbTour;
    }

    public boolean getInvisible() {
        return ball.getInvisible();
    }

    public void incTour() {
        this.nbTourCourant++;
    }

    public void resetNbtour() {
        this.nbTourCourant = 0;
    }

    private boolean finPartie() {
        if (infini) {
            return false;
        }
        return this.nbTour == this.nbTourCourant;
    }

    public int finJeu() {
        if (!this.finPartie()) {
            if (players[0].aGagne() || players[1].aGagne()) {
                this.incTour();
                if (players[0].aGagne()) {
                    players[0].incrScoreTour();
                } else {
                    players[1].incrScoreTour();
                }
                players[0].resetScore();
                players[1].resetScore();
            }
            return -1;
        } else {
            int resu;
            if (players[0].getScoreTour() > players[1].getScoreTour()) {
                resu = 0;
            } else {
                if (players[0].getScoreTour() == players[1].getScoreTour()) {
                    resu = 1;
                } else {
                    resu = 2;
                }
            }
            this.resetNbtour();
            this.getPlayerA().resetScore();
            this.getPlayerB().resetScore();
            players[0].resetScoreTour();
            players[1].resetScoreTour();
            return resu;
        }
    }

    public void update(double deltaT) {
        if (bot) {
            if (ball.getBallY() > racketA + 50) {
                racketA += racketSpeed1 * tempsReaction * deltaT;
                if (racketA + racketSize1 > height + 35)
                    racketA = height + 35 - racketSize1;
            }
            if (ball.getBallY() < racketA + 50) {
                racketA -= racketSpeed1 * tempsReaction * deltaT;
                if (racketA < 0.0)
                    racketA = 0.0;
            }
            double nextBallX = ball.getBallX() + deltaT * ball.getBallSpeedX();
            double nextBallY = ball.getBallY() + deltaT * ball.getBallSpeedY();

            if ((nextBallX > width && nextBallY > racketB && nextBallY < racketB + racketSize1)) { // change de vitesse
                                                                                                   // quand la balle
                                                                                                   // touche la raquette
                                                                                                   // du joueur
                setTempsReaction();
            }
        }
        if (!infini && !bot) {
            switch (players[0].getState()) { // déplace les raquettes selon la touche pressée
                case GOING_UP:
                    racketA -= racketSpeed1 * deltaT;
                    if (racketA < 0.0)
                        racketA = 0.0;
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    racketA += racketSpeed1 * deltaT;
                    if (racketA + racketSize1 > height + 35)
                        racketA = height + 35 - racketSize1;
                    break;
            }
        }
        switch (players[1].getState()) {
            case GOING_UP:
                racketB -= racketSpeed2 * deltaT;
                if (racketB < 0.0)
                    racketB = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                racketB += racketSpeed2 * deltaT;
                if (racketB + racketSize2 > height + 35)
                    racketB = height + 35 - racketSize2;
                break;
        }
        if (v2) {
            switch (players[2].getState()) {
                case GOING_UP:
                    racketC -= racketSpeed1 * deltaT;
                    if (racketC < 0.0)
                        racketC = 0.0;
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    racketC += racketSpeed1 * deltaT;
                    if (racketC + racketSize1 > height + 35)
                        racketC = height + 35 - racketSize1;
                    break;
            }
            switch (players[3].getState()) {
                case GOING_UP:
                    racketD -= racketSpeed1 * deltaT;
                    if (racketD < 0.0)
                        racketD = 0.0;
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    racketD += racketSpeed1 * deltaT;
                    if (racketD + racketSize1 > height + 35)
                        racketD = height + 35 - racketSize1;
                    break;
            }
        }
        if (players[0].getCompteurTaille() == 0) {
            racketSize1 = v2 ? 75. : 100.;
        }
        if (players[1].getCompteurTaille() == 0) {
            racketSize2 = v2 ? 75. : 100.;
        }
        if (players[0].getCompteurVitesse() == 0) {
            racketSpeed1 = v2 ? 225. : 300.;
        }
        if (players[1].getCompteurVitesse() == 0) {
            racketSpeed2 = v2 ? 225. : 300.;
        }
        if (!parametres.getOuvert()) {
            if (ball.updateBall(deltaT, width, height, racketA, racketB, racketC, racketD, racketSize1, players, v2))
                reset();
        }

    }

    void reset() {
        this.racketA = (infini) ? 0 : height / 2;
        this.racketB = height / 2;
        this.racketC = height / 2;
        this.racketD = height / 2;
        ball.reset(width, height);
    }

    public void resetJeu() {
        this.resetNbtour();
        this.getPlayerA().resetScore();
        this.getPlayerB().resetScore();
        players[0].resetScoreTour();
        players[1].resetScoreTour();
        // this.racketA = (infini) ? 0 : height / 2;
        // this.racketB = height / 2;
        // this.racketC = height / 2;
        // this.racketD = height / 2;
        // ball.reset(width, height);
        reset();
    }

    public void rebon() {
        ball.rebon();
    }

    public void invisible() {
        ball.invisible();
    }

}