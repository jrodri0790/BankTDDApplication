package uio.androidbootcamp.bankapplication.entities;

import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SavingsAccountTest {

    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForDeposit() throws NegativeValuesException {
        int depositQuantity = -3;
        SavingsAccount savingsAccount = new SavingsAccount();

        savingsAccount.deposit(depositQuantity);
    }

    @Test
    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
        double depositQuantity = 3;
        SavingsAccount savingsAccount = new SavingsAccount();

        savingsAccount.deposit(depositQuantity);

        assertThat(depositQuantity, is(savingsAccount.getBalance()));
    }

    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForWithdraw() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        int withdrawQuantity = -3;
        SavingsAccount savingsAccount = new SavingsAccount();

        savingsAccount.withdraw(withdrawQuantity);
    }

    @Test
    public void testShouldWithdrawWhenReceivePositiveValues() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double withdrawQuantity = 3.00;
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.deposit(withdrawQuantity);
        double balanceZero = 0.00;

        savingsAccount.withdraw(withdrawQuantity);

        assertThat(balanceZero, is(savingsAccount.getBalance()));
    }

    @Test(expected = ValueUpperBalanceException.class)
    public void testShouldThrowExceptionWhenWithdrawValueIsUpperBalance() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double withdrawQuantity = 3.00;
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.deposit(withdrawQuantity-1.00);

        savingsAccount.withdraw(withdrawQuantity);
    }

    @Test(expected = ValueUpper1000Exception.class)
    public void testShouldThrowExceptionWhenWithdrawValueIsUpper1000() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double withdrawQuantity = 1200.00;
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.deposit(withdrawQuantity);

        savingsAccount.withdraw(withdrawQuantity);
    }

    @Test
    public void testShouldSetInterest0001WhenCreateSavingAccount(){
        double interest = 0.0001;

        SavingsAccount savingsAccount = new SavingsAccount();

        assertThat(interest, is(savingsAccount.getInterest()));
    }

    @Test
    public void testShouldSetStatusActiveWhenCreateSavingAccount(){
        String status = "Activa";

        SavingsAccount savingsAccount = new SavingsAccount();

        assertThat(status, is(savingsAccount.getStatus()));
    }


}
