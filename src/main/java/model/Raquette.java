package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Raquette {
    // private double racketSize = 100.0;
    private double racketThickness = 10.0;
    // private double racketSpeed = 300.0; // m/s
    private Rectangle racket;

    public Raquette(double height) {
        this.racket = new Rectangle();
        this.racket.setHeight(height);
        this.racket.setWidth(racketThickness);
        this.racket.setFill(Color.WHITE);
    }

    public Rectangle getRacket() {
        return this.racket;
    }

    public void setThickness(double th) {
        this.racketThickness = th;
    }

    public double getThickness() {
        return this.racketThickness;
    }

    public void setSize(double d) {
        racket.setHeight(d);
    }

    public void setCouleur(Color c) {
        this.racket.setFill(c);
    }
}
