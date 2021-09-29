package com.mobiquity.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteForceSolver extends AbstractPackerSolver {

    @Override
    public Result solve() {
        int len = costs.length;
        boolean[] visitedItems = new boolean[len];

        int maxCost = solve(costs, weights, capacity, len, visitedItems);
        List<Integer> selectedItems = new ArrayList<>();
        for(int i = 0; i < visitedItems.length; i++) {
            if(visitedItems[i]) {
                selectedItems.add(i + 1);
            }
        }
        Collections.sort(selectedItems);
        Result result = Result.builder()
                .maxCost(maxCost)
                .selectedItems(selectedItems)
                .build();
        return result;
    }

    private int solve(int[] costs, int[] weights, int capacity, int N, boolean[] visitedItems) {

        if(capacity == 0 || N == 0) {
            return 0;
        }

        if(weights[N - 1] > capacity) {
            return solve(costs, weights, capacity, N-  1, visitedItems);
        } else {
            boolean visited1[] = new boolean[visitedItems.length];
            boolean visited2[] = new boolean[visitedItems.length];

            System.arraycopy(visitedItems, 0, visited1, 0, visited1.length);
            System.arraycopy(visitedItems, 0, visited2, 0, visited2.length);

            visited1[N-1] = true;

            int included = costs[N-1] + solve(costs, weights, capacity - weights[N-1], N - 1, visited1);
            int excluded = solve(costs, weights, capacity, N - 1, visited2);

            if( included > excluded ){
                System.arraycopy(visited1, 0, visitedItems, 0, visited1.length);
                return included;
            } else{
                System.arraycopy(visited2, 0, visitedItems, 0, visited2.length);
                return excluded;
            }
        }
    }
}
