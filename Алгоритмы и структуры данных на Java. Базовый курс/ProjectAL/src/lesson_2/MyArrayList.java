package lesson_2;

public class MyArrayList<E extends Comparable<E>> {
    private int size = 0;
    private Object[] list = new Object[1];

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }

    public int size() {
        return size;
    }

    public void insert(E e) {
        if (size == list.length) {
            resize(2 * list.length);
        }
        list[size++] = e;
    }

    public boolean delete(E e) {
        int i = 0;
        while (i < size && !list[i].equals(e)) {
            i++;
        }
        if (i == size) {
            return false;
        }
        for (int j = i; j < size - 1; j++) {
            list[j] = list[j + 1];
        }
        list[size - 1] = null;
        size--;

        if (size == list.length / 4 && size > 0) {
            resize(list.length / 2);
        }

        return true;
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (E) list[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }

        list[index] = e;
    }

    public boolean find(E e) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + list[i] + ", ";
        }
        return s;
    }

    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((E) list[j], (E) list[min])) {
                    min = j;
                }
            }
            exch(i, min);
        }
    }

    public void insertionSort() {
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less((E) list[j], (E) list[j - 1])) {
                    exch(j, j - 1);
                }
                else {
                    break;
                }
            }
        }
    }

    private boolean less(E e1, E e2) {
        return e1.compareTo(e2) < 0;
    }

    private void exch(int i, int j) {
        E temp = (E) list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}