package org.example;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class A_Main {
    // if while iterating over the collection, we directly try to modify that collection,
    // then the given fail-fast iterator will throw this ConcurrentModificationException.
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        try {
            System.out.println("ArrayList: ");
            Iterator<Integer> iter = arr.iterator();
            while (iter.hasNext()) {
                System.out.print(iter.next() + ", ");

                // ConcurrentModificationException
                System.out.println("\n\nTrying to add an element in between iteration\n");
                arr.add(5);
            }
        }
        catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
    }
}

// Solutions:
// - iterator directly iterator.remove();
// - removal out of iteration
// - removeIf() of the Collection interface
// - filtering using Stream -> .stream().filter(i -> i != 2)