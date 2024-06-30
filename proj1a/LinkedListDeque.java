public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }

        public T getRecursive(int index) {
            if (index == 0) {
                return this.item;
            }
            return this.next.getRecursive(index - 1);
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        size++;
        Node ptr = sentinel.next;
        sentinel.next = new Node(item, sentinel, ptr);
        ptr.prev = sentinel.next;
    }

    public void addLast(T item) {
        size++;
        Node ptr = sentinel.prev;
        sentinel.prev = new Node(item, ptr, sentinel);
        ptr.next = sentinel.prev;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        Node ptr = sentinel.next;
        sentinel.next = ptr.next;
        ptr.next.prev = sentinel;
        return ptr.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        Node ptr = sentinel.prev;
        sentinel.prev = ptr.prev;
        ptr.prev.next = sentinel;
        return ptr.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node ptr = sentinel.next;
        for (int i = 0; i < index; ++i) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return sentinel.next.getRecursive(index);
    }
}
