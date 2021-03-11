package src.book3.p14;

import java.util.ArrayList;
import java.util.List;

import src.book3.Print;

public class FilledList<T> {
    private Class<T> type;

    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < nElements; i++) {
            try {
                result.add(type.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Print.println("filledList");
        FilledList<CountedInteger> f = new FilledList<>(CountedInteger.class);
        List<CountedInteger> a = f.create(10);
        Print.println(a);
    }
}

class CountedInteger {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return Long.toString(this.id);
    }
}
