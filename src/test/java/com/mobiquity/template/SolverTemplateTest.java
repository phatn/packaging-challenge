package com.mobiquity.template;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.DynamicProgrammingSolver;
import com.mobiquity.solver.Result;
import com.mobiquity.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SolverTemplateTest {

    @Test
    public void test1_should_return_3_result() throws Exception {
        Result result1 = Result.builder().maxCost(76).selectedItems(Arrays.asList(4)).build();
        Result result2 = Result.builder().maxCost(148).selectedItems(Arrays.asList(2, 7)).build();
        Result result3 = Result.builder().maxCost(143).selectedItems(Arrays.asList(6, 9)).build();

        SolverTemplate solverTemplate = new SolverTemplateImpl(new DynamicProgrammingSolver());

        List<Result> results = solverTemplate.solve(TestUtils.getFilePathFromResource("test1_Return_3_Result_input"));
        assertThat(results).hasSize(3).contains(result1, result2, result3);
    }

    @Test
    public void test2_should_return_4_result() throws Exception {
        Result result1 = Result.builder().maxCost(76).selectedItems(Arrays.asList(4)).build();
        Result result2 = Result.builder().maxCost(148).selectedItems(Arrays.asList(2, 7)).build();
        Result result3 = Result.builder().maxCost(143).selectedItems(Arrays.asList(6, 9)).build();
        Result result4 = Result.builder().maxCost(0).selectedItems(Collections.EMPTY_LIST).build();

        SolverTemplate solverTemplate = new SolverTemplateImpl(new DynamicProgrammingSolver());

        List<Result> results = solverTemplate.solve(TestUtils.getFilePathFromResource("test2_Return_4_Result_input"));

        assertThat(results).hasSize(4).contains(result1, result2, result3, result4);
    }


    @Test
    public void test3_should_throw_APIException() throws Exception {

        SolverTemplate solverTemplate = new SolverTemplateImpl(new DynamicProgrammingSolver());

        assertThatThrownBy(() -> solverTemplate.solve(TestUtils.getFilePathFromResource("test3_Throw_APIException_input"))).isInstanceOf(APIException.class)
                .hasMessageContaining("Capacity of a package must be lower than or equals to 100");
    }

    @Test
    public void test4_should_throw_APIException() throws Exception {

        SolverTemplate solverTemplate = new SolverTemplateImpl(new DynamicProgrammingSolver());

        assertThatThrownBy(() -> {
            solverTemplate.solve(TestUtils.getFilePathFromResource("test4_Throw_APIException_input"));
        }).isInstanceOf(APIException.class)
                .hasMessageContaining("Cost of an item must be lower than or equals to 100");
    }
}
