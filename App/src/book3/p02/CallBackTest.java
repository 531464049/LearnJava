package src.book3.p02;

import src.book3.Print;

/**
 * CallBackTest
 */
public class CallBackTest {
    public static void main(String[] args) {
        KTestCallBack k = new KTestCallBack(new KCallBack() {
            @Override
            public void k_test(int a) {
                Print.println(a);
            }
        });
        k.test();

        k.k_test(new KCallBack() {
            @Override
            public void k_test(int a) {
                Print.println(a);
            }
        });
    }
}

class KTestCallBack {

    private KCallBack callBack;

    public KTestCallBack(KCallBack callback) {
        this.callBack = callback;
    }

    public void test() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                callBack.k_test(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void k_test(KCallBack callBack) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                callBack.k_test(33333);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

interface KCallBack {
    public void k_test(int a);
}

/**
 * Incrementable
 */
interface Incrementable {
    void increment();
}

/**
 * Callee1
 */
class Callee1 implements Incrementable {
    private int i = 0;

    @Override
    public void increment() {
        i++;
        Print.println(i);
    }
}

class MyIncrement {
    public void increment() {
        Print.println("MyIncrement increment");
    }

    static public void f(MyIncrement mi) {
        mi.increment();
    }
}

class Callee2 extends MyIncrement {
    private int i = 0;

    @Override
    public void increment() {
        Print.println("Callee2 increment start");
        super.increment();
        i++;
        Print.println("Callee2 increment end " + i);
    }

    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    public Incrementable getCallBackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callBackReference;

    public Caller(Incrementable cbh) {
        callBackReference = cbh;
    }

    public void go() {
        callBackReference.increment();
    }
}

class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2  = new Callee2();
        MyIncrement.f(c2);

        Caller cal1 = new Caller(c1);
        Caller cal2 = new Caller(c2.getCallBackReference());
    }
}