package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.manager.Manager;
import io.oaft.colorgame.manager.ManagerHandler;
import io.oaft.colorgame.util.GameState;
import io.oaft.colorgame.util.Timer;

public class GameManager extends Manager {

    private GameState gameState;
    private Timer startingTimer;

    public GameManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.load();
    }

    private void load() {
        this.gameState = GameState.PRE_GAME;
        this.startingTimer = new Timer(getPlugin(), 10, true);
    }

    public void startGame() {

    }

}
