package src.book1.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Class[] interfaces = new Class[] { Comparable.class };
            Object proxy = Proxy.newProxyInstance(null, interfaces, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println("key = " + key);
        int result = Arrays.binarySearch(elements, key);

        if (result >= 0) {
            System.out.println(elements[result]);
        }
    }
}

/**
 * TraceHandler
 */
class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object t) {
        this.target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(this.target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(",");
                }
            }
        }
        System.out.println(")");
        return method.invoke(this.target, args);
    }
}