package de.kyleonaut.ticketx.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class InventoryHolder {

    public void übersichtsGui(Player player){
        Builder builder = new Builder();
        Inventory inventory = Bukkit.createInventory(null,9*6,"Ticket-Übersicht");

    }
}
