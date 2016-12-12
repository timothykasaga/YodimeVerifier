package TicketingBeans;

/**
 * Created by user on 11/25/2016.
 */

public class TicketSellConfirmation {
    private String ticket_name;
    private  String ticket_category;
    private String ticket_id;
    private  String ticket_status;
    private String ticket_expiry_date;
    private  String ticket_transaction_amount;
    private String getTicket_transaction_charge;
    private  String ticket_total_amount;
    private String getTicket_transaction_id;
    private  String ticket_payment_id;
    private String ticket_terminal_id;
    private  String getTicket_transaction_status;
    private String getTicket_transaction_date;

    public TicketSellConfirmation(String ticket_name, String ticket_category, String ticket_id, String ticket_status,
                                  String ticket_expiry_date, String ticket_transaction_amount,
                                  String getTicket_transaction_charge, String ticket_total_amount, String ticket_terminal_id,
                                  String getTicket_transaction_status, String getTicket_transaction_date) {
        this.ticket_name = ticket_name;
        this.ticket_category = ticket_category;
        this.ticket_id = ticket_id;
        this.ticket_status = ticket_status;
        this.ticket_expiry_date = ticket_expiry_date;
        this.ticket_transaction_amount = ticket_transaction_amount;
        this.getTicket_transaction_charge = getTicket_transaction_charge;
        this.ticket_total_amount = ticket_total_amount;
        this.ticket_terminal_id = ticket_terminal_id;
        this.getTicket_transaction_status = getTicket_transaction_status;
        this.getTicket_transaction_date = getTicket_transaction_date;
    }

    public String getTicket_name() {
        return ticket_name;
    }

    public String getTicket_category() {
        return ticket_category;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public String getTicket_status() {
        return ticket_status;
    }

    public String getTicket_expiry_date() {
        return ticket_expiry_date;
    }

    public String getTicket_transaction_amount() {
        return ticket_transaction_amount;
    }

    public String getGetTicket_transaction_charge() {
        return getTicket_transaction_charge;
    }

    public String getTicket_total_amount() {
        return ticket_total_amount;
    }

    public String getGetTicket_transaction_id() {
        return getTicket_transaction_id;
    }

    public String getTicket_payment_id() {
        return ticket_payment_id;
    }

    public String getTicket_terminal_id() {
        return ticket_terminal_id;
    }

    public String getGetTicket_transaction_status() {
        return getTicket_transaction_status;
    }

    public String getGetTicket_transaction_date() {
        return getTicket_transaction_date;
    }
}
