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

    public Client addAccountToClient(Client client, AccountBank accountBank) {
        Client clientClone = new Client(client.getName(), client.getLastName(), client.getId());
        AccountBank accountBankClone = null;

        if(accountBank instanceof CurrentAccount){
            accountBankClone = new CurrentAccount(accountBank.getId());
        }
        else if(accountBank instanceof SavingsAccount){
            accountBankClone = new SavingsAccount(accountBank.getId());
        }

        clientClone.addOneAccount(accountBankClone);

        return clientClone;
    }
}
