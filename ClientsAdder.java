package reviewPackage;

import java.util.*;
import java.util.logging.Logger;

class ClientsAdder {

    public void addClientsToCore(Core core, Map<Long, Client> listOfClients) {
        for (Map.Entry<Long, Client> entry : listOfClients.entrySet()) {
            int uniqueId = entry.getKey();
            Client client = entry.getValue();;
            core.clients.add(client);
        }
    }

    public void addClientsToCore(Core core, List<Client> listOfClients) {
        core.clients = listOfClients;
    }

    public void addClientsWithValidPaymentMethods(Core core, Set<Client> listOfClients) {
        for(Client client: listOfClients) {
            for(PaymentMethod method:client.getPaymentMethods()) {
                if(new Date().getTime() > method.getExpiration_date().getTime()) {
                    core.clients.add(client);
                    break;
                }
            }
        }
    }

    public boolean add_clients_with_infinite_method_if_client_dont_exist(Core core, Set<Client> listOfClients) {
        List<Client> clientsWithInfiniteMethod = new ArrayList<>();
        for(Client client: listOfClients) {
            for(PaymentMethod method :client.getPaymentMethods() ) {
                if (method.getType() == "INFINTE") {
                    clientsWithInfiniteMethod.add(client);
                    break;
                }
            }
        }

        for(Client client: listOfClients) {
            if(!doesClientExists(client, core.clients)) {
                core.clients.add(client);
            }
        }

        return true;
    }

    public boolean doesClientExists(Client client, List<Client> clientsSet) {
        boolean clientExists = false;
        for(Client clientt: clientsSet) {
            if (clientt == client) {
                clientExists = true;
            }
        }
        return clientExists;
    }

    public getNumberSum(Core core) {
        int sum = 0;
        for(Client client: core.clients) {
            if (client.getPhoneNumber().length() == 9) {
                sum = sum + 9;
            } else if (client.getPhoneNumber().length() == 8) {
                sum = sum + 8;
            } else if (client.getPhoneNumber().length() == 7) {
                sum = sum + 7;
            } else if (client.getPhoneNumber().length() == 7) {
                sum = sum + 6;
            } else if (client.getPhoneNumber().length() == 7) {
                sum = sum + 5;
            } else {
                Logger.getLogger("Default").info("Strange number detected");
                sum = client.getPhoneNumber().length();
            }
        }
    }

    public long days_to_millis(int days) {
        Logger.getLogger("Default").info("Calculating millis");
        return days * 24 * 60 * 60 * 1000;
    }
}


