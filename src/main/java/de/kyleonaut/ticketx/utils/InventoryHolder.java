package de.kyleonaut.ticketx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class InventoryHolder {

    private final Builder builder = new Builder();
    private final TicketXMySQLAPI api = new TicketXMySQLAPI();
    private final TicketItem ticketItem = new TicketItem();

    public void moderationsUebersichtGui(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Ticket-Moderation");
        int c = 0;
        for (Object o : api.getAllOpenTickets()) {
            long id = (long) o;
            c++;
            if (c <= 52) {
                inventory.addItem(ticketItem.createTicketItem(id));
            }
        }
        inventory.setItem(53, builder.createItem(Material.BARRIER, "§aSchließen"));
        inventory.setItem(52, builder.createItem(Material.BOOKSHELF, "§aArchiv"));
        player.openInventory(inventory);
    }

    public void statusAendern(Player player, InventoryClickEvent e) {
        long id = Long.parseLong(e.getCurrentItem().getItemMeta().getLore().get(e.getCurrentItem().getItemMeta().getLore().size() - 1).replace("§a➥ Ticket-Id: ", ""));
        Inventory inventory = Bukkit.createInventory(null, 9, "Status Ändern");
        inventory.setItem(0, ticketItem.createTicketItem(id));
        inventory.setItem(1, builder.createGlassPane(Builder.Color.LIGHT_GRAY, " ", 1, " "));
        inventory.setItem(2, builder.createGlassPane(Builder.Color.LIME_GREEN, "§aAnnehmen", 1, "§a➥ §2Klicke um das Ticket als angenommen zu markieren."));
        inventory.setItem(3, builder.createGlassPane(Builder.Color.LIGHT_GRAY, " ", 1, " "));
        inventory.setItem(4, builder.createGlassPane(Builder.Color.ORANGE, "§aModerieren", 1, "§a➥ §2Klicke um dich als Moderator einzutragen."));
        inventory.setItem(5, builder.createGlassPane(Builder.Color.LIGHT_GRAY, " ", 1, " "));
        inventory.setItem(6, builder.createGlassPane(Builder.Color.RED, "§aAblehnen", 1, "§a➥ §2Klicke um das Ticket als abgelehnt zu markieren."));
        inventory.setItem(7, builder.createGlassPane(Builder.Color.LIGHT_GRAY, " ", 1, " "));
        inventory.setItem(8, builder.createItem(Material.BARRIER, "§aZurück"));
        player.openInventory(inventory);
    }

    public void archivGui(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9*6,"Ticket-Archiv");
        for (Object o : api.getAllClosedTickets()) {
            long id = (long) o;
            inventory.addItem(ticketItem.createTicketItem(id));
        }
        inventory.setItem(53,builder.createItem(Material.BARRIER,"§aZurück"));
        player.openInventory(inventory);
    }
}
