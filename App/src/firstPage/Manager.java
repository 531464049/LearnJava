package src.firstPage;

import java.util.ArrayList;

public class Manager extends Employee {
    private double bouns;

    public Manager(String name, double salary, double bouns) {
        super(name, salary);
        this.bouns = bouns;
    }

    public double getBouns() {
        return bouns;
    }

    public void setBouns(double bouns) {
        this.bouns = bouns;
    }

    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + this.bouns;
    }

    public static void main(String[] args) {
        ArrayList<Manager> staff = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String name = "sb" + i;
            double salary = 100 * (i + 1);
            double bouns = 20 * (i + 1);
            Manager manager = new Manager(name, salary, bouns);
            staff.add(manager);
        }

        for (int i = 0; i < staff.size(); i++) {
            Manager ma = (Manager) staff.get(i);
            ma.raiseSalry(20);
        }

        for (Manager manager : staff) {
            System.out.println("name=" + manager.getName() + " salary=" + manager.getSalary());
        }
    }
}
