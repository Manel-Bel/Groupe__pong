package gui;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HeaderGame {
    private Text score;
    private Text nomA, nomB, mancheA, mancheB;
    private Text bonusA, bonusB, malusA, malusB;

    public HeaderGame(String nomA, String nomB, int nbManche) {

        score = new Text("00 : 00");
        score.setFill(Color.WHITE);
        score.setStyle("-fx-font: 42 ARCADECLASSIC");

        this.nomA = new Text(nomA);
        this.nomA.setFill(Color.WHITE);
        this.nomA.setStyle("-fx-font: 35 ARCADECLASSIC");

        this.nomB = new Text(nomB);
        this.nomB.setId("text");
        this.nomB.setFill(Color.WHITE);
        this.nomB.setStyle("-fx-font: 35 ARCADECLASSIC");
        this.mancheA = new Text("00/" + String.format("%02d", nbManche));
        this.mancheA.setFill(Color.WHITE);
        this.mancheA.setStyle("-fx-font: 20 ARCADECLASSIC");

        this.mancheB = new Text("00/" + String.format("%02d", nbManche));
        this.mancheB.setFill(Color.WHITE);
        this.mancheB.setStyle("-fx-font: 20 ARCADECLASSIC");

        this.bonusA = new Text(" ");
        this.bonusA.setFill(Color.RED);
        this.bonusA.setStyle("-fx-font: 15 ARCADECLASSIC");

        this.bonusB = new Text(" ");
        this.bonusB.setFill(Color.RED);
        this.bonusB.setStyle("-fx-font: 15 ARCADECLASSIC");

        this.malusA = new Text(" ");
        this.malusA.setFill(Color.BLUE);
        this.malusA.setStyle("-fx-font: 15 ARCADECLASSIC");

        this.malusB = new Text(" ");
        this.malusB.setFill(Color.BLUE);
        this.malusB.setStyle("-fx-font: 15 ARCADECLASSIC");
    }

    public void updateScore(int scoreA, int scoreB) {
        String a = String.format("%02d", scoreA);
        String b = String.format("%02d", scoreB);
        score.setText(a + " : " + b);
    }

    public void updateNbManchA(int nbA, int nbManche) {
        String a = String.format("%02d", nbA);
        String b = String.format("%02d", nbManche);
        mancheA.setText(a + " / " + b);
    }

    public void updateNbManchB(int nbB, int nbManche) {
        String a = String.format("%02d", nbB);
        String b = String.format("%02d", nbManche);
        mancheB.setText(a + " / " + b);
    }

    public void updateBonus(String bonusA, String bonusB) {
        this.bonusA.setText(bonusA);
        this.bonusB.setText(bonusB);

    }

    public void updateMalus(String malusA, String malusB) {
        this.malusA.setText(malusA);
        this.malusB.setText(malusB);
    }

    @Override
    public String toString() {
        return score.getText();
    }

    public Text getScoreText() {
        return score;
    }

    public Text getNameA() {
        return this.nomA;
    }

    public Text getNameB() {
        return this.nomB;
    }

    public Text getMancheA() {
        return this.mancheA;
    }

    public Text getMancheB() {
        return this.mancheB;
    }

    public Text getBonusA() {
        return this.bonusA;
    }

    public Text getBonusB() {
        return this.bonusB;
    }

    public Text getMalusA() {
        return this.malusA;
    }

    public Text getMalusB() {
        return this.malusB;
    }

    public void setNameA(String string) {
        this.nomA.setText(string);
    }

    public void setNameB(String string) {
        this.nomB.setText(string);
    }

}
