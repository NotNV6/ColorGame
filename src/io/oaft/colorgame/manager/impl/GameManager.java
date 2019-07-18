package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.listeners.PlayerListener;
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

import java.util.HashSet;
import java.util.Set;

public class GameManager extends Manager {

    private GameState gameState;
    private Timer startingTimer;
    private Timer newBlockTimer;
    private ItemStack nextBlock;
    private Cuboid cuboid;
    private Set<Block> blockSet;

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
        new PlayerListener(getPlugin());
        this.blockSet = new HashSet<>();
    }

    public void startGame() {
        this.startingTimer.start();
        this.gameState = GameState.PRE_GAME;
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if (this.gameState == GameState.PRE_GAME) {
                int i = this.startingTimer.getSeconds();
                if (i != 0) {
                    getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting in " + i + " seconds.");
                } else {
                    getPlugin().getServer().broadcastMessage(CC.GOLD + "Game starting now.");
                    this.doGame();
                    this.startingTimer.stop();
                }
            }
        }, 0L, 20L);
    }

    private void doGame() {
        this.gameState = GameState.IN_GAME;
        this.nextBlock = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if(this.newBlockTimer.getSeconds() != 0) {
                getPlugin().getServer().broadcastMessage(CC.GOLD + "New block in " + this.newBlockTimer.getSeconds() + " seconds.");
            } else {
                for (Block block : this.cuboid) {
                    if(block.getType() == nextBlock.getType()) {
                        block.setType(Material.AIR);
                        getScheduler().runTaskLaterAsynchronously(getPlugin(), () -> block.setType(block.getType()), 100L);
                    }
                }
                this.newBlockTimer.stop();
            }
        }, 0L, 20L);
        getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> {
            if(this.nextBlock.getType() == Material.WOOL) {
                this.nextBlock = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);
            } else {
                this.nextBlock = new ItemStack(Material.WOOL, 1, (short) 14);
            }
            this.newBlockTimer.setTime(5);
            this.newBlockTimer.start();
        }, 0L, 200L);
    }

}
