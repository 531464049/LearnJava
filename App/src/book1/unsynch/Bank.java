package src.book1.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final double[] accounts;
    private Lock bankLock;

    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(this.accounts, initialBalance);
        this.bankLock = new ReentrantLock();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        if (from > this.accounts.length - 1 || to > this.accounts.length - 1) {
            return;
        }
        if (this.accounts[from] < amount) {
            return;
        }
        this.bankLock.lock();
        try {
            System.out.println(Thread.currentThread());
            this.accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d%n", amount, from, to);
            this.accounts[to] += amount;
            System.out.printf("totle balance: %10.2f%n", this.getTotleBalance());
        } finally {
            this.bankLock.unlock();
        }

    }

    public double getTotleBalance() {
        this.bankLock.lock();
        try {
            double sum = 0;
            for (double d : accounts) {
                sum += d;
            }
            return sum;
        } finally {
            this.bankLock.unlock();
        }

    }

    public int size() {
        return this.accounts.length;
    }
}
