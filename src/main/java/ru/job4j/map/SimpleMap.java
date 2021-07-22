package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity;
    private int count;
    private int modCount;
    private MapEntry<K, V>[] table;

    public SimpleMap() {
        capacity = 8;
        table = new MapEntry[capacity];
        count = 0;
        modCount = 0;
    }

    @Override
    public boolean put(K key, V value) {
        expand();
        if (get(key) != null) {
            return false;
        }
        int i = indexOf(key);
        table[i] = new MapEntry<>(key, value);
        modCount++;
        count++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 8);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private int indexOf(K key) {
        int hashResult = key == null ? 0 : hash(key.hashCode());
        return indexFor(hashResult);
    }

    private void expand() {
        if ((float) count / capacity >= LOAD_FACTOR) {
            capacity *= 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> kvMapEntry : table) {
                if (kvMapEntry != null) {
                    int newIndex = indexOf(kvMapEntry.key);
                    newTable[newIndex] = kvMapEntry;
                }
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        int i = indexOf(key);
        if ((table[i] == null) || (!key.equals(table[i].key))) {
            return null;
        }
        return table[i].value;
    }

    @Override
    public boolean remove(K key) {
        int i = indexOf(key);
        boolean result = table[i] != null && key.equals(table[i].key);
        if (result) {
            table[i] = null;
            modCount++;
            count--;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;
            int pos = 0;

            @Override
            public boolean hasNext() {
                while (pos < capacity && table[pos] == null) {
                    pos++;
                }
                return pos < capacity;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[pos++].key;
                }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return key.equals(mapEntry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public int size() {
        return count;
    }

    public int length() {
        return capacity;
    }

}