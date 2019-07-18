package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.manager.Manager;
import io.oaft.colorgame.manager.ManagerHandler;
import io.oaft.colorgame.util.CC;
import io.oaft.colorgame.util.GameState;
import io.oaft.colorgame.util.Timer;
import io.oaft.colorgame.util.cuboid.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManager extends Manager {

    private GameState gameState;
    private Timer startingTimer;
    private Timer newBlockTimer;
    private ItemStack nextBlock;
    private Cuboid cuboid;

    public GameManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.load();
    }

    private void load() {
        this.gameState = GameState.PRE_GAME;
        this.startingTimer = new Timer(getPlugin(), 10, true);
        this.newBlockTimer = new Timer(getPlugin(), 5, true);
        this.cuboid = new Cuboid(
                getPlugin().getServer().getWorld("world"),
                50,
                0,
                50,
                -50,
                200,
                -50
        );
    }

    public void startGame() {
        this.startingTimer.start();
        this.gameState = GameState.PRE_GAME;
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if (this.gameState == GameState.PRE_GAME) {
                for (int i = 0; i < this.startingTimer.getSeconds(); i++) {
                    if (i != 0) {
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
        this.nextBlock = new ItemStack(Material.WOOL, 1, (short) 14);
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if (this.gameState == GameState.IN_GAME) {
                int i = this.newBlockTimer.getSeconds();
                if (i != 0) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.getInventory().setItem(0, new ItemStack(nextBlock));
                    }
                    getPlugin().getServer().broadcastMessage(CC.GOLD + "New block in " + i + " seconds.");
                } else {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.getInventory().setItem(0, null);
                    }
                    this.newBlockTimer.stop();
                    for (Block block : this.cuboid) {
                        if (block.getTypeId() == this.nextBlock.getTypeId()) {
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
        }, 0L, 20L);
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if (this.gameState == GameState.IN_GAME) {
                switch (nextBlock.getAmount()) {
                    case 1:
                        this.nextBlock = new ItemStack(Material.WOOL, 2, (short) 4);
                    case 2:
                        this.nextBlock = new ItemStack(Material.WOOL, 3, (short) 5);
                    case 3:
                        this.nextBlock = new ItemStack(Material.WOOL, 4, (short) 3);
                    default:
                        this.nextBlock = new ItemStack(Material.WOOL, 1, (short) 14);
                }
                this.newBlockTimer.start();
            }
        }, 0L, 200L);
    }

}
