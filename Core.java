package reviewPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

public class Core {

    Set<Client> clients;

    public Core() {
        this.clients = new ArrayList<Client>();
    }

    public Core(Set<Client> clients) {
        this.clients = clients;
    }

    public boolean registerClient(String name, String surname, String phone_number, String homeAddress) {
        Client newClient = new Client(new Random().nextLong(), name, surname, phone_number, homeAddress, null);
        if (clients.contains(newClient)) {
            return false;
        } else {
            clients.add(newClient);
            return true;
        }
    }

    public int getAmountOfExpiredPaymentMethodsInFuture(String name, int daysUntilNow) {
        int expiredPaymentMethods = 0;
        for (Client client : clients) {
            for (PaymentMethod method : client.getPaymentMethods()) {
                long dateInFuture = new Date().getTime() + daysToMillis(daysUntilNow);
                if (dateInFuture - method.getExpiration_date().getTime() > 0) {
                    expiredPaymentMethods = expiredPaymentMethods + 1;
                }
            }
        }
        return expiredPaymentMethods;
    }

    public addInfinitePaymentMethodToClient(Client client) {
        PaymentMethod method = new PaymentMethod("INFINITE", new Date(), null);
        for (Client client : clients) {
            if (client == client) {
                client.getPaymentMethods().add(method);
            }
        }
    }

    public int getInfinitePaymentMethodsAmount() {
        long abc = 0;
        for (Client client : clients) {
            for (PaymentMethod method : client.getPaymentMethods()) {
                if (method.getType() == "INFINITE") {
                    abc++;
                }
            }
        }
        return abc;
    }

    public double daysToMillis(int days) {
        Logger.getLogger("Default").info("CoreClass is calculating");
        return days * 24 * 60 * 60 * 1000;
    }
}
