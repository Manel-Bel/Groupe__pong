package gui;

import javafx.scene.control.ProgressBar;

public class Jauge {
	private ProgressBar jauge;

	public Jauge(double width, double height, boolean g) {
		jauge = new ProgressBar();
		jauge.setPrefSize(150, 30);
		if (g) {
			jauge.setLayoutX(5);
		} else
			jauge.setLayoutX(width - 55);
		if (!g) {
			jauge.setRotate(180);
		}
		jauge.setLayoutY(5);
		jauge.setProgress(0);
		jauge.setStyle("-fx-accent: red");
	}

	public ProgressBar getJauge() {
		return this.jauge;
	}

	public void update(int i) {
		jauge.setProgress((((double) i * 100.) / 7.) / 100.);
	}
}
