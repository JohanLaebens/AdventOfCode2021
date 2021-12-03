package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {

    List<String> allLines;


    public static void main(String[] args) {
        new Day3();
    }


    Day3() {
        readfile("input.txt");
        String gammaRate = "";
        String epsilonRate = "";



        for (int i = 0;i<allLines.get(1).length();i++)
        {
            int count1 = 0;
            int count0 = 0;
            for (int j=0;j<allLines.size();j++)
            {
                if (allLines.get(j).substring(i,i+1).equals("0"))
                {
                    count0++;
                }
                else
                {
                    count1++;
                }
            }
            if (count0 > count1)
            {
                gammaRate = gammaRate + "0";
                epsilonRate = epsilonRate + "1";
            }
            else
            {
                gammaRate = gammaRate + "1";
                epsilonRate = epsilonRate + "0";
            }
        }
        System.out.println("gammaRate: " + gammaRate);
        System.out.println("epsilonRate: " + epsilonRate);

        int gammaRateint = Integer.parseInt(gammaRate,2);
        int epsilonRateint = Integer.parseInt(epsilonRate,2);

        System.out.println("gammaRateint: " + gammaRateint);
        System.out.println("epsilonRateint: " + epsilonRateint);
        System.out.println("Result: " + gammaRateint * epsilonRateint);

    }



    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day3\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
