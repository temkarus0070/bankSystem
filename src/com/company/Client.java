package com.company;

public class Client {
    private long timeForServe;
    private Operation bankOperation;
    private double operationSum;

    public Client(long timeForServe, Operation bankOperation, double operationSum) {
        this.timeForServe = timeForServe;
        this.bankOperation = bankOperation;
        this.operationSum = operationSum;
    }

    public long getTimeForServe() {
        return timeForServe;
    }

    public void setTimeForServe(long timeForServe) {
        this.timeForServe = timeForServe;
    }

    public Operation getBankOperation() {
        return bankOperation;
    }

    public void setBankOperation(Operation bankOperation) {
        this.bankOperation = bankOperation;
    }

    public double getOperationSum() {
        return operationSum;
    }

    public void setOperationSum(double operationSum) {
        this.operationSum = operationSum;
    }
}
