package com.company;

public class BankCash {
    private Double sum;

    public BankCash(Double sum) {
        this.sum = sum;
    }

    public  void putMoney(double money){
        synchronized (sum) {
            sum += money;
        }
    }

    public  double getMoney(double money){
            sum -= money;
            return money;
    }

    public double getBalance(){
            return sum;
    }

}
