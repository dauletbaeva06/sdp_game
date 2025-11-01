package com.game.observer;

public class BattleObserver implements Observer {
    private final GameLogger logger = GameLogger.getInstance();

    @Override
    public void update(String event) {
        logger.log(event);
    }
}
