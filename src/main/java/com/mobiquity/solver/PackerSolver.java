package com.mobiquity.solver;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;

public interface PackerSolver {

    void init(Package pkg) throws APIException;

    Result solve();
}
