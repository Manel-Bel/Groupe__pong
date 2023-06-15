package model;

import java.util.Random;

public class Player implements RacketController { // la classe Player va implémenter les méthodes dont la signature
    // sont initialisés dans l'interface RacketController
    State state = State.IDLE; // la variable state est initialisé à l'état IDLE
    private int score = 0;
    private String name;
    private int scoreTour = 0;
    private static boolean infini = false;
    private int jauge;
    private boolean inverse;
    private int compteurInverse;
    private int compteurTaille;
    private int compteurVitesse;
    private Bonus bonus;
    private Malus malus;
    private boolean smash;
    private static boolean activeJauge = false;

    public Player(String name) {
        this.name = name;
        jauge = 0;
        bonus = null;
        malus = null;
        inverse = false;
        compteurInverse = 0;
        compteurTaille = 0;
        compteurVitesse = 0;
        smash = false;
    }

    public static void setInfini(boolean b) {
        infini = b;
    }

    @Override
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getInverse() {
        return inverse;
    }

    public void setInverse() {
        inverse = true;
        compteurInverse = 5;
    }

    public Bonus getBonus() {
        return this.bonus;
    }

    public Malus getMalus() {
        return this.malus;
    }

    public void increScore() {
        this.score++;
    }

    public void resetScore() {
        this.score = 0;
    }

    public int getJauge() {
        return this.jauge;
    }

    public int getCompteurTaille() {
        return this.compteurTaille;
    }

    public void setCompteurTaille() {
        compteurTaille = 5;
    }
    
    public int getCompteurVitesse() {
        return this.compteurVitesse;
    }

    public void setCompteurVitesse() {
        compteurVitesse = 5;
    }

    public boolean getSmash() {
        return this.smash;
    }

    public void setSmash(boolean b) {
        smash = b;
    }
    
    public static void setActiveJauge(boolean b) {
    	activeJauge = b;
    }
    
    public String getNameBonus() {
    	if (bonus == null) {
    		return "?";
    	}
    	switch (bonus) {
    	case GRAND :
    		return "G";
    	case SMASH :
    		return "S";
    	case BOOST : 
    		return "B";
    	case REBON :
    		return "R";
    	}
    	return "?";
    }
    
    public String getNameMalus() {
    	if (malus == null) {
    		return "?";
    	}
    	switch (malus) {
    	case PETIT :
    		return "P";
    	case INVISIBLE :
    		return "I";
    	case RALENTI : 
    		return "S";
    	case INVERSE :
    		return "R";
    	}
    	return "?";
    }

    public boolean aGagne() { // teste si le player a gagne un tour
        return (infini) ? false : this.score >= 3;
    }

    public void incrScoreTour() { // on sauvegarde le nombre de tour qu'il a gagne
        this.scoreTour++;
    }

    public int getScoreTour() {
        return this.scoreTour;
    }

    public void resetScoreTour() {
        this.scoreTour = 0;
    }

    public void incrJauge(int i) {
    	if(activeJauge) {
	        if (jauge + i >= 7) {
	            jauge = 0;
	            Random rd = new Random();
	            int tmp = rd.nextInt(100);
	            if (tmp < 25) {
	                bonus = Bonus.GRAND;
	            } else if (tmp < 50) {
	                bonus = Bonus.BOOST;
	            } else if (tmp < 75) {
	                bonus = Bonus.REBON;
	            } else
	                bonus = Bonus.SMASH;
	            tmp = rd.nextInt(100);
	            if (tmp < 25) {
	                malus = Malus.INVERSE;
	            } else if (tmp < 50) {
	                malus = Malus.INVISIBLE;
	            } else if (tmp < 75) {
	                malus = Malus.PETIT;
	            } else
	                malus = Malus.RALENTI;
	        } else
	            jauge += i;
    	}
    }

    public void decreaseCount() {
        if (inverse) {
            compteurInverse--;
            if (compteurInverse == 0) {
                inverse = false;
            }
        }
        if (compteurVitesse > 0) {
            compteurVitesse--;
        }
        if (compteurTaille > 0) {
            compteurTaille--;
        }
    }

    public void resetPowers() {
        this.bonus = null;
        this.malus = null;
    }
    
    public void resetJauge() {
    	this.jauge = 0;
    }

}
