package uio.androidbootcamp.bankapplication.services;

import uio.androidbootcamp.bankapplication.entities.AccountBank;
import uio.androidbootcamp.bankapplication.entities.Client;
import uio.androidbootcamp.bankapplication.entities.CurrentAccount;
import uio.androidbootcamp.bankapplication.entities.SavingsAccount;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

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

    public CurrentAccount depositToCurrentAccount(double depositQuantity, CurrentAccount currentAccount) throws NegativeValuesException {
        CurrentAccount currentAccountClone = new CurrentAccount(currentAccount.getId());
        currentAccountClone.deposit(depositQuantity);
        return currentAccountClone;
    }

    public double withdrawFromCurrentAccount(double withdrawQuantity, CurrentAccount currentAccount) throws ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException {
        currentAccount.withdraw(withdrawQuantity);
        return currentAccount.getBalance();
    }

    public SavingsAccount depositToSavingAccount(double depositQuantity, SavingsAccount savingsAccount) throws NegativeValuesException {
        SavingsAccount savingsAccountClone = new SavingsAccount(savingsAccount.getId());
        savingsAccountClone.deposit(depositQuantity);
        return savingsAccountClone;
    }

    public SavingsAccount withdrawFromSavingAccount(double withdrawQuantity, SavingsAccount savingAccount) throws ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
        SavingsAccount savingAccountClone = new SavingsAccount(savingAccount.getId());
        savingAccountClone.withdraw(withdrawQuantity);
        return savingAccountClone;
    }

    public double transferBetweenAccounts(AccountBank accountBankTransmitter, AccountBank accountBankReceiver, double transferQuantity) throws ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
       accountBankTransmitter.withdraw(transferQuantity);
       accountBankReceiver.deposit(transferQuantity);
        return accountBankReceiver.getBalance();
    }
}
