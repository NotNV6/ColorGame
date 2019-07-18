package io.oaft.colorgame.listeners;

import io.oaft.colorgame.ColorGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private ColorGame plugin;

    public PlayerListener(ColorGame plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getManagerHandler().getGameManager().startGame();
    }

}
