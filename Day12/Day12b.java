package Day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day12b {

    List<String> allLines;
    ArrayList<String> starts = new ArrayList<String>();

    ArrayList<String> possibilities = new ArrayList<String>();

    ArrayList<String> smallCaves = new ArrayList<String>();

    ArrayList<String> solution = new ArrayList<String>();

    public static void main(String[] args) {
        new Day12b();
    }


    Day12b() {
        readfile("input.txt");
        createSets();
        for (int i = 0; i < smallCaves.size(); i++) {
            System.out.println(smallCaves.get(i));
            //countSmallCaveVisited = 0;
            createPossibilities(smallCaves.get(i));
    }

        // Print all solutions
//        for (int i = 0; i < solution.size(); i++) {
//        //    System.out.println(solution.get(i));
//        }
        // Print out count:
        System.out.println(solution.size());
    }



    private void createPossibilities(String smallCaveWhichCanBeVisitedTwice) {

        for (int i = 0; i < starts.size(); i++) {
            ArrayList<String> tempPossibilities = new ArrayList<String>(possibilities);
            String[] split = starts.get(i).split("-");
            int countSmallCaveVisited = 0;
            if (isSmallCave(split[1])) {
                if (split[1].equals(smallCaveWhichCanBeVisitedTwice))
                {
                        countSmallCaveVisited=1;
                }
                else
                {
                    tempPossibilities = removeFromListOfMiddles(split[1], tempPossibilities);
                }
            }
            findSomethingToAdd(starts.get(i), tempPossibilities, smallCaveWhichCanBeVisitedTwice,countSmallCaveVisited);
        }
    }

    private void findSomethingToAdd(String current, ArrayList<String> possibilities, String smallCaveWhichCanBeVisitedTwice, int countSmallCaveVisited) {

        ArrayList<String> options = new ArrayList<String>();

        for (int i = 0; i < possibilities.size(); i++) {
            String[] cavesplit = current.split("-");
            String[] split = possibilities.get(i).split("-");

            if (split[0].equals(cavesplit[cavesplit.length - 1])) {
                options.add(current + "-" + split[1]);
                   //   System.out.println("-- OPTIONS: "+ current + "-" + split[1]);
            }
        }
        for (int i = 0; i < options.size(); i++) {
            String[] split = options.get(i).split("-");
            ArrayList<String> tempPossibilities = new ArrayList<>(possibilities);

            if (isSmallCave(split[split.length - 1])) {
                if (split[split.length -1].equals(smallCaveWhichCanBeVisitedTwice))
                {
                    if (countSmallCaveVisited >= 5) {
                        tempPossibilities = removeFromListOfMiddles(split[split.length - 1], tempPossibilities);
                    }
                    else
                    {
                        countSmallCaveVisited++;
                    }
                }
                else
                {
                    tempPossibilities = removeFromListOfMiddles(split[split.length - 1], tempPossibilities);
                }

            }
            if (split[split.length - 1].contains("END")) {
                // check of er toch geen 3 inzitten
                int countSmallCaveWhichCanBeVisitedTwice =0;
                for (int z=0;z<split.length;z++)
                {
                    if (split[z].equals(smallCaveWhichCanBeVisitedTwice))
                    {
                        countSmallCaveWhichCanBeVisitedTwice++;
                    }
                }
                if ((countSmallCaveWhichCanBeVisitedTwice <= 2)) {

                    //  System.out.println(options.get(i));
                    // add to solutions if not already there
                    if (!solution.contains(options.get(i))) {
                 //      System.out.println(options.get(i));
                        solution.add(options.get(i));
                    }
              }
                boolean found = false;
//                for (int j = 0; j < solution.size(); j++) {
//                    if (solution.get(j).equals(options.get(i))) {
//                        found = true;
//                    }
//                }
//                if (!found) {
//                    solution.add(options.get(i));
//                }
            } else {
                findSomethingToAdd(options.get(i), tempPossibilities, smallCaveWhichCanBeVisitedTwice, countSmallCaveVisited);
            }
        }

        return;
    }

    private ArrayList<String> removeFromListOfMiddles(String s, ArrayList<String> tempPossibilities) {
        ArrayList<String> tmp = new ArrayList<String>();
        for (int i = 0; i < tempPossibilities.size(); i++) {
       //     System.out.print(s+ " POSSIBILITY: "+ tempPossibilities.get(i));
            String[] split = tempPossibilities.get(i).split("-");
            if (!split[1].equals(s)) {
                tmp.add(tempPossibilities.get(i));
        //        System.out.println(" and added!");
            }
            else
            {
        //        System.out.println(" and NOT added!");
            }
        }
        return tmp;
    }


    private void createSets() {
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).contains("start")) {
                allLines.set(i, allLines.get(i).replaceAll("start", "START"));
                String[] split = allLines.get(i).split("-");
                if (!split[0].equals("START")) {
                    starts.add(split[1] + "-" + split[0]);
                    addToSmallCaves(split[0]);
                } else {
                    starts.add(allLines.get(i));
                    addToSmallCaves(split[1]);
                }
            } else if (allLines.get(i).contains("end")) {
                allLines.set(i, allLines.get(i).replaceAll("end", "END"));
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
