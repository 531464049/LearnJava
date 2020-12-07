package clone;

public class CloneTest {
    public static void main(String[] args) {
        try {
            People p = new People("sb", 20);
            p.setHireDay(2020, 10, 20);

            People kp = p.clone();
            kp.setHireDay(2010, 8, 24);

            System.out.println("p hireday :" + p.getHireDay());
            System.out.println("kp hireday :" + kp.getHireDay());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
