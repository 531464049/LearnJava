package src.book1.unsynch;

public class UnsynchBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INITIALBALANCE = 1000;
    private static final double MAXAMOUNT = 1000;
    private static final int DELAY = 10;

    public static void main(String[] args) {
        System.out.println("start");
        Bank bank = new Bank(NACCOUNTS, INITIALBALANCE);
        for (int i = 0; i < 5; i++) {
            Runnable r = () -> {
                try {
                    while (true) {
                        int fromAccount = (int) (NACCOUNTS * Math.random());
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAXAMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
