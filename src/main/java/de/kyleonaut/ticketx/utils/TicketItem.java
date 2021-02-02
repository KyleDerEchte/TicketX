package de.kyleonaut.ticketx.utils;

import com.google.common.base.Splitter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TicketItem {

    private TicketXMySQLAPI api = new TicketXMySQLAPI();

    public ItemStack createTicketItem(long ticket_id){
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§2"+api.getTicketTitleById(ticket_id));
        ArrayList<String> lore = new ArrayList<>();
        String status;
        if (api.getStatusByTicketId(ticket_id) == TicketStatus.ABGELEHNT){
            status = "§cAbgelehnt";
        }else if (api.getStatusByTicketId(ticket_id) == TicketStatus.ANGENOMMEN){
            status = "§aAngenommen";
        }else if(api.getStatusByTicketId(ticket_id) == TicketStatus.ERLEDIGT){
            status = "§6Erledigt";
        }else{
            status = "§2In Bearbeitung";
        }
        Iterable<String> text = Splitter.fixedLength(35).split(api.getTicketTextByTicketId(ticket_id));
        lore.add("§a➥ Welt: §2"+api.getWeltNameByTicketId(ticket_id));
        lore.add("§a➥ Spieler: §2"+api.getPlayerNamebyTicketId(ticket_id));
        lore.add("§a➥ InDate: §2"+api.getInDateByTicketId(ticket_id));
        lore.add("§a➥ OutDate: §2"+api.getOutDateByTicketId(ticket_id));
        lore.add("§a➥ Status: "+status);
        for (String s : text){
            lore.add("§a➥ Text: §2"+s);
        }
        lore.add("§a➥ Moderator: §2"+api.getModeratorNameByTicketId(ticket_id));
        lore.add("§a➥ Ticket-Id: "+ticket_id);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
