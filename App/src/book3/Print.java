package src.book3;

public class Print {
    public static void print(Object s) {
        System.out.print(s);
    }

    public static void println(Object s) {
        System.out.println(s);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }
}
