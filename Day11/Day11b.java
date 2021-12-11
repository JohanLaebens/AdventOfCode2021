package Day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day11b {

    List<String> allLines;
    Integer[][] map;
    boolean[][] mapAllreadyFlashed;
    int flashcount = 0;

        public static void main(String[] args) {
        new Day11b();
    }


    Day11b() {
        readfile("input.txt");
        map = new Integer[allLines.size()][allLines.get(0).length()];
        mapAllreadyFlashed = new boolean[allLines.size()][allLines.get(0).length()];

        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                map[i][j] = Integer.parseInt(allLines.get(i).substring(j, j + 1));
                mapAllreadyFlashed[i][j] = false;
            }
        }
        printmap();
        for (int i=1;i<=900;i++) {
            addOneToALl();
            scanForFlash();
            setAllGreaterThenNineToZero();
            printmap();
            System.out.println("After step " + i);
            System.out.println("");
        }
        System.out.println("total Flashes: " + flashcount);
    }

    private void scanForFlash() {
        //you flash ONLY when you are 10
        for (int y=0;y<map.length;y++)
        {
            for (int x=0;x<map[y].length;x++)
            {
                if (map[y][x] > 9 && mapAllreadyFlashed[y][x]==false)
                {
                    // it is the top row?
                    if (y == 0) {
                        //first check if it's upper left corner
                        if (y == 0 && x == 0) {
                            map[y+1][x] =  map[y+1][x] +1;
                            map[y][x+1] =  map[y][x+1] +1;
                            map[y+1][x+1] =  map[y+1][x+1] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }

                        //second check if it's upper right corner
                        else if (y == 0 && x == map[y].length-1) {
                            map[y][x-1] =  map[y][x-1] +1;
                            map[y+1][x-1] =  map[y+1][x-1] +1;
                            map[y+1][x] =  map[y+1][x] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }
                        else
                        {
                            map[y][x-1] =  map[y][x-1] +1;
                            map[y][x+1] =  map[y][x+1] +1;
                            map[y+1][x-1] =  map[y+1][x-1] +1;
                            map[y+1][x+1] =  map[y+1][x+1] +1;
                            map[y+1][x] =  map[y+1][x] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }
                    }

                    // is it the bottom row
                    else if (y == map.length-1) {
                        //third check the lower left corner
                        if (y == map.length-1 && x == 0) {
                            map[y-1][x] =  map[y-1][x] +1;
                            map[y][x+1] =  map[y][x+1] +1;
                            map[y-1][x+1] =  map[y-1][x+1] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }

                        //fourth check the lower right corner
                        else if (y == map.length-1 && x == map[y].length-1) {
                            map[y][x-1] =  map[y][x-1] +1;
                            map[y-1][x-1] =  map[y-1][x-1] +1;
                            map[y-1][x] =  map[y-1][x] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }
                        else
                        { // rest of bottom row
                            map[y][x-1] =  map[y][x-1] +1;
                            map[y][x+1] =  map[y][x+1] +1;
                            map[y-1][x-1] =  map[y-1][x-1] +1;
                            map[y-1][x+1] =  map[y-1][x+1] +1;
                            map[y-1][x] =  map[y-1][x] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }
                    }
                    else
                    {
                        // normal procedure
                        // check if it's the upmost left side but don't worry, corner cases are already covered before
                        if (x== 0)
                        {
                            map[y-1][x] =  map[y-1][x] +1;
                            map[y+1][x] =  map[y+1][x] +1;

                            map[y-1][x+1] =  map[y-1][x+1] +1;
                            map[y+1][x+1] =  map[y+1][x+1] +1;

                            map[y][x+1] =  map[y][x+1] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();

                        }
                        else if (x == map[y].length-1)//check if utmost right side
                        {
                            map[y-1][x] =  map[y-1][x] +1;
                            map[y+1][x] =  map[y+1][x] +1;

                            map[y-1][x-1] =  map[y-1][x-1] +1;
                            map[y+1][x-1] =  map[y+1][x-1] +1;

                            map[y][x-1] =  map[y][x-1] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }
                        else
                        {
                            // normal field
                            map[y-1][x-1] =  map[y-1][x-1] +1;
                            map[y-1][x] =  map[y-1][x] +1;
                            map[y-1][x+1] =  map[y-1][x+1] +1;

                            map[y][x-1] =  map[y][x-1] +1;
                            map[y][x+1] =  map[y][x+1] +1;

                            map[y+1][x-1] =  map[y+1][x-1] +1;
                            map[y+1][x] =  map[y+1][x] +1;
                            map[y+1][x+1] =  map[y+1][x+1] +1;

                            mapAllreadyFlashed[y][x] = true;
                            flashcount++;
                            scanForFlash();
                        }
// add 1 energy to all all surrounding
                }
            }
        }
    }}

    private void setAllGreaterThenNineToZero() {
        int countFlashed =0;
        for (int i=0;i<map.length;i++)
        {
            for (int j=0;j<map[i].length;j++)
            {
                if (mapAllreadyFlashed[i][j] == true) {
                    countFlashed++;
                    mapAllreadyFlashed[i][j] = false;
                }
                if (map[i][j] > 9)
                {
                    map[i][j] = 0;
                }
            }
        }
        if (countFlashed == 100)
        {
            System.out.println("They will all flash NEXT RUN");
            System.exit(1);
        }

    }

    private void addOneToALl() {
        for (int i=0;i<map.length;i++)
        {
            for (int j=0;j<map[i].length;j++)
            {
                map[i][j] = map[i][j]+1;
            }
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
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day11\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
