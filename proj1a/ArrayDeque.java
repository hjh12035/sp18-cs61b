public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private double factor = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void doubleSize() {
        T[] newItems = (T[]) new Object[items.length * 2];
        System.arraycopy(items, nextLast,
                         newItems, newItems.length - (items.length - nextLast),
                  items.length - nextLast);
        System.arraycopy(items, 0, newItems, 0, nextLast);
        nextFirst = newItems.length - (items.length - nextLast) - 1;
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            doubleSize();
        }
        size++;
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    public void addLast(T item) {
        if (size == items.length) {
            doubleSize();
        }
        size++;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    private void halfSize() {
        T[] newItems = (T[]) new Object[items.length / 2];
        if (nextLast < nextFirst) {
            System.arraycopy(items, nextFirst + 1,
                             newItems, newItems.length - (items.length - (nextFirst + 1)),
                      items.length - (nextFirst + 1));
            System.arraycopy(items, 0, newItems, 0, nextLast);
            nextFirst = newItems.length - (items.length - (nextFirst + 1)) - 1;
        } else {
            System.arraycopy(items, nextFirst + 1, newItems, 0, size);
            nextFirst = newItems.length - 1;
            nextLast = size;
        }
        items = newItems;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size < items.length * factor) {
            halfSize();
        }
        size--;
        nextFirst = (nextFirst + 1) % items.length;
        T tmp = items[nextFirst];
        items[nextFirst] = null;
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size < items.length * factor) {
            halfSize();
        }
        size--;
        nextLast = (nextLast - 1 + items.length) % items.length;
        T tmp = items[nextLast];
        items[nextLast] = null;
        return tmp;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + index + 1) % items.length];
    }
}
