package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Son { //tout est statique dans cette classe, comme ça on peut accéder facilement à chaque fonction 
	private static String ssound;
	private static String tsound;
	private static String bsound;
	private static String asound;
	
	private static Media sound;
	private static Media tound;
	private static Media bound;
	private static Media aound;
	
	private static MediaPlayer mediaPlayer;
	
	private static boolean jouerSon = true, jouerMusique = true, jouerEffets = true;
	
	public static boolean getSon() {
		return jouerSon;
	}
	
	public static boolean getMusique() {
		return jouerMusique;
	}
	
	public static boolean getEffets() {
		return jouerEffets;
	}
	
	public static void changeSon(boolean b) {
		jouerSon = b;
	}
	
	public static void changeMusique(boolean b) {
		jouerMusique = b;
	}
	
	public static void changeEffets(boolean b) {
		jouerEffets = b;
	}
	
	public static void reset() {
		jouerSon = true;
		jouerMusique = true;
		jouerEffets = true;
	}
		
	//chaque fonction fait un son quand elle est appelée
    
    public static void playBound() {
    	if(jouerSon && jouerEffets) {
	    	try {
	    		bsound = Court.class.getResource("obstacle.wav").toURI().toString();
	    		bound = new Media(bsound);
	    	}
	    	catch (Exception e) {
	    		System.out.println(e);
	    	}
	    	new MediaPlayer(bound).play();
    	}
    }
    
    public static void playTound() {
    	if(jouerSon && jouerEffets) {
	    	try {
	    		tsound = Court.class.getResource("out.wav").toURI().toString();
	        	tound = new Media(tsound);
	        }
	       	catch (Exception e) {
	       		System.out.println(e);
	        }
	    	new MediaPlayer(tound).play();
    	}
    }
    
    public static void playSound() {
    	if(jouerSon && jouerEffets) {
	    	try {
	    		ssound = Court.class.getResource("pong_sound.wav").toURI().toString();
	        	sound = new Media(ssound);
	        }
	    	catch (Exception e) {
	       		System.out.println(e);
	        }
	    	new MediaPlayer(sound).play();
    	}
    }
	public static void playAound() {
		if (jouerSon && jouerMusique) {
			try {
				asound = Court.class.getResource("arcade.mp3").toURI().toString();
				aound = new Media(asound);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			mediaPlayer = new MediaPlayer(aound);
			mediaPlayer.cycleCountProperty().setValue(MediaPlayer.INDEFINITE);
			mediaPlayer.play();
		}
	}
	public static void stopMusic () {
		mediaPlayer.stop();
	}
	public static void pauseMusic () {
		mediaPlayer.pause();
	}
	public static void play() {
		mediaPlayer.play();
	}

}
