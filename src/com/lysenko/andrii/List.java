package com.lysenko.andrii;

public interface List<E> {
    void add(E value);

    void add(int index, E value);

    E get(int index);

    void set(int index, E value);

    E remove(int index);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(Object value);

    int indexOf(E value);

    int lastIndexOf(E value);
}