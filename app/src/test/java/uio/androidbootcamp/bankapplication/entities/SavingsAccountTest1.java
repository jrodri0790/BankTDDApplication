package uio.androidbootcamp.bankapplication.entities;

import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SavingsAccountTest1 {

//    @Test(expected = NegativeValuesException.class)
//    public void testShouldThrowExceptionWhenReceiveNegativeValues() throws NegativeValuesException {
//        int depositQuantity = -3;
//        SavingsAccount savingsAccount = new SavingsAccount();
//
//        savingsAccount.deposit(depositQuantity);
//    }
//
//    @Test
//    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
//        int depositQunaity = 3;
//        SavingsAccount savingsAccount = new SavingsAccount();
//
//        savingsAccount.deposit(depositQunaity);
//
//        assertThat(depositQunaity, is(savingsAccount.balance));
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
//    @Test(expected = NegativeValuesException.class)
//    public void testShouldThrowExceptionWhenPossitiveValueForWithdrawUpper1000() throws NegativeValuesException  {
//        int depositQunaity = 3000;
//        SavingsAccount savingsAccount = new SavingsAccount();
//
//        savingsAccount.withdraw(depositQunaity);
//    }
//
//
//    @Test
//    public void testShouldInitializeSavingAccountWithInterest0dot0001() throws NegativeValuesException  {
//        SavingsAccount savingsAccount;
//
//        //Act
//        savingsAccount = new SavingsAccount();
//
//        assertThat(0.0001, is(savingsAccount.interest))
//    }
//
//
//    @Test
//    public void testShouldInitializeSavingAccountWithStatusActive() throws NegativeValuesException  {
//        SavingsAccount savingsAccount;
//
//        //Act
//        savingsAccount = new SavingsAccount();
//
//        assertThat("Active", is(savingsAccount.status))
//    }
//
//
//
//

}
