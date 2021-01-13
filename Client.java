package reviewPackage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class Client {

    private final Long uniqueId;
    private final String name;
    String surname;

    private String phoneNumber;
    private String home_address;

    public Client(Long uniqueId, String name, String surname, String phoneNumber, String home_address, List<PaymentMethod> paymentMethods) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.home_address = home_address;
        this.paymentMethods = paymentMethods;
    }

    @NotNull
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @NotNull // Should be empty list if no payment methods
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }
}
