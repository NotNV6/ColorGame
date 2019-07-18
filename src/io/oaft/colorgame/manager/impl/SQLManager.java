package io.oaft.colorgame.manager.impl;

import io.oaft.colorgame.manager.Manager;
import io.oaft.colorgame.manager.ManagerHandler;
import io.oaft.colorgame.util.CC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLManager extends Manager {

    private Connection connection;

    public SQLManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.load();
    }

    private void load() {
        synchronized (this) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + getPlugin().getConfig().getString("database.sql.host") + ':' + getPlugin().getConfig().getInt("database.sql.port") + '/'
                        + getPlugin().getConfig().getString("database.sql.database"), getPlugin().getConfig().getString("database.sql.username"), getPlugin().getConfig().getString("database.sql.password"));
                print(CC.AQUA + "[oCore] " + CC.GREEN + "Connected to database " + getPlugin().getConfig().getString("database.sql.database") + '.');
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
