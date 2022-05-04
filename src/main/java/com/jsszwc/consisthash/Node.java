package com.jsszwc.consisthash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private String ip;
    private String name;
    private int replicasNum;

    @Override
    public String toString() {
        return ip;
    }
}
