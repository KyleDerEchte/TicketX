package de.kyleonaut.ticketx.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TicketItem {

    TicketXMySQLAPI api = new TicketXMySQLAPI();

    public ItemStack createTicket(int ticket_id){
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(api.getTicketTitleById(ticket_id));
        ArrayList<String> lore = new ArrayList<>();
        return itemStack;
    }
}
