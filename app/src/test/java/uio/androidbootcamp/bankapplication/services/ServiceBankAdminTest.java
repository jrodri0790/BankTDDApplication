package uio.androidbootcamp.bankapplication.services;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceBankAdminTest {


    @Test
    public void testShouldCreateClient(){
        ServiceBankAdmin serviceBankAdmin = new ServiceBankAdmin();

        serviceBankAdmin.createClient();

        assertThat(serviceBankAdmin.getClient(), not(nullValue()));
    }
}
