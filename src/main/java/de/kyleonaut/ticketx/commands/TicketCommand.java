package de.kyleonaut.ticketx.commands;

import de.kyleonaut.ticketx.TicketX;
import de.kyleonaut.ticketx.utils.Config;
import de.kyleonaut.ticketx.utils.MySQL;
import de.kyleonaut.ticketx.utils.TicketStatus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class TicketCommand implements CommandExecutor {

    public static HashMap<String,String> waitingForTicketTextInput = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length != 0) {
                switch (strings[0]) {
                    case "erstellen":
                        if (strings.length >= 2){
                            if (player.hasPermission("ticketx.erstellen")) {
                                Config.sendMessage(player," §7Schreibe dein Ticket-Anliegen in den Chat.");
                                Config.sendMessage(player," §7Du kannst diesen Vorgang mit §cEXIT §7abbrechen.");
                                waitingForTicketTextInput.put(player.getName(),strings[1]); //Player in die Chatcancel Map eintragen
                            } else {
                                Config.sendMessage(player, " §cDu hast keine Rechte diesen Befehl auszuführen.");
                            }
                        }else{
                            Config.sendMessage(player," §cDu musst einen Ticket-Titel angeben.");
                        }
                        break;
                    case "übersicht":
                        break;
                    case "moderieren":
                        break;
                }
            } else {
                Config.sendMessage(player, " §cHelp Command");
            }

        }
        return false;
    }
}
