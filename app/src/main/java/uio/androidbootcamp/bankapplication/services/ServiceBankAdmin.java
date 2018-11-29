package uio.androidbootcamp.bankapplication.services;

import uio.androidbootcamp.bankapplication.entities.Client;

public class ServiceBankAdmin {
    private Client client;

    public void createClient() {
        client = new Client("", "", "");
    }

    public Client getClient() {
        return client;
    }
}
