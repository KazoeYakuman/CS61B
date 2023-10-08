package deque;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void magicTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(4);
        ad.removeLast();
        ad.addFirst(6);
        ad.addLast(7);
        ad.removeLast();
        ad.removeFirst();
        ad.addFirst(10);
        ad.addFirst(11);
        ad.addFirst(12);
        ad.removeFirst();
        ad.addFirst(14);
        ad.addLast(15);
        ad.addFirst(16);
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        System.out.println(ad.size());
        ad.printDeque();
        assertEquals((Integer) 4, ad.removeLast());
    }
}
