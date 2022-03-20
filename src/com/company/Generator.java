package com.company;

import java.util.*;

public class Generator extends Thread{
    private List<BankClerk> clerks;
    private Random random=new Random(new Date().getTime());

    public Generator(List<BankClerk> clerks) {
        this.clerks = clerks;
    }

    @Override
    public void run() {
        while (true){
            double v = random.nextDouble();
            if (v<=0.5){
                Operation operation=Operation.PUT_MONEY;
                if (v<=0.25){
                    operation=Operation.GET_MONEY;
                }
                long time=(Main.SERVICE_TIME+random.nextInt(10000))/2;
                double sum=random.nextDouble()*100000;
              Client client=new Client(time,operation,sum);
                Optional<BankClerk> bankClerk = clerks.stream().min(Comparator.comparingInt(BankClerk::getClientsCount));
                if(bankClerk.isEmpty()){
                    System.out.println("error in clerks");
                }
                else
                    bankClerk.get().addClient(client);
            }
            else {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
