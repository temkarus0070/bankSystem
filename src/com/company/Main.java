package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {
public static final int N =500;
public static final int SERVICE_TIME=50;
    public static final int CLIENTS_PER_MINUTE=50;

    public static void main(String[] args) {
        BankCash bankCash=new BankCash(new Random(new Date().getTime()).nextDouble()*300000);
        List<BankClerk> clerkList = generate(N,bankCash);
        runClerks(clerkList);
        Generator generator=new Generator(clerkList,CLIENTS_PER_MINUTE);
        generator.start();
    }

    public static List<BankClerk> generate(int n,BankCash bankCash){
        List<BankClerk> list=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new BankClerk(bankCash));
        }
        return list;
    }

    public static void runClerks( List<BankClerk> clerks){
        for (BankClerk clerk : clerks) {
            clerk.start();
        }
    }
}
