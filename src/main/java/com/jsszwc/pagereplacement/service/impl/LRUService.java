package com.jsszwc.pagereplacement.service.impl;

import com.jsszwc.pagereplacement.service.PageReplacement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUService <K, V> implements PageReplacement<K, V> {
    private LinkedList<K> linkedList = new LinkedList<>();
    private Map<K, V> map = new HashMap<>();
    private int cacheSize;

    public LRUService(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void put(K key, V value) {
        if(containsKey(key)) {
            linkedList.remove(key);
            map.remove(key);
        }

        linkedList.add(key);
        map.put(key, value);
        if(linkedList.size() > cacheSize) {
            map.remove(linkedList.get(0));
            linkedList.remove(0);
        }
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public void showAll() {
        for(int i = linkedList.size()-1; i >= 0; --i) {
            System.out.println(linkedList.get(i) + " -> " + map.get(linkedList.get(i)));
        }
    }
}
