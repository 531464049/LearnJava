package src.book3.p02;

import src.book3.Mpr;

public class TestA {

    public static void main(String[] args) {
        TestModel model = new TestModel(2);
        TestA.test(model);
        System.out.println(model.getA());
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}