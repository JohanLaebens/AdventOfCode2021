package Day6;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6b {

    List<String> allLines;

    public static void main(String[] args) {
        new Day6b();
    }


    Day6b() {
        readfile("input.txt");
        String fishString = allLines.get(0);
        String tempFish = "";
        String currentFish = "";
        BigInteger overallCount = BigInteger.ZERO;

        int numberOfDays = 30;
        BigInteger[] days = new BigInteger[257];

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

            String[] temp = fishString.split(",");

            days[i] = BigInteger.valueOf(temp.length);
            System.out.println("DAY " + i + " " + days[i]);
        }

        for (int i = 31; i <= 256; i++) {
            days[i] = days[i - 9].add(days[i - 7]);
            System.out.println("DAY " + i + " " + days[i]);
        }

    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day6\\" + filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
