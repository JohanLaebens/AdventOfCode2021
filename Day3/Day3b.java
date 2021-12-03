package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3b {

    List<String> allLines;
    int count1 = 0;
    int count0 = 0;
    int valueOxygen;
    int valueCO2;


    public static void main(String[] args) {
        new Day3b();
    }


    Day3b() {
        readfile("input.txt");
        String currentLoop = "";

        for (int k = 0; k < 2; k++) {
            if (k == 0) {
                currentLoop = "Oxygen";
            } else {
                currentLoop = "CO2";
            }


            String bitToFind = "";

            List<String> allLinesBackup = new ArrayList<String>();
            List<String> allLinesBackup2 = new ArrayList<String>();

            allLinesBackup.addAll(allLines);

            loop:
            for (int i = 0; i < allLinesBackup.get(0).length(); i++) {
                if (allLinesBackup.size() == 1) {
                    break loop;
                }
                findMinMax(allLinesBackup, i);

                if (currentLoop.equals("Oxygen")) {
                    if (count0 > count1) {
                        bitToFind = "0";
                    } else if (count0 <= count1) {
                        bitToFind = "1";
                    }
                } else {
                    if (count0 <= count1) {
                        bitToFind = "0";
                    } else if (count0 > count1) {
                        bitToFind = "1";
                    }
                }

                for (int j = 0; j < allLinesBackup.size(); j++) {
                    if (allLinesBackup.get(j).substring(i, i + 1).equals(bitToFind)) {
                        allLinesBackup2.add(allLinesBackup.get(j));
                    }
                }

                allLinesBackup.clear();
                allLinesBackup.addAll(allLinesBackup2);
                allLinesBackup2.clear();
                count0 = 0;
                count1 = 0;
            }

            System.out.println(currentLoop + " " + allLinesBackup.get(0));
            int value = Integer.parseInt(allLinesBackup.get(0), 2);
            System.out.println("value: " + value);

            if (currentLoop.equals("Oxygen")) {
                valueOxygen = value;
            } else
                valueCO2 = value;
            System.out.println("End Result: " + valueOxygen * valueCO2);


        }


    }

    private void findMinMax(List<String> lijnen, int pos) {
        count1 = 0;
        count0 = 0;
        for (int j = 0; j < lijnen.size(); j++) {
            if (lijnen.get(j).substring(pos, pos + 1).equals("0")) {
                count0++;
            } else {
                count1++;
            }
        }
        return;
    }

    private void readfile(String filename) {
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
