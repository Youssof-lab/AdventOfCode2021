package Day02;

import java.io.*;
import java.util.ArrayList;

/**
 * Advent of Code 2021 - Day 2: Dive!
 * https://adventofcode.com/2021/day/2
 */

public class Day02 {

    public static void main (String[] args) throws IOException {
        ArrayList<String> course = getPlannedCourse("src/Day2/input.txt");
        System.out.println(solvePartOne(course));
        System.out.println(solvePartTwo(course));
    }

    public static int solvePartOne(ArrayList<String> instructions) {
        int horizontalPos = 0;
        int depth = 0;
        int units;
        String command;

        // Split each instruction into the command and number of units
        for (String instruction : instructions) {
            String[] components = instruction.split(" ");
            command = components[0];
            units = Integer.parseInt(components[1]);

            if (command.equalsIgnoreCase("forward")) {
                horizontalPos += units;
            } else if (command.equalsIgnoreCase("down")) {
                depth += units;
            } else if (command.equalsIgnoreCase("up")) {
                depth -= units;
            }
        }

        return horizontalPos * depth;
    }

    public static int solvePartTwo(ArrayList<String> instructions) {
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;
        int units;
        String command;

        // Split each instruction into the command and number of units
        for (String instruction : instructions) {
            String[] components = instruction.split(" ");
            command = components[0];
            units = Integer.parseInt(components[1]);

            if (command.equalsIgnoreCase("forward")) {
                horizontalPos += units;
                depth += (aim * units);
            } else if (command.equalsIgnoreCase("down")) {
                aim += units;
            } else if (command.equalsIgnoreCase("up")) {
                aim -= units;
            }
        }

        return horizontalPos * depth;
    }

    public static ArrayList<String> getPlannedCourse(String courseFile) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(courseFile));
        ArrayList<String> instructions = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            instructions.add(line);
        }

        return instructions;
    }
}
