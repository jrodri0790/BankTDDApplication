package uio.androidbootcamp.bankapplication.services;

import uio.androidbootcamp.bankapplication.entities.AccountBank;
import uio.androidbootcamp.bankapplication.entities.Client;
import uio.androidbootcamp.bankapplication.entities.CurrentAccount;
import uio.androidbootcamp.bankapplication.entities.SavingsAccount;

public class ServiceBankAdmin {

    public Client createClient(String name, String lastName, String id) {
        return new Client(name, lastName, id);
    }


    public AccountBank createCurrentAccount(String id) {
        return new CurrentAccount(id);
    }

    public AccountBank createSavingAccount(String id){
        return new SavingsAccount(id);
    }

}
