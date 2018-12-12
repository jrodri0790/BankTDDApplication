package uio.androidbootcamp.bankapplication.entities;

import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

abstract class AccountBank {

    protected double interest;
    protected double balance;
    protected String status;

    abstract void deposit(double depositQuantity) throws NegativeValuesException;

    abstract void withdraw(double withdrawQuantity) throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception, ValueUpper1000Exception;

}
