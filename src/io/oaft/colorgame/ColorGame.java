package io.oaft.colorgame;

import org.bukkit.plugin.java.JavaPlugin;

public class ColorGame extends JavaPlugin {

    private static ColorGame instance;

    @Override
    public void onEnable() {

    }

    public static ColorGame getInstance() {
        return instance;
    }

}
