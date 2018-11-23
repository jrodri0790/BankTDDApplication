package uio.androidbootcamp.bankapplication.entities;

import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

class CurrentAccount {

    private double balance;
    private double discountPercentage;

    public CurrentAccount(){
        this.discountPercentage = 0.01;
    }

    public void deposit(double depositQuantity) throws NegativeValuesException {
        if(depositQuantity >= 0){
            BigDecimal depositQuantityDecimal = new BigDecimal(depositQuantity);
            BigDecimal discountDecimal = new BigDecimal(this.discountPercentage).setScale(3, BigDecimal.ROUND_HALF_UP);
            double onePercentageDiscount = (depositQuantityDecimal.multiply(discountDecimal)).setScale(2,RoundingMode.HALF_UP).doubleValue();
            double discount = depositQuantity - onePercentageDiscount;
            this.balance += discount;
        }
        else {
            throw new NegativeValuesException();
        }
    }

    public void withdraw(double withdrawQuantity) throws NegativeValuesException, ValueUpperBalanceException {
        if(withdrawQuantity < 0){
            throw new NegativeValuesException();
        }
        else if(withdrawQuantity > this.balance){
            throw new ValueUpperBalanceException();
        }
        this.balance = (new BigDecimal(this.balance).subtract(BigDecimal.valueOf(withdrawQuantity), MathContext.DECIMAL32)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getBalance() {
        return balance;
    }
}
