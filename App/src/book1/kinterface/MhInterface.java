package src.book1.kinterface;

import src.book1.firstPage.*;

public class MhInterface implements Comparable<MhInterface> {
    private double salary;

    public MhInterface(double aSalary) {
        this.salary = aSalary;
    }

    public int compareTo(MhInterface o) {
        return Double.compare(this.salary, o.salary);
    }
}
