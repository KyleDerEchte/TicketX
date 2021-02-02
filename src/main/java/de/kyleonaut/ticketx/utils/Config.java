package de.kyleonaut.ticketx.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class Config {

    private static final FileConfiguration cfg = getFileConfiguration();

    public static void setConfig(){

        cfg.options().copyDefaults(true);

        /*
         * MySQL login
         * */
        cfg.addDefault("Mysql.host","");
        cfg.addDefault("Mysql.port","");
        cfg.addDefault("Mysql.database","");
        cfg.addDefault("Mysql.username","");
        cfg.addDefault("Mysql.password","");

        /*
         * Chat Messages
         * */


        /*
        * Settings
        * */
        cfg.addDefault("Settings.Prefix","§7[§aTicket§2X§7]");


        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(){
        return new File("plugins/TicketX","config.yml");
    }

    private static FileConfiguration getFileConfiguration(){
        return YamlConfiguration.loadConfiguration(getFile());
    }


    public static FileConfiguration getCfg(){
        return cfg;
    }

    /*public static void sendMessage(Player player,String key){
        player.sendMessage(cfg.getString("Settings.Prefix")+ cfg.get(key));
    }*/
    public static void sendMessage(Player player,String msg){
        player.sendMessage(cfg.getString("Settings.Prefix")+ msg);
    }

}

