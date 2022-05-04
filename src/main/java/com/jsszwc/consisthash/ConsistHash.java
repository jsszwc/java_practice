package com.jsszwc.consisthash;

import com.jsszwc.consisthash.service.HashService;

import java.util.*;

public class ConsistHash {
    private final HashService hashService;
    private final SortedMap<Long, Node> circle = new TreeMap<>();

    public ConsistHash(HashService hashService, List<Node> nodes) {
        this.hashService = hashService;
        for(Node node : nodes) {
            add(node);
        }
    }

    private void add(Node node) {
        int replicasNum = node.getReplicasNum();
        for(int i = 0; i < replicasNum; ++i) {
            circle.put(this.hashService.hash(node.toString() + "_" + i), node);
        }
    }

    public void remove(Node node) {
        int replicasNum = node.getReplicasNum();
        for(int i = 0; i < replicasNum; ++i) {
            circle.remove(this.hashService.hash(node.toString() + "_" + i));
        }
    }

    public Node get(String key) {
        if(circle.isEmpty()) {
            return null;
        }

        long hash = hashService.hash(key);

        if(!circle.containsKey(hash)) {
            SortedMap<Long, Node> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
