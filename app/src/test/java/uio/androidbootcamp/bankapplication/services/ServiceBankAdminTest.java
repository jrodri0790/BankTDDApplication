package uio.androidbootcamp.bankapplication.services;

import org.junit.Before;
import org.junit.Test;
import uio.androidbootcamp.bankapplication.entities.*;

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


}
