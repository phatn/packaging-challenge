package com.mobiquity.solver;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DynamicProgrammingSolver extends AbstractPackerSolver {

    private int[][] fillTable() {

        // Number of items
        int itemNum = costs.length;
        int[][] table = new int[itemNum + 1][capacity + 1];
        for(int itemIndex = 1; itemIndex <= itemNum; itemIndex++) {

            // Cost and weight of current item
            int cost = costs[itemIndex - 1];
            int weight = weights[itemIndex - 1];

            for(int w = 1; w <= capacity; w++) {

                // If current item is excluded from the package
                table[itemIndex][w] = table[itemIndex - 1][w];

                // If current item is included in the package, will consider if having more cost?
                if (w >= weight && table[itemIndex - 1][w - weight] + cost > table[itemIndex][w]) {
                    table[itemIndex][w] = table[itemIndex - 1][w - weight] + cost;
                }
            }
        }
        return table;
    }

    @Override
    public Result solve() {
        int[][] table = fillTable();
        int itemNum = costs.length;
        int maxCost = table[itemNum][capacity];

        List<Integer> selectedItems = new ArrayList<>();

        int weight = capacity;
        for(int i = costs.length; i > 0; i--) {
            if(table[i][weight] != table[i - 1][weight]) {
                int index = i - 1;
                selectedItems.add(index + 1);
                weight -= weights[index];
            }
        }
        Collections.sort(selectedItems);
        return Result.builder()
                .maxCost(maxCost)
                .selectedItems(selectedItems)
                .build();
    }

}
