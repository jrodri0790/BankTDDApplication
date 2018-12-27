package uio.androidbootcamp.bankapplication.services;

import uio.androidbootcamp.bankapplication.entities.*;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import java.util.ArrayList;
import java.util.List;

public class ServiceBankAdmin {

    private List<Client> clients;
    private List<CurrentAccount> currentAccounts;
    private List<SavingsAccount> savingsAccounts;

    public ServiceBankAdmin(){
        this.clients = new ArrayList<>();
        this.currentAccounts = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
    }

    public Client createClient(String name, String lastName, String id) {
        Client newClient = new Client(name, lastName, id);
        this.clients.add(newClient);
        return newClient;
    }


    public AccountBank createCurrentAccount(String id) {
        CurrentAccount currentAccount = new CurrentAccount(id);
        currentAccounts.add(currentAccount);
        return currentAccount;
    }

    public AccountBank createSavingAccount(String id){
        SavingsAccount savingsAccount = new SavingsAccount(id);
        savingsAccounts.add(savingsAccount);
        return savingsAccount;
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

    public CurrentAccount depositToCurrentAccount(double depositQuantity, CurrentAccount currentAccount) throws NegativeValuesException {
        CurrentAccount currentAccountClone = currentAccount.clone();
        currentAccountClone.deposit(depositQuantity);
        return currentAccountClone;
    }

    public double withdrawFromCurrentAccount(double withdrawQuantity, CurrentAccount currentAccount) throws ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException {
        currentAccount.withdraw(withdrawQuantity);
        return currentAccount.getBalance();
    }

    public SavingsAccount depositToSavingAccount(double depositQuantity, SavingsAccount savingsAccount) throws NegativeValuesException {
        SavingsAccount savingsAccountClone = savingsAccount.clone();
        savingsAccountClone.deposit(depositQuantity);
        return savingsAccountClone;
    }

    public SavingsAccount withdrawFromSavingAccount(double withdrawQuantity, SavingsAccount savingAccount) throws ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
        SavingsAccount savingAccountClone = savingAccount.clone();
        savingAccountClone.withdraw(withdrawQuantity);
        return savingAccountClone;
    }

    public TransferAccounts transferBetweenAccounts(AccountBank accountBankTransmitter, AccountBank accountBankReceiver, double transferQuantity) throws ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
        AccountBank accountBankTransmitterClone = accountBankTransmitter.clone();
        AccountBank accountBankReceiverClone = accountBankReceiver.clone();
        accountBankTransmitterClone.withdraw(transferQuantity);
        accountBankReceiverClone.deposit(transferQuantity);
        return new TransferAccounts(accountBankTransmitterClone, accountBankReceiverClone);
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<CurrentAccount> getCurrentAccounts() {
        return this.currentAccounts;
    }

    public List<SavingsAccount> getSavingAccounts() {
        return savingsAccounts;
    }
}
