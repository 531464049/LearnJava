package src.book1.unsynch;

import java.util.*;

public class Bank {

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(this.accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {
        if (from > this.accounts.length - 1 || to > this.accounts.length - 1) {
            return;
        }
        if (this.accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread());
        this.accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d%n", amount, from, to);
        this.accounts[to] += amount;
        System.out.printf("totle balance: %10.2f%n", this.getTotleBalance());
    }

    public double getTotleBalance() {
        double sum = 0;
        for (double d : accounts) {
            sum += d;
        }
        return sum;
    }

    public int size() {
        return this.accounts.length;
    }
}
