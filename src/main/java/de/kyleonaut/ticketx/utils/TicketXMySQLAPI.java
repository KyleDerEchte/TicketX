package de.kyleonaut.ticketx.utils;

import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TicketXMySQLAPI {

    private final MySQL con = new MySQL();

    public String getPlayerNamebyTicketId(long id){
        return (String) con.get("SELECT playerName FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "playerName").get(0);
    }
    public TicketStatus getStatusByTicketId(long id){
        String s = (String) con.get("SELECT status FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "status").get(0);
        return TicketStatus.valueOf(s);
    }
    public String getInDateByTicketId(long id){
        return (String) con.get("SELECT inDate FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "inDate").get(0);
    }
    public String getOutDateByTicketId(long id){
        return (String) con.get("SELECT outDate FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "outDate").get(0);
    }
    public String getTicketTextByTicketId(long id){
        return (String) con.get("SELECT ticket_text FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "ticket_text").get(0);
    }
    public String getModeratorNameByTicketId(long id){
        return (String) con.get("SELECT moderatorName FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "moderatorName").get(0);
    }
    public String getWeltNameByTicketId(long id){
        return (String) con.get("SELECT weltName FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "weltName").get(0);
    }
    public String getTicketTitleById(long id){
        return (String) con.get("SELECT ticket_title FROM ticketx_tickets WHERE ticket_id = "+id+"",
                "ticket_title").get(0);
    }
    public ArrayList<Object> getAllOpenTickets(){
        return con.get("SELECT ticket_id FROM ticketx_tickets WHERE status = 'IN_BEARBEITUNG'","ticket_id");
    }
    public void changeStatusAtId(long id,TicketStatus status){
        con.execute("UPDATE ticketx_tickets SET status = '"+status+"' WHERE ticket_id = "+id+"");
    }
    public void setModerator(long id, Player moderator){
        con.execute("UPDATE ticketx_tickets SET moderatorName = '"+moderator.getName()+"', moderatorUUID = '"
                +moderator.getUniqueId()+"' WHERE ticket_id = "+id+"");
    }
    public void setOutDate(long id){
        LocalDateTime ldt = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.GERMANY).format(ldt);
        con.execute("UPDATE ticketx_tickets SET outDate = '"+date+"' WHERE ticket_id = "+id+"");
    }
    public ArrayList<Object> getAllClosedTickets(){
        return con.get("SELECT ticket_id FROM ticketx_tickets WHERE status != 'IN_BEARBEITUNG' ORDER BY `ticketx_tickets`.`ticket_id` DESC","ticket_id");
    }
}
