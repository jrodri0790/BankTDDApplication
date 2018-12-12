package uio.androidbootcamp.bankapplication.services;

import org.junit.Before;
import org.junit.Test;

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

        serviceBankAdmin.createClient("", "", "");

        assertThat(serviceBankAdmin.getClient(), not(nullValue()));
    }

    @Test
    public void testShouldCreateClientWithGivenName(){
        String givenName = "Jaime";

        serviceBankAdmin.createClient(givenName, "", "");

        assertThat(serviceBankAdmin.getClient().getName(), is(givenName));
    }

    @Test
    public void testShouldCreateClientWithGivenLastName(){
        String lastName = "Borja";

        serviceBankAdmin.createClient("",lastName, "");

        assertThat(serviceBankAdmin.getClient().getLastName(), is(lastName));
    }

    @Test
    public void testShouldCreateClientWithGivenId(){
        String id = "12";

        serviceBankAdmin.createClient("","", id);

        assertThat(serviceBankAdmin.getClient().getId(), is(id));
    }
}
