package uio.androidbootcamp.bankapplication.entities;

import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SavingsAccountTest {

    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValues() throws NegativeValuesException {
        int depositQuantity = -3;
        SavingsAccount savingsAccount = new SavingsAccount();

        savingsAccount.deposit(depositQuantity);
    }

    @Test
    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
        int depositQunaity = 3;
        SavingsAccount savingsAccount = new SavingsAccount();

        savingsAccount.deposit(depositQunaity);

        assertThat(depositQunaity, is(savingsAccount.balance));
    }
}
