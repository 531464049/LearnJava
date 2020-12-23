package src.book3.p02;

public class TestA {

    public static void main(String[] args) {
        TestModel model = new TestModel(2);
        TestA.test(model);
        System.out.println(model.getA());
    }

    public static void test(TestModel model) {
        model.setA(10);

        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
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