package TicketingBeans;

/**
 * Created by user on 11/25/2016.
 */

public class TicketVerification {
    private String ticket_name;
    private String ticket_id;
    private String ticket_category;
    private String ticket_status;
    private String expire_date;
    private String amount_paid;
    private String ticket_reference_id;

    public TicketVerification(String ticket_name, String ticket_id, String ticket_category, String ticket_status, String expire_date, String amount_paid,String ticket_reference_id) {
        this.ticket_name = ticket_name;
        this.ticket_id = ticket_id;
        this.ticket_category = ticket_category;
        this.ticket_status = ticket_status;
        this.expire_date = expire_date;
        this.amount_paid = amount_paid;
        this.ticket_reference_id = ticket_reference_id;
    }

    public String getTicket_reference_id() {
        return ticket_reference_id;
    }

    public String getTicket_name() {
        return ticket_name;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public String getTicket_category() {
        return ticket_category;
    }

    public String getTicket_status() {
        return ticket_status;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public String getAmount_paid() {
        return amount_paid;
    }
}
