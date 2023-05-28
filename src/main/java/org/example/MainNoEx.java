package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class MainNoEx {
    // if while iterating over the collection, we directly try to modify that collection,
    // then the given fail-fast iterator will throw this ConcurrentModificationException.
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        //Modifications once the iteration is done, or
        //Implement the concept of the synchronized block or method
        try {
            System.out.println("ArrayList: ");
            Iterator<Integer> iter = arr.iterator();
            while (iter.hasNext()) {
                System.out.print(iter.next() + ", ");
            }

            // modification is done after the iteration
            System.out.println("\n\nTrying to add an element in between iteration: "
                            + arr.add(5));

            System.out.println("\nUpdated ArrayList: ");
            iter = arr.iterator();
            while (iter.hasNext()) {
                System.out.print(iter.next() + ", ");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}