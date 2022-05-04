package com.jsszwc.consisthash;

import com.jsszwc.consisthash.service.impl.MurMruHashService;

import java.util.*;

public class TestConsistHash {

    private static final String IP_PREFIX = "127.0.0.";

    public static void main(String[] args) {
        Map<String, Integer> counts = new HashMap<>();

        List<Node> nodes = new ArrayList<>();
        //构造10个节点，每个节点映射为500个虚拟节点，实现节点负载均衡。也可以根据节点的承载能力，不同节点映射成不同个数的虚拟节点，实现根据节点承载能力负载均衡的效果
        for(int i = 0; i < 10; ++i) {
            Node node = new Node();
            node.setIp(IP_PREFIX + i);
            node.setName(IP_PREFIX + i);
            node.setReplicasNum(500);
            nodes.add(node);
            counts.put(node.toString(), 0);
        }

        ConsistHash consistHash = new ConsistHash(new MurMruHashService(), nodes);

        //5000个请求，均衡落到10个节点上
        for(int i = 0; i < 5000; ++i) {
            String key = UUID.randomUUID().toString();
            Node node = consistHash.get(key);
            counts.put(node.toString(), counts.get(node.toString())+1);
        }

        for(Map.Entry<String, Integer> entry: counts.entrySet()) {
            System.out.println(entry.getKey() + " 记录个数: " + entry.getValue());
        }
    }
}
