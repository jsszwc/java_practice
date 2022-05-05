package com.jsszwc.pagereplacement;

import com.jsszwc.pagereplacement.service.PageReplacement;
import com.jsszwc.pagereplacement.service.impl.LRUService;

import java.sql.SQLOutput;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("--------LRU Test--------");
        PageReplacement<String, String> lruService = new LRUService<>(4);
        lruService.put("1", "1");
        lruService.put("2", "2");
        lruService.put("3", "3");
        lruService.put("4", "4");
        //lruService.showAll();
        lruService.put("3", "3");
        lruService.put("1", "1");
        //lruService.showAll();
        lruService.put("5", "5");
        //lruService.showAll();
        lruService.put("6", "6");
        lruService.showAll();
    }
}
