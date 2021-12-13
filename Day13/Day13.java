package Day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day13 {

    List<String> allLines;
    List<String> folds;
    Integer[][] map;

    int maxSizeX = 0;
    int maxSizeY = 0;

    public static void main(String[] args) {
        new Day13();
    }


    Day13() {
        readfile("input");


        maxSizeX = findMaxXCoordinates();
        maxSizeY = findMaxYCoordinates();

        map = new Integer[maxSizeY][maxSizeX];
        for (int y=0;y<maxSizeY;y++)
        {
            for (int x=0;x<maxSizeX;x++)
            {
                map[y][x] = 0;
            }
        }

        for (int i=0;i<allLines.size();i++)
        {
            int xCoor = Integer.parseInt(allLines.get(i).substring(0,allLines.get(i).indexOf(",")));
            int yCoor = Integer.parseInt(allLines.get(i).substring(allLines.get(i).indexOf(",")+1));
            map[yCoor][xCoor] = 1;
        }

       // printmap();

        for (int i= 0;i<folds.size();i++)
        {
            foldMap(folds.get(i).substring(folds.get(i).lastIndexOf(" ")));
            System.out.println("Round "+ i +":" + countNumberOfPoints());
       //     printmap();
        }
      //  System.out.println("");
       // System.out.println("");
        printmap();
    }

    private int countNumberOfPoints() {
        int count = 0;
        for (int y=0;y<maxSizeY;y++)
        {
            for (int x=0;x<maxSizeX;x++)
            {
                if (map[y][x] != 0)
                {
                 count++;
                }
            }
        }
        return count;
    }

    private int findMaxXCoordinates() {
        int maxCoordinates =0;
        for (int i=0;i<allLines.size();i++)
        {
            int tmp = Integer.parseInt(allLines.get(i).substring(0,allLines.get(i).indexOf(",")));
            if (tmp > maxCoordinates)
            {
                maxCoordinates = tmp;
            }
        }
        return maxCoordinates+1;
    }


    private int findMaxYCoordinates() {
        int maxCoordinates =0;
        for (int i=0;i<allLines.size();i++)
        {
            int tmp = Integer.parseInt(allLines.get(i).substring(allLines.get(i).indexOf(",")+1));
            if (tmp > maxCoordinates)
            {
                maxCoordinates = tmp;
            }
        }
        return maxCoordinates+1;
    }

    private void foldMap(String foldInstruction) {
        String foldAxis = foldInstruction.substring(0,foldInstruction.indexOf("=")).trim();
        int foldCoordinate = Integer.parseInt(foldInstruction.substring(foldInstruction.indexOf("=")+1));

        int offset = 0;
        if (foldAxis.equals("y"))
        {
           offset =  foldCoordinate-(maxSizeY-foldCoordinate)+1;
            int i = 1;
            for (int y=foldCoordinate-1;y>=Math.abs(offset);y--)
            {
                for (int x = 0;x<maxSizeX;x++)
                {
                    map[y][x] = map[y][x]+ map[foldCoordinate+i][x];
                }
                i++;
            }
            maxSizeY = foldCoordinate;
            Integer[][] tempMap = new Integer[maxSizeY][maxSizeX];
            for (int y=0;y<maxSizeY;y++)
            {
                for (int x = 0;x<maxSizeX;x++)
                {
                    tempMap[y][x] = map[y][x];
                }
            }
            map = new Integer[maxSizeY][maxSizeX];
            map = tempMap;
        }
        if (foldAxis.equals("x"))
        {
            offset = (maxSizeX/2)-foldCoordinate;
            for (int y=0;y<maxSizeY;y++)
            {
                int i = 1;
                for (int x = foldCoordinate-1;x>=Math.abs(offset);x--)
                {
                    map[y][x] = map[y][x]+ map[y][foldCoordinate+i];
                    i++;
                }
            }
            maxSizeX = foldCoordinate;
            Integer[][] tempMap = new Integer[maxSizeY][maxSizeX];
            for (int y=0;y<maxSizeY;y++)
            {
                for (int x = 0;x<maxSizeX;x++)
                {
                    tempMap[y][x] = map[y][x];
                }
            }
            map = new Integer[maxSizeY][maxSizeX];
            map = tempMap;
        }
    }

    private void printmap() {
        for (int y=0;y<maxSizeY;y++)
        {
            for (int x=0;x<maxSizeX;x++)
            {
                if(map[y][x] !=0)
                {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }


    private void readfile(String filename) {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day13\\" + filename +".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            folds = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day13\\" + filename +"_fold.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
