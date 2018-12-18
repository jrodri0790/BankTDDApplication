package uio.androidbootcamp.bankapplication.services;

import org.junit.Before;
import org.junit.Test;
import uio.androidbootcamp.bankapplication.entities.*;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

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

        double depositQuantityResult = serviceBankAdmin.depositToCurrentAccount(depositQuantity, currentAccount);

        assertThat(depositQuantityResult, is(depositQuantity-depositQuantity*0.01));
    }


    @Test
    public void testShouldWithdrawFromCurrentAccount() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double depositQuantity = 120.00;
        double withdrawQuantity = 80.00;
        CurrentAccount currentAccount = new CurrentAccount("1");
        double currentBalanceQuantity  = serviceBankAdmin.depositToCurrentAccount(depositQuantity, currentAccount);

        double withdrawQuantityResult = serviceBankAdmin.withdrawFromCurrentAccount(withdrawQuantity, currentAccount);

        assertThat(withdrawQuantityResult,is(currentBalanceQuantity - withdrawQuantity));
    }


    @Test
    public void testShouldDepositToSavingtAccount() throws NegativeValuesException {
        double depositQuantity = 100.00;
        SavingsAccount savingsAccount = new SavingsAccount("1");

        double depositQuantityResult = serviceBankAdmin.depositToSavingAccount(depositQuantity, savingsAccount);

        assertThat(depositQuantityResult, is(depositQuantity));
    }


    @Test
    public void testShouldWithdrawFromSavingAccount() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double depositQuantity = 120.00;
        double withdrawQuantity = 80.00;
        SavingsAccount savingAccount = new SavingsAccount("1");
        double currentBalanceQuantity  = serviceBankAdmin.depositToSavingAccount(depositQuantity, savingAccount);

        double withdrawQuantityResult = serviceBankAdmin.withdrawFromSavingAccount(withdrawQuantity, savingAccount);

        assertThat(withdrawQuantityResult,is(currentBalanceQuantity - withdrawQuantity));
    }

    @Test
    public void testShouldTransferBetweenSavingAccount() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double transferQuantity = 100.00;
        double depostiQuantity = 250.00;
        SavingsAccount accountBankTransmitter = new SavingsAccount("2");
        accountBankTransmitter.deposit(depostiQuantity);
        SavingsAccount accountBankReceiver = new SavingsAccount("3");

        double currentBalanceAccountBankReceiver = serviceBankAdmin.transferBetweenSavingAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);

        assertThat(currentBalanceAccountBankReceiver, is(transferQuantity));
    }


    @Test
    public void testShouldTransferBetweenCurrentAccount() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double transferQuantity = 100.00;
        double depostiQuantity = 250.00;
        AccountBank accountBankTransmitter = new CurrentAccount("45");
        ((CurrentAccount) accountBankTransmitter).deposit(depostiQuantity);
        AccountBank accountBankReceiver = new CurrentAccount("55");

        double currentBalanceAccountBankReceiver = serviceBankAdmin.transferBetweenCurrentAccounts(accountBankTransmitter, accountBankReceiver, transferQuantity);

        assertThat(currentBalanceAccountBankReceiver, is(transferQuantity-transferQuantity*0.01));
    }


}
