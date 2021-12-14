package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day14b {

    List<String> templates;
    List<String> rules;
    String[] combinations;
    long[] combinationsCount;

    String template;
    long[] letters = new long[26];

    public static void main(String[] args) {
        new Day14b();
    }


    Day14b() {
        readfile("input");
        template = templates.get(0);

        combinations = new String[rules.size()];
        combinationsCount = new long[rules.size()];
        long[] combinationsCountTMP = new long[rules.size()];

        // create list of options
        for (int i = 0; i < rules.size(); i++) {
            combinations[i] = rules.get(i).substring(0, 2);
            combinationsCount[i] = 0;
        }

        //set initial combinations from template
        for (int z = 0; z < template.length() - 1; z++) {
            String currentCombination = template.substring(z, z + 2);
            searchForCombination:
            for (int i = 0; i < combinations.length; i++) {
                if (combinations[i].equals(currentCombination)) {
                    combinationsCount[i]++;
                    break searchForCombination;
                }
            }
        }

        // show how much I got from each
        printHowMuchOfEach();

        for (int a = 0; a < 40; a++) { // run 10 times
            //Run through combinations & count and ADD the new combinations
            // eg an NN adds an NC and and CN combination
            for (int i = 0; i < combinations.length; i++) {
                if (combinationsCount[i] != 0) // check if the combination is available
                {
                    String addedChar = rules.get(i).substring(rules.get(i).lastIndexOf(" ") + 1);
                    String combination1 = combinations[i].substring(0, 1) + addedChar;
                    String combination2 = addedChar + combinations[i].substring(1, 2);

                    for (int k = 0; k < combinations.length; k++) {
                        if (combinations[k].equals(combination1)) {
                            combinationsCountTMP[k] = combinationsCountTMP[k] + combinationsCount[i];
                        } else if (combinations[k].equals(combination2)) {
                            combinationsCountTMP[k] = combinationsCountTMP[k] + combinationsCount[i];
                        }
                    }
                }
            }
            // overwrite the temp back with to the general list and clear tmp
            for (int i = 0; i < combinationsCountTMP.length; i++) {
                if (combinationsCountTMP[i] != combinationsCount[i])
                {
                    combinationsCount[i] = combinationsCountTMP[i];
                }
                combinationsCountTMP[i] = 0;
            }
          //  printHowMuchOfEach();
        }

        findLeastAndMostCommon();
    }

    private void printHowMuchOfEach() {
        for (int i = 0; i < combinations.length; i++) {
            System.out.println(combinations[i] + " " + combinationsCount[i]);
        }
        System.out.println("");
    }


    private void findLeastAndMostCommon() {
        setUpLetterCount();

        long most = Long.MIN_VALUE;
        long least = Long.MAX_VALUE;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                if (letters[i] > most) {
                    most = letters[i];
                }
                if (letters[i] < least) {
                    least = letters[i];
                }
            }
            System.out.println("MOST: " + getCharForNumber(i) + " " + most);
            System.out.println("LEAST: " + getCharForNumber(i) + " " + least);
        }
        System.out.println(most - least);
    }

    private void setUpLetterCount() {
        String letter = "";
        for (int i=0;i<combinations.length;i++)
        {
            letter = combinations[i].substring(0,1);
            letters[getNumberForChar(letter)] =  letters[getNumberForChar(letter)] + combinationsCount[i];
        }
        // de allerlaatste letter van originele moet er ook nog bij
        letter = template.substring(template.length()-1,template.length());
        letters[getNumberForChar(letter)]++;
    }


    private String getCharForNumber(int i) {
        return String.valueOf((char) (i + 64));
    }

    private int getNumberForChar(String c) {
        char character = c.charAt(0); // This gives the character 'a'
        int ascii = (int) character; // ascii is now 97.
        return (ascii - 64);
    }

    private void readfile(String filename) {
        try {
            templates = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day14\\" + filename + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rules = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day14\\" + filename + "_rules.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
