package uio.androidbootcamp.bankapplication.entities;

import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

class CurrentAccount {

    private double balance;

    public void deposit(double depositQuantity) throws NegativeValuesException {
        if(depositQuantity >= 0){
            double onePercetageDiscount = depositQuantity/100;
            double discount = depositQuantity - onePercetageDiscount;
            this.balance += discount;
        }
        else {
            throw new NegativeValuesException();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double withdrawQuantity) throws NegativeValuesException, ValueUpperBalanceException {
        if(withdrawQuantity < 0){
            throw new NegativeValuesException();
        }
        else if(withdrawQuantity > this.balance){
            throw new ValueUpperBalanceException();
        }
        this.balance -= withdrawQuantity;
    }
}
