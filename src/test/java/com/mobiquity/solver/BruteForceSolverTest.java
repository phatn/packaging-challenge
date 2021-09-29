package com.mobiquity.solver;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Package;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BruteForceSolverTest {

    private BruteForceSolver solver = new BruteForceSolver();

    @Test
    public void test1_should_return_cost_12() throws Exception {
        Result expected = Result.builder().maxCost(12).selectedItems(Arrays.asList(2, 3)).build();
        Package pkg = Package.builder()
                .capacity(10)
                .items(Arrays.asList(
                        Item.builder().cost(1).weight(3).build(),
                        Item.builder().cost(4).weight(3).build(),
                        Item.builder().cost(8).weight(5).build(),
                        Item.builder().cost(5).weight(6).build()
                ))
                .build();
        solver.init(pkg);
        assertThat(solver.solve()).isEqualTo(expected);
    }

    @Test
    public void test1_should_return_cost_13() throws Exception {
        Result expected = Result.builder().maxCost(10).selectedItems(Arrays.asList(2, 4, 5)).build();
        Package pkg = Package.builder()
                .capacity(7)
                .items(Arrays.asList(
                        Item.builder().cost(2).weight(3).build(),
                        Item.builder().cost(2).weight(1).build(),
                        Item.builder().cost(4).weight(3).build(),
                        Item.builder().cost(5).weight(4).build(),
                        Item.builder().cost(3).weight(2).build()
                ))
                .build();
        solver.init(pkg);
        assertThat(solver.solve()).isEqualTo(expected);
    }
}
