package com.lysenko.andrii;

public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;

        Node() {
            item = null;
            next = null;
        }

        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean isEnd() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();

    public void push(T item) {
        top = new Node<T>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.isEnd()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<String>();
        for (String s : "Go on java!".split(" ")) {
            linkedStack.push(s);
        }
        String str;
        while ((str = linkedStack.pop()) != null) {
            System.out.println(str);
        }
    }
}