package com.mobiquity.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Package {

    private int capacity;

    private List<Item> items;
}
