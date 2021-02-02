package de.kyleonaut.ticketx.utils;

public class TicketXMySQLAPI {

    private MySQL con = new MySQL();

    public String getPlayerNamebyTicketId(int id){
        return (String) con.get("SELECT playerName FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }
    public TicketStatus getStatusByTicketId(int id){
        String s = (String) con.get("SELECT status FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
        return TicketStatus.valueOf(s);
    }
    public String getInDateByTicketId(int id){
        return (String) con.get("SELECT inDate FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }
    public String getOutDateByTicketId(int id){
        return (String) con.get("SELECT outDate FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }
    public String getTicketTextByTicketId(int id){
        return (String) con.get("SELECT ticket_text FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }
    public String getModeratorNameByTicketId(int id){
        return (String) con.get("SELECT moderatorName FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }
    public String getWeltNameByTicketId(int id){
        return (String) con.get("SELECT weltName FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }
    public String getTicketTitleById(int id){
        return (String) con.get("SELECT ticket_title FROM ticketx_tickets WHERE ticket_id = "+id+"",
                0).get(0);
    }

}
