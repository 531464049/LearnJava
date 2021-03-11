package src.book3.p14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import src.book3.Print;

public class ShowMethods {
    private static String usage = "usage:\n" + "ShowMethods qualified.clas.name\n"
            + "To Show all methods in class or:\n" + "To search for methods involving 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if (args.length < 1) {
            Print.println(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            if (args.length == 1) {
                for (Method method : methods) {
                    Print.print(p.matcher(method.toString()).replaceAll(""));
                }
                for (Constructor ctor : ctors) {
                    Print.print(p.matcher(ctor.toString()).replaceAll(""));
                }
                lines = methods.length + ctors.length;
            } else {
                for (Method method : methods) {
                    if (method.toString().indexOf(args[1]) != -1) {
                        Print.print(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                }
                for (Constructor ctor : ctors) {
                    if (ctor.toString().indexOf(args[1]) != -1) {
                        Print.print(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
