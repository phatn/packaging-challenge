package com.mobiquity.solver;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Result {

    private int maxCost;

    private List<Integer> selectedItems;

}
