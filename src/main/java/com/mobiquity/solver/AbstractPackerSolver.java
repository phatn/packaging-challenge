package com.mobiquity.solver;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;

import java.util.List;

public abstract class AbstractPackerSolver implements PackerSolver {

    protected int[] costs;

    protected int[] weights;

    protected int capacity;

    @Override
    public void init(Package pkg) throws APIException {
        this.capacity = hasFractionalWeight(pkg) ? pkg.getCapacity() * 100 : pkg.getCapacity();
        List<Item> items = pkg.getItems();
        int len = items.size();
        this.costs = new int[len];
        this.weights = new int[len];

        for(int i = 0; i < len; i++) {
            Item item = items.get(i);
            costs[i] = item.getCost();
            weights[i] = hasFractionalWeight(pkg) ? (int) item.getWeight() * 100 : (int) item.getWeight();
        }
    }

    private boolean hasFractionalWeight(Package pkg) {
        for(Item item : pkg.getItems()) {
            if(item.getWeight() != (int) item.getWeight()) {
                return true;
            }
        }
        return false;
    }

}
