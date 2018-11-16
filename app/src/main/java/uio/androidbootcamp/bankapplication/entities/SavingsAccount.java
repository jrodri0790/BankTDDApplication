package uio.androidbootcamp.bankapplication.entities;

import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;

class SavingsAccount {

    public int balance;

    public void deposit(int depositQuantity) throws NegativeValuesException {
        if(depositQuantity >=0){
            this.balance += depositQuantity;
        }
        else{
            throw  new NegativeValuesException();
        }
    }
}
