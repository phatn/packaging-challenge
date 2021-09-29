package com.mobiquity.utils;

import com.mobiquity.solver.Result;

import java.util.List;

public abstract class Utils {

    public static final String NEW_LINE = System.lineSeparator();

    /**
     * Generate a string result as below format
     *
     * 4
     * -
     * 4
     * 2,7
     * 6,9
     *
     * @param results
     * @return
     */
    public static String generateOutput(List<Result> results) {
        StringBuilder output = new StringBuilder().append(results.size()).append(NEW_LINE).append("-").append(NEW_LINE);
        for(Result result : results) {
            StringBuilder line = new StringBuilder();
            List<Integer> selectedItems = result.getSelectedItems();
            if(!selectedItems.isEmpty()) {
                for (int i = 0; i < selectedItems.size(); i++) {
                    line.append(selectedItems.get(i));
                    if(i < selectedItems.size() - 1) {
                        line.append(",");
                    }
                }
                output.append(line).append(NEW_LINE);
            }
        }
        return output.substring(0, output.length() - 1);
    }
}
