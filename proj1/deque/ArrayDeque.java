package deque;

import java.util.Iterator;
public class ArrayDeque<Item> implements Iterable<Item>{
    private Item[] items;
    private int size;
    private int head, tail;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        tail = 0;
        head = 0;
    }

    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        int p = 0;
        for(int i = head; i != tail; i = (i + 1) % items.length) {
            a[p] = items[i];
            p ++;
        }
        head = 0;
        tail = p;
        items = a;
    }

    public void addFirst(Item item) {
        if(size == items.length) {
            resize(size * 2);
        }
        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size ++;
    }

    public void addLast(Item item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size ++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = head; i != tail; i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println("");
    }

    public Item removeFirst() {
        if(size == 0) {
            return null;
        }
        Item ret = items[head];
        head = (head + 1) % items.length;
        size --;
        if(items.length >= 16 && size * 4 <= items.length) {
            resize(items.length / 2);
        }
        return ret;
    }

    public Item removeLast() {
        if(size == 0) {
            return null;
        }
        tail = (tail - 1 + items.length) % items.length;
        size --;
        if(items.length >= 16 && size * 4 <= items.length) {
            resize(items.length / 2);
        }
        return items[tail];
    }

    public Item get(int index) {
        return items[(head + index) % items.length];
    }

    public Iterator<Item> iterator() {
        return new ArrayDequeIterator(head);
    }

    private class ArrayDequeIterator implements Iterator<Item> {
        int index;

        ArrayDequeIterator(int idx) {
            index = idx;
        }
        @Override
        public boolean hasNext() {
            return index != tail;
        }

        @Override
        public Item next() {
            return items[index ++];
        }
    }

    public boolean equals(Object o) {
        if(o instanceof ArrayDeque) {
            ArrayDeque<Item> O = (ArrayDeque<Item>) o;
            if(O.size() == size) {
                for(int i = 0; i < size; i ++) {
                    if(O.items[(O.head + i) % O.items.length] != items[(head + i) % items.length]) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
