package de.kyleonaut.ticketx;

import de.kyleonaut.ticketx.commands.TicketCommand;
import de.kyleonaut.ticketx.events.ChatHandler;
import de.kyleonaut.ticketx.utils.Config;
import de.kyleonaut.ticketx.utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class TicketX extends JavaPlugin {

    private static TicketX plugin;
    private static String MySQLhost, MySQLdatabase, MySQLusername, MySQLpassword;
    private static int MySQLport;
    private MySQL con = new MySQL();

    @Override
    public void onEnable() {
        getConfigValues();
        doTableSetup();
        plugin = this;

        /*
         * CommandRegistery
         * */
        getCommand("ticket").setExecutor(new TicketCommand());

        /*
         * EventRegistery
         * */
        Bukkit.getPluginManager().registerEvents(new ChatHandler(),this);
    }

    @Override
    public void onDisable() {

    }

    /*
     * Public Methods
     * */
    public static String getMySQLhost() {
        return MySQLhost;
    }

    public static int getMySQLport() {
        return MySQLport;
    }

    public static String getMySQLdatabase() {
        return MySQLdatabase;
    }

    public static String getMySQLpassword() {
        return MySQLpassword;
    }

    public static String getMySQLusername() {
        return MySQLusername;
    }

    public static TicketX getPlugin() {
        return plugin;
    }



    /*
     * Private Methods
     * */
    private void getConfigValues() {
        Config.setConfig();
        MySQLhost = Config.getCfg().getString("Mysql.host");
        MySQLport = Config.getCfg().getInt("Mysql.port");
        MySQLdatabase = Config.getCfg().getString("Mysql.database");
        MySQLusername = Config.getCfg().getString("Mysql.username");
        MySQLpassword = Config.getCfg().getString("Mysql.password");
    }

    private void doTableSetup() {
        con.execute("CREATE TABLE IF NOT EXISTS ticketx_tickets (ticket_id BIGINT NOT NULL AUTO_INCREMENT," +
                "playerName VARCHAR(60),playerUUID VARCHAR(60),status VARCHAR(30),inDate VARCHAR(25)," +
                "outDate VARCHAR(25),ticket_text TEXT,moderatorName VARCHAR(60),moderatorUUID VARCHAR(60)," +
                "weltName VARCHAR(50),ticket_title TEXT,PRIMARY KEY(ticket_id))");
        con.execute("CREATE TABLE IF NOT EXISTS ticketx_notizen(ticket_id BIGINT," +
                " notiz_id BIGINT NOT NULL AUTO_INCREMENT,notiz_text TEXT,authorName VARCHAR(60),authorUUID VARCHAR(60)," +
                "PRIMARY KEY(notiz_id))");

    }
}
