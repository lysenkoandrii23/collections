package com.lysenko.andrii;

import java.util.ArrayList;

public class HashMap<K, V> {
    private static class Entry<K,V> {
        K key;
        V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<");
            stringBuilder.append(key);
            stringBuilder.append(", ");
            stringBuilder.append(value);
            stringBuilder.append(">");
            return stringBuilder.toString();
        }
    }

    private ArrayList<Entry<K, V>>[] buckets;
    private int size;
    private int bucketNumber = 16;
    private static double CAPACITY = 0.2;

    public HashMap() {
        buckets = new ArrayList[bucketNumber];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry<K, V>>();
        }
    }

    public void put(K key, V value) {
        ArrayList<Entry<K, V>> bucketList = getBucket(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        for (int i = 0; i < bucketList.size(); i++) {
            if (bucketList.get(i).getKey().equals(newEntry.getKey())) {
                bucketList.set(i, newEntry);
                return;
            }
        }
        bucketList.add(newEntry);
        size++;
        widening();
    }

    public void putAll(HashMap<K, V> hashMap) {
        ArrayList<K> keys = hashMap.keys();
        for(K key : keys) {
            put(key, hashMap.get(key));
        }
    }

    public void putIfAbsent(K key, V value) {
        ArrayList<Entry<K, V>> bucketList = getBucket(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        for (Entry<K, V> entry : bucketList) {
            if (entry.getKey().equals(newEntry.getKey())) {
                return;
            }
        }
        bucketList.add(newEntry);
        size++;
        widening();
    }

    public V get(K key) {
        ArrayList<Entry<K, V>> bucketList = getBucket(key);
        for (Entry<K, V> entry : bucketList) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        ArrayList<Entry<K, V>> bucketList = getBucket(key);
        for (Entry<K, V> entry : bucketList) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (ArrayList<Entry<K, V>> arrayList : buckets) {
            for (Entry<K, V> entry : arrayList) {
                if (value == null) {
                    if (entry.value == null) {
                        return true;
                    }
                } else {
                    if (value.equals(entry.value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public V remove(K key) {
        ArrayList<Entry<K, V>> bucketList = getBucket(key);
        getBucket(key);
        for (int i = 0; i < bucketList.size(); i++) {
            if (bucketList.get(i).getKey().equals(key)) {
                V value = bucketList.get(i).getValue();
                bucketList.remove(i);
                size--;
                return value;
            }
        }
        throw new IllegalArgumentException("There is no " + key + " key");
    }

    public ArrayList<K> keys() {
        ArrayList<K> keyList = new ArrayList<K>();
        for (ArrayList<Entry<K, V>> arrayList : buckets) {
            for (Entry<K, V> entry : arrayList) {
                keyList.add(entry.getKey());
            }
        }
        return keyList;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        for (ArrayList<Entry<K, V>> arrayList : buckets) {
            for (Entry<K, V> entry : arrayList) {
                stringBuilder.append(entry);
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    private ArrayList<Entry<K, V>> getBucket(K key) {
        int bucket = key.hashCode() % bucketNumber; //buckets.length
        return buckets[bucket];
    }

    private void widening() {
        if (size/(double)bucketNumber > CAPACITY && bucketNumber < Integer.MAX_VALUE) {
            int newBucketNumber = Math.min(Integer.MAX_VALUE, bucketNumber * 3 / 2);
            ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[newBucketNumber];
            for (int i = 0; i < newBuckets.length; i++) {
                newBuckets[i] = new ArrayList<Entry<K, V>>();
            }
            for (ArrayList<Entry<K, V>> arrayList : buckets) {
                for (Entry<K, V> entry : arrayList) {
                    int bucket = entry.key.hashCode() % newBucketNumber;
                    ArrayList<Entry<K, V>> bucketList = newBuckets[bucket];
                    bucketList.add(entry);
                }
            }
            bucketNumber = newBucketNumber;
            buckets = newBuckets;
        }
    }
}