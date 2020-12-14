package src.book1.linkList;

import java.util.*;

public class LinkListText {
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            String s = "link_a_" + (i + 1);
            a.add(s);
        }

        List<String> b = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            String s = "link_b_" + (i + 1);
            b.add(s);
        }

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();
        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }
            aIter.add(bIter.next());
        }
        System.out.println(a);

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(b);

        a.removeAll(b);
        System.out.println(a);
    }

}
