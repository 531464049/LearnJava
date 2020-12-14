package src.book1.treeSet;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        System.out.println("x");

        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("qqqq", 1234));
        parts.add(new Item("aaaa", 4567));
        parts.add(new Item("xxxx", 5678));

        System.out.println(parts);

        NavigableSet<Item> sortedByDescription = new TreeSet<>(Comparator.comparing(Item::getDeString));

        sortedByDescription.addAll(parts);
        System.out.println(sortedByDescription);
    }
}

/**
 * Item
 */
class Item implements Comparable<Item> {
    private String description;
    private int partNumber;

    public Item(String des, int partNum) {
        this.description = des;
        this.partNumber = partNum;
    }

    public String getDeString() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[description=" + this.description + " partNumber=" + this.partNumber + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return Objects.equals(this.description, other.description) && this.partNumber == other.partNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.partNumber);
    }

    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(this.partNumber, o.partNumber);
        return diff != 0 ? diff : this.description.compareTo(o.description);
    }

}
