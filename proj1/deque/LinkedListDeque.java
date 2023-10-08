package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Iterable<Item>, Deque<Item> {
    public class Node {
        public Item item;
        public Node next;
        public Node pre;
        public Node(Item i, Node p, Node n) {
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

    private Item getR(int index, Node n) {
        if(index == 0) {
            return n.item;
        }
        return getR(index-1, n.next);
    }

    public Item getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return getR(index, head.next);
    }

    @Override
    public void addFirst(Item item) {
        Node tmp = new Node(item, head, head.next);
        tmp.pre.next = tmp;
        tmp.next.pre = tmp;
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        Node tmp = new Node(item, tail.pre, tail);
        tmp.pre.next = tmp;
        tmp.next.pre = tmp;
        size += 1;
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
        Node tmp = head.next;
        while(tmp != tail) {
            System.out.print(tmp.item + " ");
            tmp = tmp.next;
        }
        System.out.println("");
    }

    @Override
    public Item removeFirst() {
        if(size == 0) {
            return null;
        }
        Item ret = head.next.item;
        head.next = head.next.next;
        head.next.pre = head;
        size -= 1;
        return ret;
    }

    @Override
    public Item removeLast() {
        if(size == 0) {
            return null;
        }
        Item ret = tail.pre.item;
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
        size -= 1;
        return ret;
    }

    @Override
    public Item get(int index) {
        if(index >= size) {
            return null;
        }
        Node tmp = head.next;
        for(int i = 0; i < index; i ++) {
            tmp = tmp.next;
        }
        return tmp.item;
    }

    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator(0);
    }

    private class LinkedListDequeIterator implements Iterator<Item> {
        int index;

        LinkedListDequeIterator(int idx) {
            index = idx;
        }
        @Override
        public boolean hasNext() {
            return index < LinkedListDeque.this.size();
        }

        @Override
        public Item next() {
            return LinkedListDeque.this.get(index++);
        }
    }

    public boolean equals(Object o) {
        if(o instanceof LinkedListDeque) {
            LinkedListDeque<Item> O = (LinkedListDeque<Item>) o;
            if(O.size() == size) {
                Node tmp1 = O.head.next;
                Node tmp2 = head.next;
                for(int i = 0; i < size; i ++) {
                    if(! tmp1.item.equals(tmp2.item)) {
                        return false;
                    }
                    tmp1 = tmp1.next;
                    tmp2 = tmp2.next;
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
