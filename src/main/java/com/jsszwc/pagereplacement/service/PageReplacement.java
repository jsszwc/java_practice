package com.jsszwc.pagereplacement.service;

import java.util.List;
import java.util.Map;

public interface PageReplacement<K, V>{
    /**
     * 添加/更新元素
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * 得到指定key对应的值，不存在返回null
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 队列中是否存在指定的key
     * @param key
     * @return
     */
    boolean containsKey(K key);

    /**
     * 打印整个队列
     */
    void showAll();
}
