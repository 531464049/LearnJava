package src;

public class Employee {
    private static int nextID = 1;
    private String name;
    private double salary;
    private int ID;

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("sb1", 200);
        staff[1] = new Employee("sb2", 300);
        staff[2] = new Employee("sb3", 400);

        for (Employee employee : staff) {
            employee.setID();
            System.out.println("name=" + employee.getName() + ",id=" + employee.getID());
        }

        int n = Employee.getNextID();
        System.out.println("next id = " + n);
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.ID = 0;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getID() {
        return this.ID;
    }

    public void setID() {
        this.ID = this.nextID;
        nextID++;
    }

    public static int getNextID() {
        return Employee.nextID;
    }

    public void raiseSalry(double byPercent) {
        double raise = this.salary * byPercent / 100 + this.salary;
        this.salary = raise;
    }

}
