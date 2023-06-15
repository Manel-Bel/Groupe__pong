package model;

public interface RacketController {
    enum State {
        GOING_UP, IDLE, GOING_DOWN
    }

    public State getState();
}
