package io.oaft.colorgame;

import io.oaft.colorgame.manager.ManagerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorGame extends JavaPlugin {

    private static ColorGame instance;
    private ManagerHandler managerHandler;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        this.managerHandler = new ManagerHandler(this);
    }

    public static ColorGame getInstance() {
        return instance;
    }

    public ManagerHandler getManagerHandler() {
        return managerHandler;
    }
}
