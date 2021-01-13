package reviewPackage;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.logging.Logger;

class PaymentMethod {

    private String type;

    private Date addition_date;
    private Date expiration_date;

    public PaymentMethod(String type, Date addition_date, Date expiration_date) {
        this.type = type;
        this.addition_date = addition_date;
        this.expiration_date = expiration_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public String getType() {
        return type;
    }

    @NotNull
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public boolean checkIfPaymentMethodWillBeValidAfterGivenAmountOfDaysByComparingDates(int amountOfDays) {
        Date now = new Date();
        double daysInMillis = daysToMillis(amountOfDays);
        long futureDateInMillis = addMillis(now.getTime(), daysInMillis);;
        return futureDateInMillis - getExpiration_date().getTime() > 0;
    }

    public double daysToMillis(int days) {
        return days * 24 * 60 * 60 * 1000;
    }

    public long addMillis(long firstValue, long secondValue) {
        Logger.getLogger("Default").info("addMillis method called");
        return firstValue + secondValue;
    }
}
