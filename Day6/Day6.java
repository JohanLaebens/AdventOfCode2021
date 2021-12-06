package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6 {

    List<String> allLines;

    public static void main(String[] args) {
        new Day6();
    }


    Day6() {
        readfile("input.txt");
        String fishString = allLines.get(0);
        String tempFish = "";
        String currentFish = "";

        int numberOfDays = 30;

        for (int i=1;i<=numberOfDays;i++)
        {
            tempFish = "";
            while (fishString.length() > 0)
            {
                currentFish = fishString.substring(0,fishString.indexOf(","));
                fishString = fishString.substring(2); //always a max length of 2
                if (currentFish.equals("0"))
                {
                    tempFish = tempFish + "6,8"; // reset of current fish + add new fish
                    tempFish = tempFish + ",";
                }
                else {
                    currentFish = (Integer.parseInt(currentFish) -1) + "";
                    tempFish = tempFish + currentFish + ",";
                }
            }
            fishString = tempFish;
            System.out.println("DAY " + i + " "+ fishString);
        }
        countNumberOfFish(fishString);
    }

    private void countNumberOfFish(String fishString) {
        String[] numberofFish = fishString.split(",");
        System.out.println("NumberOfFish: " + numberofFish.length);
    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day6\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
