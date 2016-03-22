package com.lysenko.andrii;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class HashMapTest {

    HashMap<String, String> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<String, String>();
    }

    @Test
    public void testPut() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertTrue(map.size() == 2);
        assertTrue(map.get("key1").equals("value1"));
        assertTrue(map.get("key2").equals("value2"));
    }

    @Test
    public void testPutIfAbsent() throws Exception {
        map.putIfAbsent("key1", "value1");
        map.putIfAbsent("key1", "value2");
        assertTrue(map.get("key1").equals("value1"));
        map.putIfAbsent("key3", "value3");
        assertTrue(map.get("key3").equals("value3"));
    }

    @Test
    public void testPutAll() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("key3", "value3");
        hashMap.put("key4", "value4");
        hashMap.put("key5", "value5");
        map.putAll(hashMap);
        assertTrue(map.size() == 5);
        assertTrue(map.get("key3").equals("value3"));
        assertTrue(map.get("key4").equals("value4"));
        assertTrue(map.get("key5").equals("value5"));
    }

    @Test
    public void testGet() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key2", "value3");
        assertTrue(map.get("key1").equals("value1"));
        assertTrue(map.get("key2").equals("value3"));
        assertTrue(map.get("key5") == null);
    }

    @Test
    public void testContainsKey() throws Exception {
        map.put("key1", "value1");
        assertTrue(map.containsKey("key1"));
        assertFalse(map.containsKey("key5"));
    }

    @Test
    public void testContainsValue() throws Exception {
        map.put("key1", "value1");
        map.put("key2", null);
        assertTrue(map.containsValue("value1"));
        assertFalse(map.containsValue("value5"));
        assertTrue(map.containsValue(null));
    }

    @Test
    public void testRemove() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertTrue(map.get("key1").equals("value1"));
        map.remove("key1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemove1() throws Exception {
        map.put("key1", "value1");
        map.remove("key13");
    }

    @Test
    public void testKeys() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        ArrayList<String> keysTest = map.keys();
        assertTrue(keysTest.size() == 2);
        assertTrue(keysTest.contains("key1"));
        assertTrue(keysTest.contains("key2"));
    }

    @Test
    public void testSize() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertTrue(map.size() == 3);
    }

    @Test
    public void testToString() throws Exception {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertTrue(map.toString().equals("<<key1, value1><key2, value2>>"));
    }
}