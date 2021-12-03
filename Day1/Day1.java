package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day1();
    }


    Day1() {
        readfile("input.txt");

        int depthHasIncreased = 0;

        for (int i = 0;i<allLines.size()-1;i++)
        {
            if (Integer.parseInt(allLines.get(i+1)) > Integer.parseInt(allLines.get(i)))
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
