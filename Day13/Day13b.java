package Day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day13b {

    List<String> allLines;
    List<String> folds;
    Integer[][] map;

    int maxSizeX = 0;
    int maxSizeY = 0;

    public static void main(String[] args) {
        new Day13b();
    }


    Day13b() {
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

        if (foldAxis.equals("y"))
        {
            for (int y=0;y<=foldCoordinate;y++)
            {
                for (int x = 0;x<maxSizeX;x++)
                {
                    map[y][x] = map[y][x]+ map[maxSizeY-1-y][x];
                }
            }
            Integer[][] tempMap = new Integer[foldCoordinate][maxSizeX];
            for (int y=0;y<foldCoordinate;y++)
            {
                for (int x = 0;x<maxSizeX;x++)
                {
                    tempMap[y][x] = map[y][x];
                }
            }
            maxSizeY = foldCoordinate;
            map = new Integer[maxSizeY][maxSizeX];
            map = tempMap;
        }
        if (foldAxis.equals("x"))
        {
            for (int y=0;y<maxSizeY;y++)
            {
                for (int x = 0;x<=foldCoordinate;x++)
                {
                    map[y][x] = map[y][x]+ map[y][maxSizeX-1-x];
                }
            }
            Integer[][] tempMap = new Integer[maxSizeY][foldCoordinate];
            for (int y=0;y<maxSizeY;y++)
            {
                for (int x = 0;x<foldCoordinate;x++)
                {
                    tempMap[y][x] = map[y][x];
                }
            }
            maxSizeX = foldCoordinate;
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
