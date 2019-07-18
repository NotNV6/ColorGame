package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.manager.Manager;
import io.oaft.colorgame.manager.ManagerHandler;
import io.oaft.colorgame.util.CC;
import io.oaft.colorgame.util.GameState;
import io.oaft.colorgame.util.Timer;

public class GameManager extends Manager {

    private GameState gameState;
    private Timer startingTimer;
    private Timer gameTimer;

    public GameManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.load();
    }

    private void load() {
        this.gameState = GameState.PRE_GAME;
        this.startingTimer = new Timer(getPlugin(), 10, true);
        this.gameTimer = new Timer(getPlugin(), 0, false);
    }

    public void startGame() {
        this.startingTimer.start();
        this.gameState = GameState.PRE_GAME;
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if(this.startingTimer.getSeconds() == 10) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in 10 seconds.");
            } else if (this.startingTimer.getSeconds() == 5) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in 5 seconds.");
            } else if (this.startingTimer.getSeconds() == 4) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in 4 seconds.");
            } else if (this.startingTimer.getSeconds() == 3) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in 3 seconds.");
            } else if (this.startingTimer.getSeconds() == 2) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in 2 seconds.");
            } else if (this.startingTimer.getSeconds() == 1) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in 1 second.");
            } else if (this.startingTimer.getSeconds() == 0) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting now.");
                this.doGame();
                this.startingTimer.stop();
            }
        }, 0L, 20L);
    }

    private void doGame() {
        this.gameState = GameState.IN_GAME;
    }

}
