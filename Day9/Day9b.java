package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Day9b {

    List<String> allLines;
    Integer[][] map;
    int basinResult = 0;
    List<Integer> results = new ArrayList<Integer>();


    public static void main(String[] args) {
        new Day9b();
    }


    Day9b() {
        readfile("input.txt");
        map = new Integer[allLines.size()+2][allLines.get(0).length()+2];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                map[i+1][j+1] = Integer.parseInt(allLines.get(i).substring(j, j + 1));
            }
        }

        put9borderAroundMap();
   //    printmap();
        findBasins();
        Collections.sort(results, Collections.reverseOrder());

      System.out.println("FINAL RESULT: " + results.get(0)*results.get(1)*results.get(2));
    }

    private void findBasins() {
        for (int y = 0;y<map.length;y++)
        {
            for (int x=0;x<map[0].length;x++)
            {
                if (map[y][x] != 9)
                {
                    basinResult = 0;
                    // een eerste deel van een gat
                    currentRow:
                    for (int currX=x;currX<map[0].length;currX++)
                    {
                        if (map[y][currX] != 9) {
                            basinResult ++;
                            map[y][currX] = 9;
                      //      printmap();
                            lookAtLine(y+1,currX);
                        }
                        else
                        {
                            break currentRow;
                        }
                    }
                    //System.out.println("RESULT: " + basinResult);
                    System.out.println(""+ basinResult);
                    results.add(basinResult);
                }
            }
        }
    }

    private void lookAtLine(int line, int x) {
        if (map[line][x] != 9) {
            // eerste vlak eronder en erboven zien
            basinResult++;
            map[line][x] = 9;
            lookAtLine(line+1,x);
            lookAtLine(line-1,x);
            //        printmap();
                // eerst naar links gaan zien
                toTheLeft:
                for (int k = x - 1; k >= 0; k--) {
                    if (map[line][k] != 9) {
                        basinResult++;
                        map[line][k] = 9;
                        lookAtLine(line+1,k);
                        lookAtLine(line-1,k);
                    } else {
                        break toTheLeft;
                    }
                }

                // dan naar rechts
                toTheRight:
                for (int k = x + 1; k <= map[0].length; k++) {
                    if (map[line][k] != 9) {
//                        printmap();
                        basinResult++;
                        map[line][k] = 9;
                        lookAtLine(line+1,k);
                        lookAtLine(line-1,k);
                    } else {
                        break toTheRight;
                    }
                }
            }
    }


    private void put9borderAroundMap() {
        for (int i=0;i<map.length;i++)
        {
            map[i][0] = 9;
            map[i][map[0].length-1] = 9;
        }
        for (int i = 0; i< map[0].length; i++)
        {
            map[0][i] = 9;
            map[map.length-1][i] = 9;
        }
    }


    private void printmap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }


    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day9\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
