package com.company;

import java.util.*;

public class Generator extends Thread{
    private List<BankClerk> clerks;
    private Random random=new Random(new Date().getTime());
private int clientsCount=0;

    public Generator(List<BankClerk> clerks, int clientsCount) {
        this.clerks = clerks;
        this.clientsCount = clientsCount;
    }

    @Override
    public void run() {
        while (true){
            for (int i = 0; i < clientsCount; i++) {
               double v=random.nextDouble();
                    Operation operation = Operation.PUT_MONEY;
                    if (v <= 0.25) {
                        operation = Operation.GET_MONEY;
                    }
                    long time = (Main.SERVICE_TIME + random.nextInt(10000)) / 2;
                    double sum = random.nextDouble() * 1000000;
                    Client client = new Client(time, operation, sum);
                    Optional<BankClerk> bankClerk = clerks.stream().min(Comparator.comparingInt(BankClerk::getClientsCount));
                    if (bankClerk.isEmpty()) {
                        System.out.println("error in clerks");
                    } else
                        bankClerk.get().addClient(client);
            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
