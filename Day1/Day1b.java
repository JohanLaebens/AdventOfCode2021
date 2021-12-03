package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1b {

    List<String> allLines;


    public static void main(String[] args) {
        new Day1b();
    }


    Day1b() {
        readfile("input.txt");

        int depthHasIncreased = 0;

        for (int i = 0;i<allLines.size()-3;i++)
        {
            int firstMeasurement = Integer.parseInt(allLines.get(i)) + Integer.parseInt(allLines.get(i+1)) + Integer.parseInt(allLines.get(i+2));
            int secondMeasurement = Integer.parseInt(allLines.get(i+1)) + Integer.parseInt(allLines.get(i+2)) + Integer.parseInt(allLines.get(i+3));
            if (secondMeasurement > firstMeasurement)
            {
                depthHasIncreased++;
            }
        }

    System.out.print("HasIncreased: " + depthHasIncreased);


    }



    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day1\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
