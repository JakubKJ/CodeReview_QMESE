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
