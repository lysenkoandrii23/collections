package com.lysenko.andrii;

public class ArrayList<E> implements List<E> {
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[10];
    }

    public void add(E value) {
        add(size, value);
    }

    public void add(int index, E value) {
        if (index < 0 || index > size) {
            String message = "Index " + index + " is not between 0 and " + size;
            throw new IndexOutOfBoundsException(message);
        }
        if (size == array.length) {
            int lengthNew = array.length * 3 / 2 + 1;
            Object[] arrayNew = new Object[lengthNew];
            System.arraycopy(array, 0, arrayNew, 0, size);
            array = arrayNew;
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    public E get(int index) {
        validateIndex(index);
        return (E) array[index];
    }

    public void set(int index, E value) {
        validateIndex(index);
        array[index] = value;
    }

    public E remove(int index) {
        validateIndex(index);
        E element = (E) array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[--size] = null;
        return element;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(Object value) {
        return indexOf((E) value) > -1;
    }

    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (((E) array[i]).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(E value) {
        for (int i = size - 1; i > -1; i--) {
            if (((E) array[i]).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size; i++) {
            str.append((E) array[i]);
            if (i < size - 1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            String message = "Index " + index + " is not between 0 and " + size;
            throw new IndexOutOfBoundsException(message);
        }
    }
}