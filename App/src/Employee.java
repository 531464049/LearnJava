package src;

public class Employee {
    private String name;
    private double salary;

    public static void main(String[] args) {
        Employee e = new Employee("sb", 20.4);
        System.out.println(e.getName());
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void raiseSalry(double byPercent) {
        double raise = this.salary * byPercent / 100 + this.salary;
        this.salary = raise;
    }

}
