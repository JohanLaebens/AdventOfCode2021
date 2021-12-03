package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2b {

    List<String> allLines;


    public static void main(String[] args) {
        new Day2b();
    }


    Day2b() {
        readfile("input.txt");

        int horizontalPos = 0;
        int horizontalDepth = 0;
        int aim = 0;

        for (int i = 0;i<allLines.size();i++)
        {
            String instruction = allLines.get(i).substring(0,allLines.get(i).indexOf(" "));
            int index = allLines.get(i).indexOf(" ")+1;
            int value = Integer.parseInt(allLines.get(i).substring(index));

            switch (instruction) {
                case "forward":
                    horizontalDepth += value * aim;
                    horizontalPos += value;
                    break;
                case "down":
                    aim += value;
                    break;
                case "up":
                    aim -= value;
                    break;
            }
        }

        System.out.println("horizontalDepth: " + horizontalDepth);
        System.out.println("horizontalPos: " + horizontalPos);
        System.out.println("Result: " + horizontalPos * horizontalDepth);


    }



    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day2\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
