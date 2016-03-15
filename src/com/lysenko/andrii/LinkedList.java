package com.lysenko.andrii;

public class LinkedList<E> implements List<E> {
    private static class Node<U> {
        U item;
        Node<U> prev;
        Node<U> next;

        Node(U item, Node<U> prev, Node<U> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> firstElement;
    private Node<E> lastElement;
    private int size;

    public void add(E value) {
        add(size, value);
    }

    public void add(int index, E value) {
        if (index < 0 || index > size) {
            String message = "Index " + index + " is not between 0 and " + size;
            throw new IndexOutOfBoundsException(message);
        }
        if (size == 0) { // adding first element of list
            firstElement = lastElement = new Node<E>(value, null, null);
        } else if (index == 0) { // adding in first position
            firstElement = new Node<E>(value, null, firstElement);
            firstElement.next.prev = firstElement;
        } else if (index == size) { // adding in last position
            lastElement = new Node<E>(value, lastElement, null);
            lastElement.prev.next = lastElement;
        } else { // adding in the middle position
            Node<E> prevNode = firstElement;
            for (int i = 0; i < index -1; i++) {
                prevNode = prevNode.next;
            }
            prevNode.next = new Node<E>(value, prevNode, prevNode.next);
            prevNode.next.next.prev = prevNode.next;
        }
        size++;
    }

    public E get(int index) {
        validateIndex(index);
        if (index == size - 1) { // last element
            return lastElement.item;
        } else {
            Node<E> node = firstElement;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.item;
        }
    }

    public void set(int index, E value) {
        validateIndex(index);
        if (index == size - 1) { // last element
            lastElement.item = value;
        } else {
            Node<E> node = firstElement;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.item = value;
        }
    }

    public E remove(int index) {
        validateIndex(index);
        E element;
        if (size == 1) { // one element in list
            element = firstElement.item;
            firstElement = lastElement = null;
        } else if (index == 0) { // deleting from first position
            element = firstElement.item;
            firstElement = firstElement.next;
            firstElement.prev = null;
        } else if (index == size) { // deleting from last position
            element = lastElement.item;
            lastElement = lastElement.prev;
            lastElement.next = null;
        } else { // deleting from the middle position
            Node<E> prevNode = firstElement;
            for (int i = 0; i < index -1; i++) {
                prevNode = prevNode.next;
            }
            element = prevNode.next.item;
            prevNode.next = prevNode.next.next;
            prevNode.next.prev = prevNode;
        }
        size--;
        return element;
    }

    public void clear() {
        firstElement = lastElement = null;
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
        int index = 0;
        Node<E> node = firstElement;
        for (int i = 0; i < size; i++) {
            if (node.item.equals(value)) {
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }

    public int lastIndexOf(E value) {
        int index = size - 1;
        Node<E> node = lastElement;
        for (int i = 0; i < size; i++) {
            if (node.item.equals(value)) {
                return index;
            }
            index--;
            node = node.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        Node<E> node = firstElement;
        for (int i = 0; i < size; i++) {
            str.append(node.item);
            if (i < size - 1) {
                str.append(", ");
            }
            node = node.next;
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