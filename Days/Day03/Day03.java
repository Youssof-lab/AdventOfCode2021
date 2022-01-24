package Day03;

import java.io.*;
import java.util.ArrayList;

/**
 * Advent of Code 2021 - Day 3: Binary Diagnostic
 * https://adventofcode.com/2021/day/3
 */

public class Day03 {

    public static void main (String[] args) throws IOException {

        ArrayList<String> diagnosticsReport = getDiagnosticReport("src/Day3/input.txt");
        System.out.println(solvePartOne(diagnosticsReport));
        System.out.println(solvePartTwo(diagnosticsReport));
    }

    public static int solvePartOne(ArrayList<String> report) {

        int[] oneBitCounts = new int[report.get(0).length()];   // Store counts of '1' at each bit position
        StringBuilder gammaRateBits = new StringBuilder();      // Binary representation of Gamma Rate
        StringBuilder epsilonRateBits = new StringBuilder();    // Binary representation of Epsilon Rate
        int gammaRateDecimal;                                   // Decimal representation of Gamma Rate
        int epsilonRateDecimal;                                 // Decimal representation of Epsilon Rate

        // Determine the number of '1' bits at each position
        for (String bitSequence : report) {
            String[] bits = bitSequence.split("");
            for (int i = 0; i < bits.length - 1; i++) {
                oneBitCounts[i] += Integer.parseInt(bits[i]);
            }
        }

        // Build the gamma and epsilon rate binary strings based on the criteria
        for (int oneCount : oneBitCounts) {
            int zeroCount = report.size() - oneCount;
            gammaRateBits.append((oneCount > zeroCount) ? "1" : "0");
            epsilonRateBits.append((oneCount < zeroCount) ? "1" : "0");
        }

        // Convert binary to decimal representation
        gammaRateDecimal = Integer.parseInt(String.valueOf(gammaRateBits), 2);
        epsilonRateDecimal = Integer.parseInt(String.valueOf(epsilonRateBits), 2);

        return gammaRateDecimal * epsilonRateDecimal;
    }

    public static int solvePartTwo(ArrayList<String> report) {

        String oxygenRatingBits = getRating(report, "oxygen", 0);   // Binary representation of Oxygen Rate
        String co2RatingBits = getRating(report, "co2", 0);         // Binary representation of CO2 Rate
        int oxygenRatingDecimal;                                                // Decimal representation of Oxygen Rate
        int co2RatingDecimal;                                                   // Decimal representation of CO2 Rate

        // Convert binary to decimal representation
        oxygenRatingDecimal = Integer.parseInt(String.valueOf(oxygenRatingBits), 2);
        co2RatingDecimal = Integer.parseInt(String.valueOf(co2RatingBits), 2);

        return oxygenRatingDecimal * co2RatingDecimal;
    }

    // This recursive method searches for the specified rating value (Oxygen or CO2)
    // The base case occurs when the current report has only 1 value left
    // Otherwise, the bit criteria specified in the question is followed to filter out unwanted bit sequences
    public static String getRating(ArrayList<String> report, String priority, int pos) {

        int oneCount = 0;
        int zeroCount;
        ArrayList<String> currReport = new ArrayList<>(report); // Copy the elements from the report into a temp report

        // Base case
        if (currReport.size() == 1) {
            return currReport.get(0);
        }

        // Count number of 1's at the current position
        for (String bitSequence : currReport) {
            oneCount += Integer.parseInt(String.valueOf(bitSequence.charAt(pos)));
        }

        zeroCount = currReport.size() - oneCount;

        // Use the Bit Criteria from the problem to determine which values to remove
        if (priority.equalsIgnoreCase("oxygen")) {
            if (oneCount >= zeroCount) {
                currReport.removeIf(value -> value.charAt(pos) == '0');
            } else {
                currReport.removeIf(value -> value.charAt(pos) == '1');
            }
        } else {
            if (zeroCount <= oneCount) {
                currReport.removeIf(value -> value.charAt(pos) == '1');
            } else {
                currReport.removeIf(value -> value.charAt(pos) == '0');
            }
        }

        // Perform the same steps on the next position until the base case is reached
        return getRating(currReport, priority, pos + 1);
    }

    public static ArrayList<String> getDiagnosticReport(String courseFile) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(courseFile));
        ArrayList<String> report = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            report.add(line);
        }

        return report;
    }
}
