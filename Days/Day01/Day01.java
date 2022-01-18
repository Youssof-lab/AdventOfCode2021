import java.io.*;
import java.util.ArrayList;

/**
 * Advent of Code 2021 - Day 1: Sonar Sweep
 * https://adventofcode.com/2021/day/1
 */

public class Day01 {

    public static void main (String[] args) throws IOException {
        ArrayList<Integer> depthsReport = getDepthsReport("src/Day1/input.txt");
        System.out.println(solvePartOne(depthsReport));
        System.out.println(solvePartTwo(depthsReport));
    }

    public static int solvePartOne(ArrayList<Integer> depths) {
        int lastDepth = depths.get(0);
        int numIncreases = 0;

        for (Integer currDepth : depths) {
            numIncreases += (currDepth > lastDepth) ? 1 : 0;
            lastDepth = currDepth;
        }

        return numIncreases;
    }

    public static int solvePartTwo(ArrayList<Integer> depths) {
        int lastDepthWindow;
        int currDepthWindow;
        int numIncreases = 0;

        // Make sure there are enough numbers to have two windows
        if (depths.size() > 4) {
            lastDepthWindow = depths.get(0) + depths.get(1) + depths.get(2);

            for (int i = 0; i < depths.size() - 2; i++) {
                if ((i + 2) < depths.size()) {
                    currDepthWindow = depths.get(i) + depths.get(i + 1) + depths.get(i + 2);
                } else {
                    break;
                }

                numIncreases += (currDepthWindow > lastDepthWindow) ? 1 : 0;
                lastDepthWindow = currDepthWindow;
            }
        }

        return numIncreases;
    }

    public static ArrayList<Integer> getDepthsReport(String reportFile) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(reportFile));
        ArrayList<Integer> depths = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            depths.add(Integer.parseInt(line));
        }

        return depths;
    }
}
