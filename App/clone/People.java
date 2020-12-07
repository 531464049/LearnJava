package clone;

import java.util.Date;
import java.util.GregorianCalendar;

public class People implements Cloneable {
    private String name;
    private int age;
    private Date hireDay;

    public People(String aName, int aAge) {
        this.name = aName;
        this.age = aAge;
        this.hireDay = new Date();
    }

    public People clone() throws CloneNotSupportedException {
        People aPeople = (People) super.clone();
        aPeople.hireDay = (Date) this.hireDay.clone();
        return aPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(int year, int month, int day) {
        this.hireDay = new GregorianCalendar(year, month - 1, day).getTime();
    }

}
