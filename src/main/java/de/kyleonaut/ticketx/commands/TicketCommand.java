package de.kyleonaut.ticketx.commands;

import com.google.common.base.Splitter;
import de.kyleonaut.ticketx.TicketX;
import de.kyleonaut.ticketx.utils.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class TicketCommand implements CommandExecutor {

    public static HashMap<String,String> waitingForTicketTextInput = new HashMap<>();
    private InventoryHolder holder = new InventoryHolder();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length != 0) {
                switch (strings[0]) {
                    case "erstellen":
                        if (player.hasPermission("ticketx.erstellen")){
                            if (strings.length >= 2) {
                                Config.sendMessage(player," §7Schreibe dein Ticket-Anliegen in den Chat.");
                                Config.sendMessage(player," §7Du kannst diesen Vorgang mit §cEXIT §7abbrechen.");
                                waitingForTicketTextInput.put(player.getName(),strings[1]); //Player in die Chatcancel Map eintragen
                            } else {
                                Config.sendMessage(player, " §cDu musst einen Ticket-Titel angeben.");
                            }
                        }else{
                            Config.sendMessage(player," §cDu hast keine Rechte diesen Befehl auszuführen.");
                        }
                        break;
                    case "übersicht":
                        if (player.hasPermission("ticketx.übersicht")){
                            holder.useruebsersichtGui(player);
                        }else{
                            Config.sendMessage(player," §cDu hast keine Rechte diesen Befehl auszuführen.");
                        }
                        break;
                    case "mod":
                        if (player.hasPermission("ticketx.mod")){
                            holder.moderationsUebersichtGui(player);
                        }else{
                            Config.sendMessage(player, " §cDu hast keine Rechte diesen Befehl auszuführen.");
                        }
                        break;


                }
            } else {
                Config.sendMessage(player,"§a - - - §aTicket §2X §a- - -");
                Config.sendMessage(player,"§a/ticket §2erstellen <Tickettitel>");
                Config.sendMessage(player,"§a/ticket §2übersicht");
                if (player.hasPermission("ticketx.moderieren")){
                    Config.sendMessage(player,"§a/ticket §2mod");
                }
            }

        }
        return false;
    }
}
