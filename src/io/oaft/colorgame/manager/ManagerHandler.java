package io.oaft.colorgame.manager;

import io.oaft.colorgame.ColorGame;
import io.oaft.colorgame.manager.impl.GameManager;
import io.oaft.colorgame.manager.impl.SQLManager;

public class ManagerHandler {

    private ColorGame plugin;

    private SQLManager sqlManager;
    private GameManager gameManager;

    public ManagerHandler(ColorGame plugin) {
        this.plugin = plugin;
        this.loadManagers();
    }

    private void loadManagers() {
        this.sqlManager = new SQLManager(this);
        this.gameManager = new GameManager(this);
    }

    ColorGame getPlugin() {
        return plugin;
    }

    public SQLManager getSQLManager() {
        return sqlManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

}
