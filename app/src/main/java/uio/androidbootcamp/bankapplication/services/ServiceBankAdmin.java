package uio.androidbootcamp.bankapplication.services;

import uio.androidbootcamp.bankapplication.entities.Client;

public class ServiceBankAdmin {
    private Client client;

    public void createClient(String name, String lastName, String id) {
        client = new Client(name, lastName, id);
    }

    public Client getClient() {
        return client;
    }
}
