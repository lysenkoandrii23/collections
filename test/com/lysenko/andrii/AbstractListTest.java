package com.lysenko.andrii;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractListTest {

    ArrayList list;

    @Before
    public abstract void setUp() throws Exception;

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        list.add("first");
        list.add("second");
        list.add(10, "third");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException1() {
        list.add("first");
        list.add("second");
        list.add(-1, "third");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException3() {
        list.add("first");
        list.add("second");
        list.set(10, "third");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException4() {
        list.add("first");
        list.add("second");
        list.set(-1, "third");
    }

    @Test
    public void testNoOutOfBoundsException() {
        while (list.size() < 20) {
            list.add("one more");
        }
        assertTrue(list.size() == 20);
    }

    @Test
    public void testAdd() throws Exception {
        list.add("first");
        list.add("second");
        assertTrue(list.size() == 2);
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    public void testAdd1() throws Exception {
        list.add("first");
        list.add("second");
        assertEquals("second", list.get(1));
        list.add(1,"third");
        assertTrue(list.size() == 3);
        assertEquals("third", list.get(1));
    }

    @Test
    public void testGet() throws Exception {
        list.add("first");
        list.add("second");
        assertEquals("first", list.get(0));
    }

    @Test
    public void testSet() throws Exception {
        list.add("first");
        list.add("second");
        list.set(1, "third");
        assertEquals("third", list.get(1));
    }

    @Test
    public void testRemove() throws Exception {
        list.add("first");
        list.add("second");
        assertEquals("first", list.remove(0));
        assertTrue(list.size() == 1);
        assertEquals("second", list.remove(0));
        assertTrue(list.size() == 0);
    }

    @Test
    public void testClear() throws Exception {
        list.add("first");
        list.add("second");
        list.clear();
        assertTrue(list.size() == 0);
    }

    @Test
    public void testSize() throws Exception {
        list.add("first");
        list.add("second");
        assertTrue(list.size() == 2);
        list.add("third");
        assertTrue(list.size() == 3);
    }

    @Test
    public void testIsEmpty() throws Exception {
        list.add("first");
        list.add("second");
        assertFalse(list.isEmpty());
        while (list.size() > 0) {
            list.remove(0);
        }
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        list.add("first");
        list.add("second");
        assertFalse(list.contains("third"));
        assertFalse(list.contains(new Object()));
        list.add("third");
        assertTrue(list.contains("third"));
    }

    @Test
    public void testIndexOf() throws Exception {
        list.add("first");
        list.add("second");
        assertTrue(list.indexOf("third") == -1);
        assertTrue(list.indexOf("first") == 0);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        list.add("first");
        list.add("second");
        assertTrue(list.lastIndexOf("third") == -1);
        assertTrue(list.lastIndexOf("first") == 0);
        list.add("first");
        assertTrue(list.lastIndexOf("first") == 2);
    }

    @Test
    public void testToString() throws Exception {
        list.add("first");
        list.add("second");
        assertTrue("[first, second]".equals(list.toString()));
        list.clear();
        assertTrue("[]".equals(list.toString()));
    }
}