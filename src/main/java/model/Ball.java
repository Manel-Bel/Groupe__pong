package model;

import javafx.scene.shape.Circle;
import java.util.Random;
import javafx.scene.paint.Color;

public class Ball {
    private final double ballRadius;
    private double ballX, ballY;
    private double ballSpeedX, ballSpeedY;
    private Circle ball;
    private boolean startRight, smash, invisible;
    private double saveBallSpeed = 0;
    private static boolean infini = false;

    public Ball(double radian, double scale, double xMargin) {
        this.ballRadius = radian;
        ball = new Circle();
        ball.setRadius(ballRadius);
        ball.setFill(Color.WHITE);
        ball.setCenterX(ballX * scale + xMargin);
        ball.setCenterY(ballY * scale);
        startRight = true;
        smash = false;
        invisible = false;
    }

    public static void setInfini(boolean b) {
        infini = b;
    }

    public double getBallSpeedX() {
        return ballSpeedX;
    }

    public double getBallSpeedY() {
        return ballSpeedY;
    }

    public Circle getBall() {
        return this.ball;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }
    
    public boolean getInvisible() {
    	return invisible;
    }

    public void setColor(Color c) {
        ball.setFill(c);
    }

    /**
     * @return true if a player lost
     */
    public boolean updateBall(double deltaT, double width, double height, double racketA, double racketB,
            double racketC, double racketD, double racketSize,
            Player[] players, boolean v2) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        // next, see if the ball would meet some obstacle
        if (nextBallY < +10 || nextBallY > height + 25) {
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
            Son.playBound();
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + ((infini) ? 600 : racketSize))
                || (nextBallX > width && nextBallY > racketB && nextBallY < racketB + racketSize)
                || (v2 && nextBallX < 100 && nextBallX > 100 - 5 && nextBallY > racketC
                        && nextBallY < racketC + racketSize)
                || (v2 && nextBallX > width - 100 && nextBallX < width - 100 + 5 && nextBallY > racketD
                        && nextBallY < racketD + racketSize)) {
        	invisible = false;
            if (infini && nextBallX > width && nextBallY > racketB && nextBallY < racketB + racketSize) {
                players[1].increScore();
            }
            if (ballSpeedX > 0) {
                players[1].incrJauge(1);
                players[1].decreaseCount();
                if(players[1].getSmash()) {
                	smash = true;
                	players[1].setSmash(false);
                }
            } else {
                players[0].incrJauge(1);
                players[0].decreaseCount();
                if(players[0].getSmash()) {
                	smash = true;
                	players[0].setSmash(false);
                }
            }
            ballSpeedX = -ballSpeedX;
            if(smash) {
            	saveBallSpeed = -ballSpeedX;
            	ballSpeedX *= 5;
            	smash = false;
            }
            else {
            	if(saveBallSpeed != 0) {
            		ballSpeedX = saveBallSpeed;
            		saveBallSpeed = 0;
            	}
            	ballSpeedX = ballSpeedX > 0 ? ballSpeedX + 15 : ballSpeedX - 15;
            }
            nextBallX = ballX + deltaT * ballSpeedX;
            Son.playSound();
        } else if (nextBallX < 0) {
            players[1].increScore();
            players[0].incrJauge(2);
            Son.playTound();
            startRight = true;
            invisible = false;
            return true;
        } else if (nextBallX > width) {
        	invisible = false;
            if (!infini) {
                players[0].increScore();
                players[1].incrJauge(2);
                Son.playTound();
                startRight = false;
            } else
                players[1].resetScore();
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }

    public void reset(double width, double height) {
        Random rd = new Random();
        this.ballSpeedX = startRight ? 200.0 : -200.0;
        this.ballSpeedY = rd.nextInt(101) + 175.0;
        if (rd.nextBoolean()) {
            this.ballSpeedY = -this.ballSpeedY;
        }
        this.ballX = width / 2;
        this.ballY = height / 2;
    }
    
    public void rebon() {
    	ballSpeedY = - ballSpeedY;
    }
    
    public void invisible() {
    	invisible = true;
    }

}