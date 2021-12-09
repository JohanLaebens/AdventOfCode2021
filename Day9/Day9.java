package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day9 {

    List<String> allLines;
    Integer[][] map;
    Integer riskLevel = 0;

    public static void main(String[] args) {
        new Day9();
    }


    Day9() {
        readfile("input.txt");
        map = new Integer[allLines.size()][allLines.get(0).length()];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                map[i][j] = Integer.parseInt(allLines.get(i).substring(j, j + 1));
            }
        }
       // printmap();
        findLowPoints();
    }

    private void findLowPoints() {
        for (int i = 0; i < map.length; i++) {
            System.out.print("LINE:" + i);
            for (int j = 0; j < map[i].length; j++) {
                System.out.println(" ROW:" + j);
                // it is the top row?
                if (i == 0) {
                    //first check if it's upper left corner
                    if (i == 0 && j == 0) {
                        if (map[i][j] < map[i+1][j] &&
                                map[i][j] < map[i][j+1]  )
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }

                    //second check if it's upper right corner
                    else if (i == 0 && j == map[i].length-1) {
                        if (map[i][j] < map[i+1][j] &&
                                map[i][j] < map[i][j-1])
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }
                    else
                    {
                        if ( map[i][j] < map[i+1][j] &&
                                map[i][j] < map[i][j-1] &&
                                map[i][j] < map[i][j+1]  )
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }
                }

                // is it the bottom row
                else if (i == map.length-1) {
                    //third check the lower left corner
                    if (i == map.length-1 && j == 0) {
                        if (map[i][j] < map[i-1][j] &&
                                map[i][j] < map[i][j+1]  )
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }

                    //fourth check the lower right corner
                    else if (i == map.length-1 && j == map[i].length-1) {
                        if (map[i][j] < map[i-1][j] &&
                                map[i][j] < map[i][j-1] )
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }
                    else
                    { // rest of bottom row
                        if (map[i][j] < map[i-1][j] &&
                                map[i][j] < map[i][j-1] &&
                                map[i][j] < map[i][j+1]  )
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }
                }
                else
                {
                    // normal procedure
                    // check if it's the upmost left side but don't worry, corner cases are already covered before
                    if (j== 0)
                    {
                        if (map[i][j] < map[i+1][j] &&
                                map[i][j] < map[i-1][j] &&
                                map[i][j] < map[i][j+1]  )
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }
                    else if (j == map[i].length-1)//check if utmost right side
                    {
                        if (map[i][j] < map[i-1][j] &&
                                map[i][j] < map[i+1][j] &&
                                map[i][j] < map[i][j-1])
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }
                    else
                    {
                        // normal field
                        if (map[i][j] < map[i-1][j] &&
                                map[i][j] < map[i+1][j] &&
                                map[i][j] < map[i][j-1] &&
                                map[i][j] < map[i][j+1])
                        {
                            System.out.println(map[i][j]);
                            riskLevel+=map[i][j]+1;
                        }
                    }

                }

            }
        }
        System.out.println("risklevel: " + riskLevel);
    }


    private void printmap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
    }


    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day9\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
