package uio.androidbootcamp.bankapplication.entities;

import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CurrentsAccountTest1 {

//    @Test(expected = NegativeValuesException.class)
//    public void testShouldThrowExceptionWhenReceiveNegativeValues() throws NegativeValuesException {
//        int depositQuantity = -3;
//        CurrentAccount currentAccount = new CurrentAccount();
//
//        currentAccount.deposit(depositQuantity);
//    }
//
//    @Test
//    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
//        int depositQunaity = 3;
//        CurrentAccount currentAccount = new CurrentAccount();
//
//        currentAccount.deposit(depositQunaity);
//
//        assertThat(depositQunaity, is(currentAccount.balance));
//    }
//
//
////****************************
//    @Test
//    public void testShouldWithdrawWhenReceivePositiveValues() throws NegativeValuesException  {
//        int withdrawQuantity = 3;
//        CurrentAccount currentAccount = new CurrentAccount();
//
//        currentAccount.withdraw(withdrawQuantity);
//
//        assertThat(withdrawQuantity*(-1), is(currentAccount.balance));
//    }
//
//
//    @Test(expected = NegativeValuesException.class)
//    public void testShouldThrowExceptionWhenPositiveValueIsUpperBalance() throws NegativeValuesException  {
//        int withdrawQuantity = 3;
//        CurrentAccount currentAccount = new CurrentAccount();
//
//        currentAccount.withdraw(withdrawQuantity);
//    }
//
//
//
//    @Test
//    public void testShouldDiscountNPercentageWhenCallDeposit() throws NegativeValuesException  {
//        int depositQuantity = 3;
//        CurrentAccount currentAccount = new CurrentAccount();
//
//        currentAccount.deposit(depositQuantity);
//        int discount = 1*depositQuantity/100;
//        currentAccount.deposit(depositQuantity);
//
//        assertThat(depositQunaity-discount, is(currentAccount.balance));
//    }
//
//
//
//    @Test(expected = NegativeValuesException.class)
//    public void testShouldThrowExceptionWhenPossitiveValueForWithdrawUpper2000()() throws NegativeValuesException  {
//        int withdrawQuantity = 3000;
//        CurrentAccount currentAccount = new CurrentAccount();
//
//        currentAccount.withdraw(withdrawQuantity);
//    }
//
//
//    @Test
//    public void testShouldInitializeSavingAccountWithInterest0dot00015() throws NegativeValuesException  {
//        CurrentAccount savingsAccount;
//
//        //Act
//        currentAccount = new CurrentAccount();
//
//        assertThat(0.0001, is(currentAccount.interest))
//    }
//
//
//    @Test
//    public void testShouldInitializeSavingAccountWithInterest0dot00015() throws NegativeValuesException  {
//        CurrentAccount savingsAccount;
//
//        //Act
//        currentAccount = new CurrentAccount();
//
//        assertThat("Active", is(currentAccount.status))
//    }






}
