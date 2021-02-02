package de.kyleonaut.ticketx.events;

import de.kyleonaut.ticketx.utils.Config;
import de.kyleonaut.ticketx.utils.InventoryHolder;
import de.kyleonaut.ticketx.utils.TicketStatus;
import de.kyleonaut.ticketx.utils.TicketXMySQLAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickHandler implements Listener {

    private InventoryHolder holder = new InventoryHolder();
    private TicketXMySQLAPI api = new TicketXMySQLAPI();
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        String title = e.getInventory().getTitle();
        Player player = (Player) e.getWhoClicked();
        switch (title){
            case "Ticket-Moderation":
                e.setCancelled(true);
                if (e.getSlot() == 53){
                    player.closeInventory();
                }else if (e.getSlot() == 52){
                    holder.archivGui(player);
                }else if (e.getCurrentItem().getType().equals(Material.BOOK)) {
                    holder.erweiterteModerationsGui(player, e);
                }
                break;
            case "Erweiterte Moderation":
                e.setCancelled(true);
                long id = Long.parseLong(e.getInventory().getItem(0).getItemMeta().getLore().get(e.getInventory()
                        .getItem(0).getItemMeta().getLore().size()-1).replace("§a➥ Ticket-Id: ",""));
                if (e.getSlot() == 4){
                    api.changeStatusAtId(id, TicketStatus.ERLEDIGT);
                    api.setModerator(id,player);
                    api.setOutDate(id);
                    holder.moderationsUebersichtGui(player);
                    Config.sendMessage(player," §aDer Ticketstatus wurde auf Erledigt gesetzt.");
                }else if (e.getSlot() == 1){
                    api.setModerator(id,player);
                    Config.sendMessage(player," §aDu bist jetzt der Ticketmoderator.");
                    holder.moderationsUebersichtGui(player);
                }else if (e.getSlot() == 2){
                    api.changeStatusAtId(id,TicketStatus.ANGENOMMEN);
                    api.setModerator(id,player);
                    api.setOutDate(id);
                    Config.sendMessage(player," §aDer Ticketstatus wurde auf Angenommen gesetzt.");
                    holder.moderationsUebersichtGui(player);
                }else if (e.getSlot() == 8){
                    holder.moderationsUebersichtGui(player);
                }else if (e.getSlot() == 3){
                    api.changeStatusAtId(id,TicketStatus.ABGELEHNT);
                    api.setModerator(id,player);
                    api.setOutDate(id);
                    Config.sendMessage(player," §aDer Ticketstatus wurde auf Abgelehnt gesetzt.");
                    holder.moderationsUebersichtGui(player);
                }
                break;
            case "Ticket-Archiv":
                e.setCancelled(true);
                if (e.getSlot() == 53){
                    holder.moderationsUebersichtGui(player);
                }else{
                    try{
                        id = Long.parseLong(e.getCurrentItem().getItemMeta().getLore().get(e.getCurrentItem().getItemMeta().getLore().size()-1).replace("§a➥ Ticket-Id: ",""));
                        holder.reopenTicket(player,id);
                    }catch (NullPointerException ignored){
                    }
                }
            case "Neueröffnen":
                e.setCancelled(true);
                id = Long.parseLong(e.getInventory().getItem(0).getItemMeta().getLore().get(e.getInventory()
                        .getItem(0).getItemMeta().getLore().size()-1).replace("§a➥ Ticket-Id: ",""));
                if (e.getSlot() == 2){
                    api.changeStatusAtId(id,TicketStatus.IN_BEARBEITUNG);
                    holder.moderationsUebersichtGui(player);
                    api.clearOutDate(id);
                }else if (e.getSlot() == 4){
                    holder.archivGui(player);
                }else if (e.getSlot() == 6){
                    holder.moderationsUebersichtGui(player);
                }
                break;
            case "Ticket-Übersicht":
                e.setCancelled(true);
                if (e.getSlot() == 53){
                    player.closeInventory();
                }

        }

    }
}
