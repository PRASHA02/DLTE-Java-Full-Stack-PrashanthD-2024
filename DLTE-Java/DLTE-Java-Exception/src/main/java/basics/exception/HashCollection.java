package basics.exception;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class HashCollection {
    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();
        linkedHashSet.add(100);linkedHashSet.add(20);linkedHashSet.add(117);
        linkedHashSet.add(10);linkedHashSet.add(20);linkedHashSet.add(30);

        HashSet<Integer> hashSet=new HashSet<>(linkedHashSet);

        System.out.println("Linked Hash Set"+linkedHashSet);
        System.out.println("Hash Set"+hashSet);

        TreeSet<Integer> treeSet=new TreeSet<>();treeSet.addAll(linkedHashSet);
        System.out.println("Tree Set"+treeSet);



    }
}
