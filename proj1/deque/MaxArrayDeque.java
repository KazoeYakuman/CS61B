package deque;

import java.util.*;

public class MaxArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int head, tail;
    Comparator<T> comp;

    public MaxArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        tail = 0;
        head = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int p = 0;
        for (int i = head; i != tail; i = (i + 1) % items.length) {
            a[p] = items[i];
            p++;
        }
        head = 0;
        tail = p;
        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = head; i != tail; i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = items[head];
        head = (head + 1) % items.length;
        size--;
        if (items.length >= 16 && size * 4 <= items.length) {
            resize(items.length / 2);
        }
        return ret;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + items.length) % items.length;
        size--;
        if (items.length >= 16 && size * 4 <= items.length) {
            resize(items.length / 2);
        }
        return items[tail];
    }

    @Override
    public T get(int index) {
        return items[(head + index) % items.length];
    }

    public MaxArrayDeque(Comparator<T> c) {
        items = (T[]) new Object[8];
        size = 0;
        tail = 0;
        head = 0;
        comp = c;
    }

    public T max() {
        if (size == 0) return null;
        if (size == 1) return items[head];
        T ret = items[head];
        for (int i = (head + 1) % items.length; i != tail; i = (i + 1) % items.length) {
            if (comp.compare(items[i], ret) > 0) {
                ret = items[i];
            }
        }
        return ret;
    }

    public T max(Comparator<T> c) {
        if (size == 0) return null;
        if (size == 1) return items[head];
        T ret = items[head];
        for (int i = (head + 1) % items.length; i != tail; i = (i + 1) % items.length) {
            if (c.compare(items[i], ret) > 0) {
                ret = items[i];
            }
        }
        return ret;
    }

    public Iterator<T> iterator() {
        return new MaxArrayDeque.MaxArrayDequeIterator(head);
    }

    private class MaxArrayDequeIterator implements Iterator<T> {
        int index;

        MaxArrayDequeIterator(int idx) {
            index = idx;
        }

        @Override
        public boolean hasNext() {
            return index != tail;
        }

        @Override
        public T next() {
            return items[index++];
        }
    }
}
