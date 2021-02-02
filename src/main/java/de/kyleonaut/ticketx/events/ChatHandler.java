package de.kyleonaut.ticketx.events;

import de.kyleonaut.ticketx.TicketX;
import de.kyleonaut.ticketx.commands.TicketCommand;
import de.kyleonaut.ticketx.utils.Config;
import de.kyleonaut.ticketx.utils.MySQL;
import de.kyleonaut.ticketx.utils.TicketStatus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ChatHandler implements Listener {

    private final MySQL con = new MySQL();



    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String msg = e.getMessage();
        if (TicketCommand.waitingForTicketTextInput.containsKey(player.getName())){
            if (msg.equalsIgnoreCase("EXIT")){
                e.setCancelled(true);
                Config.sendMessage(player," §7Der Vorgang wurde abgebrochen.");
            }else{
                e.setCancelled(true); //Event canceln um die Chatausgabe zu verhindern

                LocalDateTime ldt = LocalDateTime.now();
                String date = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.GERMANY).format(ldt);

                con.execute("INSERT INTO ticketx_tickets VALUES(" + 0 + "," + //DB Eintrag des Tickets
                        "'" + player.getName() + "','" + player.getUniqueId() + "','" + TicketStatus.IN_BEARBEITUNG + "'," +
                        "'" + date + "',' ','"+msg+"',' ',' ','" +
                        player.getWorld().getName() + "','"+TicketCommand.waitingForTicketTextInput.get(player.getName())+"')");
                Config.sendMessage(player," §aDein Ticket ist in unser System aufgenommen worden."); //Sicherheitsbestätigung für den User
            }
        }
    }

}
