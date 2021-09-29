package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.DynamicProgrammingSolver;
import com.mobiquity.template.SolverTemplate;
import com.mobiquity.template.SolverTemplateImpl;
import com.mobiquity.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Packer {

    private Packer() {
    }

    /**
     * Default using DynamicProgrammingSolver to solve the problem.
     *
     * The algorithm could be injected by setter method e.g solverTemplate.setPackerSolver(new BruteForceSolver());
     *
     * @param filePath
     * @return a String with below example format
     * 4
     * -
     * 4
     * 2,7
     * 6,9
     *
     * @throws APIException
     */
    public static String pack(String filePath) throws APIException {

        // Init SolverTemplate and inject DynamicProgrammingSolver
        SolverTemplate solverTemplate = new SolverTemplateImpl(new DynamicProgrammingSolver());

        log.info("Solving the problem using DynamicProgrammingSolver");
        return Utils.generateOutput(solverTemplate.solve(filePath));
    }

}
