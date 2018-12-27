package uio.androidbootcamp.bankapplication.services;

import org.junit.Before;
import org.junit.Test;
import uio.androidbootcamp.bankapplication.entities.*;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceBankAdminTest {

    private ServiceBankAdmin serviceBankAdmin;

    @Before
    public void init(){
        serviceBankAdmin = new ServiceBankAdmin();
    }


    @Test
    public void testShouldCreateClient(){
        Client client  = serviceBankAdmin.createClient("", "", "");

        assertThat(client, not(nullValue()));
    }

    @Test
    public void testShouldCreateClientWithGivenName(){
        String givenName = "Jaime";

        Client client = serviceBankAdmin.createClient(givenName, "", "");

        assertThat(client.getName(), is(givenName));
    }

    @Test
    public void testShouldCreateClientWithGivenLastName(){
        String lastName = "Borja";

        Client client = serviceBankAdmin.createClient("",lastName, "");

        assertThat(client.getLastName(), is(lastName));
    }

    @Test
    public void testShouldCreateClientWithGivenId(){
        String id = "12";

        Client cLient = serviceBankAdmin.createClient("","", id);

        assertThat(cLient.getId(), is(id));
    }


    @Test
    public void testShouldCreateCurrentAccount(){
        AccountBank currentAccount = serviceBankAdmin.createCurrentAccount("");

        assertThat(currentAccount, not(nullValue()));
    }

    @Test
    public void testShouldBeInstanceOfCurrentAccount(){
        AccountBank currentAccount = serviceBankAdmin.createCurrentAccount("");

        assertThat(true, is(currentAccount instanceof CurrentAccount));
    }

    @Test
    public void testShouldBeInstanceOfSavingAccount(){
        AccountBank savingsAccount = serviceBankAdmin.createSavingAccount("");

        assertThat(true, is(savingsAccount instanceof SavingsAccount));
    }

    @Test
    public void testShouldCreateCurrentAccountWithGivenId(){
        String id = "3";

        AccountBank currentAccount = serviceBankAdmin.createCurrentAccount(id);

        assertThat(currentAccount.getId(), is(id));
    }

    @Test
    public void testShouldCreateSavingAccountWithGivenId(){
        String id = "4";

        AccountBank savingsAccount = serviceBankAdmin.createSavingAccount(id);

        assertThat(savingsAccount.getId(), is(id));
    }

    @Test
    public void testShouldAssociateClientWithCurrentAccount(){
        Client client = new Client("Pepito", "Lara", "1");
        AccountBank accountBank = new CurrentAccount("33");

        Client clientResult= serviceBankAdmin.addAccountToClient(client, accountBank);

        assertThat(clientResult.getAccountsBank().get(0).getId(), is(accountBank.getId()));

    }

    @Test
    public void testShouldAssociateClientWithSavingAccount(){
        Client client = new Client("Pepito", "Lara", "1");
        AccountBank accountBank = new SavingsAccount("33");

        Client clientResult= serviceBankAdmin.addAccountToClient(client, accountBank);

        assertThat(clientResult.getAccountsBank().get(0).getId(), is(accountBank.getId()));
    }


    @Test
    public void testShouldDepositToCurrentAccount() throws NegativeValuesException {
        double depositQuantity = 120.00;
        CurrentAccount currentAccount = new CurrentAccount("1");

        double depositQuantityResult = serviceBankAdmin.depositToCurrentAccount(depositQuantity, currentAccount).getBalance();

        assertThat(depositQuantityResult, is(depositQuantity-depositQuantity*0.01));
    }


    @Test
    public void testShouldWithdrawFromCurrentAccount() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double depositQuantity = 120.00;
        double withdrawQuantity = 80.00;
        CurrentAccount currentAccount = new CurrentAccount("1");
        CurrentAccount currentAccountResult = serviceBankAdmin.depositToCurrentAccount(depositQuantity, currentAccount);
        double currentBalanceQuantity  = currentAccountResult.getBalance();

        double withdrawQuantityResult = serviceBankAdmin.withdrawFromCurrentAccount(withdrawQuantity, currentAccountResult);

        assertThat(withdrawQuantityResult,is(currentBalanceQuantity - withdrawQuantity));
    }


    @Test
    public void testShouldDepositToSavingAccount() throws NegativeValuesException {
        double depositQuantity = 100.00;
        SavingsAccount savingsAccount = new SavingsAccount("1");

        double depositQuantityResult = serviceBankAdmin.depositToSavingAccount(depositQuantity, savingsAccount).getBalance();

        assertThat(depositQuantityResult, is(depositQuantity));
    }


    @Test
    public void testShouldWithdrawFromSavingAccount() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double depositQuantity = 120.00;
        double withdrawQuantity = 80.00;
        SavingsAccount savingAccount = new SavingsAccount("1");

        SavingsAccount savingAccountResult = serviceBankAdmin.depositToSavingAccount(depositQuantity, savingAccount);
        double currentBalanceQuantity  = savingAccountResult.getBalance();

        double withdrawQuantityResult = serviceBankAdmin.withdrawFromSavingAccount(withdrawQuantity, savingAccountResult).getBalance();

        assertThat(withdrawQuantityResult,is(currentBalanceQuantity - withdrawQuantity));
    }


    @Test
    public void testShouldTranferMethodReturnsTwoTransferAccounts() throws ValueUpperBalanceException, ValueUpper1000Exception, ValueUpper2000Exception, NegativeValuesException {
        double transferQuantity = 100.00;
        AccountBank accountBankTransmitter = new SavingsAccount("2");
        AccountBank accountBankReceiver = new CurrentAccount("4");
        accountBankTransmitter.deposit(200.00);

        TransferAccounts transferAccounts = serviceBankAdmin.transferBetweenAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);

        assertThat(transferAccounts.accountTransmitter.getId(), is(accountBankTransmitter.getId()));
        assertThat(transferAccounts.accountReceiver.getId(), is(accountBankReceiver.getId()));
    }

    @Test
    public void testShouldTransferBetweenSavingAccounts() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception, ValueUpper1000Exception {
        double transferQuantity = 100.00;
        double depostiQuantity = 250.00;
        AccountBank accountBankTransmitter = new SavingsAccount("2");
        accountBankTransmitter.deposit(depostiQuantity);
        AccountBank accountBankReceiver = new SavingsAccount("3");

        TransferAccounts transferAccountsResult = serviceBankAdmin.transferBetweenAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);

        assertThat(transferAccountsResult.accountTransmitter.getBalance(), is(depostiQuantity-transferQuantity));
        assertThat(transferAccountsResult.accountReceiver.getBalance(), is(transferQuantity));
    }


    @Test
    public void testShouldTransferBetweenCurrentAccounts() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception, ValueUpper1000Exception {
        double transferQuantity = 100.00;
        double depostiQuantity = 250.00;
        AccountBank accountBankTransmitter = new CurrentAccount("45");
        accountBankTransmitter.deposit(depostiQuantity);
        AccountBank accountBankReceiver = new CurrentAccount("55");

        TransferAccounts transferAccountsResult = serviceBankAdmin.transferBetweenAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);
        double expectedBalanceAccountReceiver = transferQuantity-(transferQuantity*0.01);
        double expectedBalanceAccountTransmitter = depostiQuantity-(depostiQuantity*0.01)-transferQuantity;

        assertThat(transferAccountsResult.accountTransmitter.getBalance(), is(expectedBalanceAccountTransmitter));
        assertThat(transferAccountsResult.accountReceiver.getBalance(), is(expectedBalanceAccountReceiver));
    }

    @Test
    public void testShouldTransferBetweenSavingAccountCurrentAccount() throws NegativeValuesException, ValueUpper2000Exception, ValueUpperBalanceException, ValueUpper1000Exception {
        double transferQuantity = 100.00;
        double depostiQuantity = 250.00;
        AccountBank accountBankTransmitter = new SavingsAccount("45");
        accountBankTransmitter.deposit(depostiQuantity);
        AccountBank accountBankReceiver = new CurrentAccount("55");

        TransferAccounts transferAccountsResult = serviceBankAdmin.transferBetweenAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);
        double expectedBalanceAccountReceiver = transferQuantity-(transferQuantity*0.01);
        double expectedBalanceAccountTransmitter = depostiQuantity-transferQuantity;

        assertThat(transferAccountsResult.accountTransmitter.getBalance(), is(expectedBalanceAccountTransmitter));
        assertThat(transferAccountsResult.accountReceiver.getBalance(), is(expectedBalanceAccountReceiver));
    }

    @Test
    public void testShouldTransferBetweenCurrentAccountSavingAccount() throws NegativeValuesException, ValueUpper2000Exception, ValueUpperBalanceException, ValueUpper1000Exception {
        double transferQuantity = 100.00;
        double depostiQuantity = 250.00;
        AccountBank accountBankTransmitter = new CurrentAccount("45");
        accountBankTransmitter.deposit(depostiQuantity);
        AccountBank accountBankReceiver = new SavingsAccount("55");

        TransferAccounts transferAccountsResult = serviceBankAdmin.transferBetweenAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);
        double expectedBalanceAccountTransmitter = depostiQuantity-(depostiQuantity*0.01)-transferQuantity;

        assertThat(transferAccountsResult.accountTransmitter.getBalance(), is(expectedBalanceAccountTransmitter));
        assertThat(transferAccountsResult.accountReceiver.getBalance(), is(transferQuantity));
    }


    @Test
    public void testShouldSaveClientsWhenCreateClient(){
        String name = "Pedro";
        String lastName = "Alvarez";
        String id = "67";

        serviceBankAdmin.createClient(name, lastName,id);

        assertThat(serviceBankAdmin.getClients().get(0).getName(), is(name));
    }

     @Test
    public void testShouldSaveCurrentAccountWhenCreateCurrentAccount(){
        String id = "90";

        serviceBankAdmin.createCurrentAccount(id);

        assertThat(serviceBankAdmin.getCurrentAccounts().get(0).getId(), is(id));
     }

     @Test
    public void testShouldSaveSavingAccountWhenCreateSavingAccount(){
        String id = "788";

        serviceBankAdmin.createSavingAccount(id);

        assertThat(serviceBankAdmin.getSavingAccounts().get(0).getId(), is(id));
     }


     @Test
    public void testShouldReturnClientWhenIdIsGiven(){
        String id = "12";
        String name = "Felipe";
        String lastName = "Portilla";
        serviceBankAdmin.createClient(name, lastName, id);
        List<Client> clients = serviceBankAdmin.getClients();

        Client client = serviceBankAdmin.searchClient(id);

        assertThat(client.getId(), is(id));
    }


    @Test
    public void testShouldReturnAccountWhenIdIsGiven(){
        String id = "89";
        serviceBankAdmin.createCurrentAccount("12");
        serviceBankAdmin.createSavingAccount("89");

        AccountBank accountBank = serviceBankAdmin.searchAccountBank(id);

        assertThat(accountBank.getId(), is(id));
    }

    @Test
    public void testShouldRemoveAccountWhenAccountIsAddedToClient(){
        String id = "12";
        String name = "Felipe";
        String lastName = "Portilla";
        Client client = serviceBankAdmin.createClient(name, lastName, id);
        String idAccount = "89";
        AccountBank currentAccountBank = serviceBankAdmin.createCurrentAccount("12");
        AccountBank savingAccountBank = serviceBankAdmin.createSavingAccount("89");

        Client clientResult = serviceBankAdmin.addAccountToClient(client, savingAccountBank);

        assertThat(serviceBankAdmin.getSavingAccounts().size(), is(0));

    }

}
