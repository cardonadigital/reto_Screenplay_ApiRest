package co.com.sofka.models;

public class Bookingdates {
    public String checkin;
    public String checkout;


    public Bookingdates create(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
        return this;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}
