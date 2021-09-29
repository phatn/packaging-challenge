package com.mobiquity.template;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.solver.PackerSolver;
import com.mobiquity.solver.Result;

import java.util.List;

/**
 * Solver Template Pattern
 * This class uses PackerSolver as a strategy pattern, i.e can inject different implementations of PackerSolver
 */
public abstract class SolverTemplate {

    public abstract void setPackerSolver(PackerSolver packerSolver);

    public final List<Result> solve(String filePath) throws APIException {
        List<Package> packages = handleInput(filePath);
        return solvePackages(packages);
    }

    protected abstract List<Package> handleInput(String filePath) throws APIException;
    protected abstract List<Result> solvePackages(List<Package> packages) throws APIException;
}
