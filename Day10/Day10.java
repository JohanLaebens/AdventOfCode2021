package Day10;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day10 {

    List<String> allLines;
    String[] corruptStrings = new String[] {"{>", "{)", "{]", "<}", "<)", "<]", "(}", "(>", "(]", "[}", "[>", "[)"};
    boolean shouldIContinue = true;
    int points = 0;

        public static void main(String[] args) {
        new Day10();
    }


    Day10() {
        readfile("input.txt");

        for (int i = 0; i < allLines.size(); i++) {
            System.out.println("Starting line " + i);
            shouldIContinue = true;
            parseLine(allLines.get(i));
        }
        System.out.println("POINTS: " + points);
    }

    private void parseLine(String line) {
        whileloop:
        while (line.length() > 1 && shouldIContinue) {
            int currentLength = line.length();
            line = line.replaceAll("\\(\\)", "");
            line = line.replaceAll("<>", "");
            line = line.replaceAll("\\{}", "");
            line = line.replaceAll("\\[]", "");
            System.out.println(line);
            if (line.length() == currentLength) // nothing was replaced
            {
                // check for a corrupted string
                for (int i = 0;i<corruptStrings.length;i++)
                {
                    if (line.contains(corruptStrings[i]))
                    {
                        System.out.println("CORRUPTED STRING: " + line);
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
                shouldIContinue = false;
                break whileloop;
            } else {
                parseLine(line);
            }
        }
    }

    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day10\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
