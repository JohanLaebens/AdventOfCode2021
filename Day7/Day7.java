package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Day7 {

    List<String> allLines;
    Integer[] list;
    public static void main(String[] args) {
        new Day7();
    }




    Day7() {
        readfile("input.txt");
        String input = allLines.get(0);
        String[] listOfInputs = input.split(",");

        list = new Integer[listOfInputs.length];


        int usedFuel = 0;
        int lowestFuelCost = Integer.MAX_VALUE;
        int upperlimit = Integer.MIN_VALUE;
        int lowerlimit = Integer.MAX_VALUE;


        for (int i=0;i<listOfInputs.length;i++)
        {
            list[i] = Integer.parseInt(listOfInputs[i]);
        }

        //find biggest and smallest position
        for (int i=0;i<list.length;i++)
        {
            if (list[i]< lowerlimit)
            {
                lowerlimit = list[i];
            }
            if (list[i] > upperlimit)
            {
                upperlimit = list[i];
            }
        }


        //go over all positions and add all fuel
        for (int i=lowerlimit;i<upperlimit;i++)
        {
            usedFuel = 0;
            //count fuelcost
            for (int j = 0;j<list.length;j++)
            {
                usedFuel+= Math.abs(i-list[j]);
            }
            if (usedFuel < lowestFuelCost)
            {
                lowestFuelCost = usedFuel;
            }
        }

        System.out.println("lowest fuelcost: "+ lowestFuelCost);



    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day7\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
