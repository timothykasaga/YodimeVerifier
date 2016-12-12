package TicketingBeans;

/**
 * Created by user on 11/25/2016.
 */

public class TicketSellVerification {
    private String ticket_name;
    private String category;
    private String expiry_date;
    private String amount;
    private  String reference_id;

    public String getAmount() {
        return amount;
    }

    public String getTicket_name() {
        return ticket_name;
    }

    public String getCategory() {
        return category;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public String getReference_id() {
        return reference_id;
    }

    public TicketSellVerification(String ticket_name, String category, String expiry_date, String amount, String reference_id) {
        this.ticket_name = ticket_name;
        this.category = category;
        this.expiry_date = expiry_date;
        this.amount = amount;
        this.reference_id = reference_id;
    }
}
