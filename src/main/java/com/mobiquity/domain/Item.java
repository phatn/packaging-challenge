package com.mobiquity.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private int index;

    private double weight;

    private int cost;

}
