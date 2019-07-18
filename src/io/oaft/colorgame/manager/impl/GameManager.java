package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.manager.Manager;
import io.oaft.colorgame.manager.ManagerHandler;
import io.oaft.colorgame.util.GameState;

public class GameManager extends Manager {

    private GameState gameState;

    public GameManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.load();
    }

    private void load() {
        this.gameState = GameState.PRE_GAME;
    }

}
