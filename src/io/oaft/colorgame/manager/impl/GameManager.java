package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.manager.Manager;
import io.oaft.colorgame.manager.ManagerHandler;
import io.oaft.colorgame.util.CC;
import io.oaft.colorgame.util.GameState;
import io.oaft.colorgame.util.Timer;
import org.bukkit.inventory.ItemStack;

public class GameManager extends Manager {

    private GameState gameState;
    private Timer startingTimer;
    private Timer gameTimer;
    private Timer newBlockTimer;
    private ItemStack nextBlock;

    public GameManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.load();
    }

    private void load() {
        this.gameState = GameState.PRE_GAME;
        this.startingTimer = new Timer(getPlugin(), 10, true);
        this.gameTimer = new Timer(getPlugin(), 0, false);
        this.newBlockTimer = new Timer(getPlugin(), 5, true);
    }

    public void startGame() {
        this.startingTimer.start();
        this.gameState = GameState.PRE_GAME;
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if(this.gameState == GameState.PRE_GAME) {
                for (int i = 0; i < this.startingTimer.getSeconds(); i++) {
                    if(i != 0) {
                        getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in " + i + " seconds.");
                    } else {
                        getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting now.");
                        this.doGame();
                        this.startingTimer.stop();
                    }
                }
            }
        }, 0L, 20L);
    }

    private void doGame() {
        this.gameState = GameState.IN_GAME;
        this.gameTimer.start();
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if(this.gameState == GameState.IN_GAME) {

                if(this.newBlockTimer.getSeconds() == 5) {

                }

            }
        }, 0L, 20L);
    }

}
