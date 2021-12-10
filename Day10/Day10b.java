package Day10;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10b {

    List<String> allLines;
    ArrayList<String> incompleteLines = new ArrayList<String>();
    String[] corruptStrings = new String[] {"{>", "{)", "{]", "<}", "<)", "<]", "(}", "(>", "(]", "[}", "[>", "[)"};
    boolean shouldIContinue = true;
    int points = 0;
    ArrayList<BigInteger> scores = new ArrayList<BigInteger>();

        public static void main(String[] args) {
        new Day10b();
    }


    Day10b() {
        readfile("input.txt");

        for (int i = 0; i < allLines.size(); i++) {
            System.out.println("Starting line " + i);
            shouldIContinue = true;
            parseLine(allLines.get(i));
        }
        for (int i =0;i<incompleteLines.size();i++)
        {
            incompleteLines.set(i,reverseString(incompleteLines.get(i).replaceAll("\\{","}").replaceAll("<",">").replaceAll("\\[","]").replaceAll("\\(",")")));
            System.out.print(incompleteLines.get(i));
            scores.add(calculateScore(incompleteLines.get(i)));
            System.out.println(scores.get(i));
        }
        Collections.sort(scores);
        System.out.println("MIDDLE SCORE: " + scores.get(scores.size()/2));
        //System.out.println("POINTS: " + points);
    }

    private BigInteger calculateScore(String line) {
        BigInteger score = BigInteger.ZERO;
        for (int i =0;i<line.length();i++)
        {
            score= score.multiply(BigInteger.valueOf(5));
            if (line.substring(i,i+1).equals(")"))
            {
                score = score.add(BigInteger.valueOf(1));
            }
            if (line.substring(i,i+1).equals("]"))
            {
                score = score.add(BigInteger.valueOf(2));
            }
            if (line.substring(i,i+1).equals("}"))
            {
                score = score.add(BigInteger.valueOf(3));
            }
            if (line.substring(i,i+1).equals(">"))
            {
                score = score.add(BigInteger.valueOf(4));
            }
        }
        return score;
    }

    private void parseLine(String line) {
        whileloop:
        while (line.length() > 1 && shouldIContinue) {
            int currentLength = line.length();
            line = line.replaceAll("\\(\\)", "");
            line = line.replaceAll("<>", "");
            line = line.replaceAll("\\{}", "");
            line = line.replaceAll("\\[]", "");
       //     System.out.println(line);
            if (line.length() == currentLength) // nothing was replaced
            {
                // check for a corrupted string
                for (int i = 0;i<corruptStrings.length;i++)
                {
                    if (line.contains(corruptStrings[i]))
                    {
                   //     System.out.println("CORRUPTED STRING: " + line);
                        if (corruptStrings[i].substring(1,2).equals(")"))
                        {
                            points+=3;
                        }
                        if (corruptStrings[i].substring(1,2).equals("]"))
                        {
                            points+=57;
                        }
                        if (corruptStrings[i].substring(1,2).equals("}"))
                        {
                            points+=1197;
                        }
                        if (corruptStrings[i].substring(1,2).equals(">"))
                        {
                            points+=25137;
                        }
                        shouldIContinue = false;
                        break whileloop;
                    }
                }
                //if it's not corrupted, it just not finished
                System.out.println("Nothing to replace anymore. " + line);
                incompleteLines.add(line);
                shouldIContinue = false;
                break whileloop;
            } else {
                parseLine(line);
            }
        }
    }

    private String reverseString(String input)
    {
        StringBuilder input1 = new StringBuilder();
        input1.append(input);
        input1.reverse();
        return input1.toString();
    }


    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day10\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
