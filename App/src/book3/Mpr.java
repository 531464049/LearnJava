package src.book3;

public class Mpr {
    public static void pr(String s) {
        System.out.println(s);
    }

    public static void prf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }
}
