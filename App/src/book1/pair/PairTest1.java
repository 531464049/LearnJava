package src.book1.pair;

public class PairTest1 {
    public static void main(String[] args) {
        String[] word = { "sklskl", "iuwheriuh", "qbndjkv", "jkjk" };
        Pair<String> mm = ArrayAlg.minmax(word);
        if (mm != null) {
            System.out.println(mm.getFirst() + "--" + mm.getSecond());
        }
    }
}

class ArrayAlg {
    @SuppressWarnings("unchecked")
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (T s : a) {
            if (min.compareTo(s) > 0) {
                min = s;
            }
            if (max.compareTo(s) < 0) {
                max = s;
            }
        }
        return new Pair<>(min, max);
    }
}