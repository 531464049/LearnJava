package src.book3.p02;

import src.book1.firstPage.K01;
import src.book3.Mpr;
import src.book3.Print;

public class TestA {

    public static void main(String[] args) {
        TestModel model = new TestModel(2);
        TestA.test(model);
        System.out.println(model.getA());

        // K1 k1 = new K1(2);
        // k1.test();
    }

    public static void test(TestModel model) {
        model.setA(10);

        System.getProperties().list(System.out);
        Mpr.pr(System.getProperty("user.name"));
        Mpr.pr(System.getProperty("java.library.path"));

    }
}

class TestModel {
    private int a = 0;

    public TestModel(int a) {
        this.a = a;
    }

    public TestModel(int a, int b) {
        this.a = a + b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

class TestModel2 extends TestModel {
    public TestModel2(int a) {
        super(a);
    }
}

class K1 {
    private int a;
    private K2 k2;

    public K1(int a) {
        System.out.println("init k1");
        this.a = a;
        this.k2 = new K2(a);
    }

    public void test() {
        System.out.println(a);
    }
}

class K2 {
    private int a;
    private K1 k1;

    public K2(int a) {
        System.out.println("init k2");
        this.a = a;
        this.k1 = new K1(a);
    }
}