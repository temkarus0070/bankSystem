package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BankClerk extends Thread {
    private final LinkedList<Client> clientList = new LinkedList<>();
    private BankCash bankCash;

    public BankClerk(BankCash bankCash) {
        this.bankCash = bankCash;
    }

    @Override
    public void run() {

            while (true) {
                synchronized (clientList) {
                if (clientList.size() == 0) {
                    try {
                        clientList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Client client = clientList.pop();
                Operation bankOperation = client.getBankOperation();
                if (bankOperation == Operation.GET_MONEY) {
                    System.out.printf("client %s снимает деньги в размере %f \n", client.toString(),client.getOperationSum());
                    synchronized (bankCash) {
                        if (bankCash.getBalance() >=client.getOperationSum()){
                            try {
                                sleep(client.getTimeForServe());
                                bankCash.getMoney(client.getOperationSum());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            System.out.printf("client %s получил отказ в снятии  денег в размере %f \n", client.toString(), client.getOperationSum());
                        continue;
                        }
                        }
                } else {
                    System.out.printf("client %s принес  деньги в размере %f \n", client.toString(),client.getOperationSum());
                    try {
                        sleep(client.getTimeForServe());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bankCash.putMoney(client.getOperationSum());

                }
                System.out.printf("client %s обслужен \n", client.toString());
                }
        }
    }

    public synchronized void addClient(Client client) {
        synchronized (clientList) {
            clientList.add(client);
            clientList.notify();
        }
    }

    public int getClientsCount(){
        return clientList.size();
    }


}
