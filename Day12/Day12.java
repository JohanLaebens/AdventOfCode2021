package Day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

    List<String> allLines;
    ArrayList<String> starts = new ArrayList<String>();

    ArrayList<String> possibilities = new ArrayList<String>();

    ArrayList<String> smallCaves = new ArrayList<String>();

    ArrayList<String> solution = new ArrayList<String>();
    public static void main(String[] args) {
        new Day12();
    }


    Day12() {
        readfile("input.txt");
        createSets();
        createPossibilities();
    }

    private void createPossibilities() {

        for (int i = 0; i < starts.size(); i++) {
            ArrayList<String> tempPossibilities = new ArrayList<String>(possibilities);
            String[] split = starts.get(i).split("-");
            if (isSmallCave(split[1])) {
                tempPossibilities = removeFromListOfMiddles(split[1], tempPossibilities);
            }
            findSomethingToAdd(starts.get(i), tempPossibilities);
        }

        // Print all solutions
        for (int i = 0;i< solution.size();i++)
        {
            System.out.println(solution.get(i));
        }
        // Print out count:
        System.out.println(solution.size());
    }

    private void findSomethingToAdd(String current, ArrayList<String> possibilities) {

        ArrayList<String> options = new ArrayList<String>();

        for (int i = 0; i < possibilities.size(); i++) {
            String[] cavesplit = current.split("-");
            String[] split = possibilities.get(i).split("-");

            if (split[0].equals(cavesplit[cavesplit.length - 1])) {
                options.add(current + "-" + split[1]);
          //      System.out.println(current + "-" + split[1]);
            }
        }
        for (int i = 0; i < options.size(); i++) {
            String[] split = options.get(i).split("-");
            ArrayList<String> tempPossibilities = new ArrayList<>(possibilities);

            if (isSmallCave(split[split.length-1])) {
                tempPossibilities = removeFromListOfMiddles(split[split.length-1], tempPossibilities);
            }
            if (split[split.length-1].contains("END")) {
              //  System.out.println(options.get(i));
                // add to solutions if not already there
                boolean found = false;
                for (int j=0;j<solution.size();j++)
                {
                    if (solution.get(j).equals(options.get(i)))
                    {found = true;}
                }
                if (!found)
                {
                    solution.add(options.get(i));
                }
             //   break;
            } else {
                findSomethingToAdd(options.get(i), tempPossibilities);
            }
        }

        return;
    }

    private ArrayList<String> removeFromListOfMiddles(String s, ArrayList<String> tempPossibilities) {
        ArrayList<String> tmp = new ArrayList<String>();
        for (int i = 0; i < tempPossibilities.size(); i++) {
            String[] split = tempPossibilities.get(i).split("-");
            if (!split[1].equals(s)) {
               tmp.add(tempPossibilities.get(i));
            }
        }
        return tmp;
    }


    private void createSets() {
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).contains("start")) {
                allLines.set(i,allLines.get(i).replaceAll("start","START"));
                String[] split = allLines.get(i).split("-");
                if (!split[0].equals("START")) {
                    starts.add(split[1] + "-" + split[0]);
                    addToSmallCaves(split[0]);
                } else {
                    starts.add(allLines.get(i));
                    addToSmallCaves(split[1]);
                }
            } else if (allLines.get(i).contains("end")) {
                allLines.set(i,allLines.get(i).replaceAll("end","END"));
                String[] split = allLines.get(i).split("-");
                if (split[0].equals("END")) {
                    possibilities.add(split[1] + "-" + split[0]);
                    addToSmallCaves(split[0]);
                } else {
                    possibilities.add(allLines.get(i));
                    addToSmallCaves(split[1]);
                }
            } else {
                possibilities.add(allLines.get(i));
                String[] split = allLines.get(i).split("-"); //the reverse is possible too
                possibilities.add(split[1] + "-" + split[0]);
                addToSmallCaves(split[0]);
                addToSmallCaves(split[1]);
            }
        }
    }

    private void addToSmallCaves(String cave) {
        boolean found = false;
        char[] caveArray = cave.toCharArray();
        if (Character.isUpperCase(caveArray[0])) {
            return;
        }
        search:
        for (int i = 0; i < smallCaves.size(); i++) {
            if (smallCaves.get(i).equals(cave)) {
                found = true;
                break search;
            }
        }
        if (!found) {
            smallCaves.add(cave);
        }
        return;
    }

    private boolean isSmallCave(String cave) {
        char[] caveArray = cave.toCharArray();
        if (Character.isUpperCase(caveArray[0])) {
            return false;
        } else {
            return true;
        }
    }

    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day12\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
