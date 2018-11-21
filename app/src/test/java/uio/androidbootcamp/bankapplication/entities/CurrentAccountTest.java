package uio.androidbootcamp.bankapplication.entities;

import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrentAccountTest {


    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForDeposit() throws NegativeValuesException {
        double depositQuantity = -3;
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(depositQuantity);
    }



    @Test
    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
        double depositQunaity = 3;
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(depositQunaity);

        assertThat(depositQunaity, is(currentAccount.getBalance()));
    }



    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForWithdraw() throws NegativeValuesException, ValueUpperBalanceException {
        double withdrawQuantity = -3;
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.withdraw(withdrawQuantity);
    }


    @Test(expected = ValueUpperBalanceException.class)
    public void testShouldThrowExceptionWhenPositiveValueForWithdrawIsUpperBalance() throws NegativeValuesException, ValueUpperBalanceException {
        double withdrawQuantity = 3;
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.deposit(withdrawQuantity-2);

        currentAccount.withdraw(withdrawQuantity);
    }


    @Test
    public void testShouldWithdrawWhenReceivePositiveValuesLowerThanBalance() throws NegativeValuesException, ValueUpperBalanceException {
        double withdrawQuantity = 3;
        double depositQuantity = withdrawQuantity+2;
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.deposit(depositQuantity);

        currentAccount.withdraw(withdrawQuantity);

        assertThat(depositQuantity - withdrawQuantity, is(currentAccount.getBalance()));
    }


    @Test
    public void testShouldWithdrawWhenReceivePositiveValuesEqualsBalance() throws NegativeValuesException, ValueUpperBalanceException {
        double withdrawQuantity = 3;
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.deposit(withdrawQuantity);
        double balanceZero = 0;

        currentAccount.withdraw(withdrawQuantity-withdrawQuantity/100);

        assertThat(balanceZero, is(currentAccount.getBalance()));
    }


    @Test
    public void testShouldDiscountNPercentageWhenCallDeposit() throws NegativeValuesException  {
        double depositQuantity = 3;
        CurrentAccount currentAccount = new CurrentAccount();
        double onePercentageDiscount = depositQuantity/100;

        currentAccount.deposit(depositQuantity);

        assertThat(depositQuantity-onePercentageDiscount, is(currentAccount.getBalance()));
    }




}
