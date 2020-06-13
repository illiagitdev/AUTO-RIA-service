package com.illia.riasurfing.entities;

public class ResponseMapper<K, V> {
    private K key;
    private V value;

    public ResponseMapper() {
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
