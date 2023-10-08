package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node pre;

        Node(T i, Node p, Node n) {
            item = i;
            next = n;
            pre = p;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    private T getR(int index, Node n) {
        if (index == 0) {
            return n.item;
        }
        return getR(index - 1, n.next);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getR(index, head.next);
    }

    @Override
    public void addFirst(T item) {
        Node tmp = new Node(item, head, head.next);
        tmp.pre.next = tmp;
        tmp.next.pre = tmp;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node tmp = new Node(item, tail.pre, tail);
        tmp.pre.next = tmp;
        tmp.next.pre = tmp;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node tmp = head.next;
        while (tmp != tail) {
            System.out.print(tmp.item + " ");
            tmp = tmp.next;
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = head.next.item;
        head.next = head.next.next;
        head.next.pre = head;
        size -= 1;
        return ret;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = tail.pre.item;
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
        size -= 1;
        return ret;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node tmp = head.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator(0);
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int index;

        LinkedListDequeIterator(int idx) {
            index = idx;
        }

        @Override
        public boolean hasNext() {
            return index < LinkedListDeque.this.size();
        }

        @Override
        public T next() {
            return LinkedListDeque.this.get(index++);
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> O = (Deque<T>) o;
            if (O.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (!Objects.equals(O.get(i), get(i))) {
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
