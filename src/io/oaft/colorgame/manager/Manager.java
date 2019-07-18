package io.oaft.colorgame.manager;

import io.oaft.colorgame.ColorGame;
import io.oaft.colorgame.util.CC;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.Connection;

public class Manager {

    protected ManagerHandler managerHandler;

    protected Manager(ManagerHandler managerHandler) {
        this.managerHandler = managerHandler;
    }

    protected ColorGame getPlugin() {
        return managerHandler.getPlugin();
    }

    protected void print(String string) {
        getPlugin().getServer().getConsoleSender().sendMessage(CC.translate(string));
    }

    protected Connection getConnection() {
        return managerHandler.getSQLManager().getConnection();
    }

    protected BukkitScheduler getScheduler() {
        return getPlugin().getServer().getScheduler();
    }

}

