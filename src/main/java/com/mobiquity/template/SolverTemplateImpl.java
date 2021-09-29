package com.mobiquity.template;

import com.mobiquity.utils.Constants;
import com.mobiquity.domain.Item;
import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.solver.PackerSolver;
import com.mobiquity.solver.Result;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SolverTemplateImpl extends SolverTemplate {

    private PackerSolver packerSolver;

    /**
     *
     * Inject packerSolver in constructor
     */
    public SolverTemplateImpl(PackerSolver packerSolver) {
        this.packerSolver = packerSolver;
    }

    /**
     *
     * Inject packerSolver in setter method
     */
    @Override
    public void setPackerSolver(PackerSolver packerSolver) {
        this.packerSolver = packerSolver;
    }

    @Override
    public List<Package> handleInput(String filePath) throws APIException {
        List<Package> packages = new ArrayList<>();
        try {
            List<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset()).collect(Collectors.toList());

            for(String line : lines) {
                String[] arr = line.split(":");
                int capacity = Integer.parseInt(arr[0].trim());
                if(capacity > Constants.MAX_CAPACITY) {
                    log.error("The capacity {} is greater than {}", capacity, Constants.MAX_CAPACITY);
                    throw new APIException("Capacity of a package must be lower than or equals to " + Constants.MAX_CAPACITY);
                }
                String[] arrayItems = arr[1].trim().split("\\s+");
                List<Item> items = new ArrayList<>();
                for(String item : arrayItems) {
                    String[] itemDetails = item.substring(1, item.length() - 1).split(",");
                    int index = Integer.parseInt(itemDetails[0].trim());
                    double weight = Double.parseDouble(itemDetails[1].trim());
                    if(weight > Constants.MAX_WEIGHT) {
                        log.error("The weight {} is greater than {}", weight, Constants.MAX_WEIGHT);
                        throw new APIException("Weight of an item must be lower than or equals to " + Constants.MAX_WEIGHT);
                    }

                    int cost = Integer.parseInt(itemDetails[2].trim().substring(1));
                    if(cost > Constants.MAX_COST) {
                        log.error("The cost {} is greater than {}", cost, Constants.MAX_COST);
                        throw new APIException("Cost of an item must be lower than or equals to " + Constants.MAX_COST);
                    }

                    items.add(Item.builder()
                            .index(index)
                            .weight(weight)
                            .cost(cost)
                            .build());
                }

                if(items.size() > Constants.MAX_ITEM_IN_PACKAGE) {
                    throw new APIException("Number of items in a package must be lower than or equals to " + Constants.MAX_ITEM_IN_PACKAGE);
                }

                packages.add(Package.builder()
                        .capacity(capacity)
                        .items(items)
                        .build());
            }

        } catch (IOException ex) {
            throw new APIException(ex.getMessage());
        }

        return packages;
    }

    @Override
    public List<Result> solvePackages(List<Package> packages) throws APIException {
        List<Result> results = new ArrayList<>();

        for(Package pkg : packages) {

            // Init values for the packerSolver
            packerSolver.init(pkg);

            // Delegate to packerSolver to solve the problem
            Result result = packerSolver.solve();
            results.add(result);
        }

        return results;
    }

}
