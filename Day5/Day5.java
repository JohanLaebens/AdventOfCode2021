package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day5 {

    List<String> allLines;
    Integer[][] board;
    int DIMENTION = 10;

    public static void main(String[] args) {
        new Day5();
    }


    Day5() {
        readfile("input.txt");
        board = new Integer[DIMENTION][DIMENTION];
        fillBoard();
        //printBoard();

        for (int i = 0;i<allLines.size();i++)
        {
            int x1,x2,y1,y2 = 0;
            String line = allLines.get(i);

            x1 = Integer.parseInt(line.substring(0,line.indexOf(",")));
            y1 = Integer.parseInt(line.substring(line.indexOf(",")+1,line.indexOf(" ")));

            x2 = Integer.parseInt(line.substring(line.indexOf("-> ")+3,line.lastIndexOf(",")));
            y2 = Integer.parseInt(line.substring(line.lastIndexOf(",")+1));

            if( (x1 == x2) || (y1 == y2)) // enkel horizontale of vertikale lijnen
            {
                drawLine(x1,y1,x2,y2);
               // printBoard();
                //System.out.println();
            }

        }
       // printBoard();
        countOverlappingPoint();
    }



    private void countOverlappingPoint() {
        int counter = 0;
        for (int i = 0;i<DIMENTION;i++)
        {
            for (int j = 0;j< DIMENTION;j++)
            {
                if (board[i][j] >= 2)
                {
                    counter++;
                }
            }
        }
        System.out.println("Overlapping point: " + counter);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        // verticale lijn
        if ((x1 == x2))
        {
            if (y1 > y2)
            {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }
            for (int y=y1;y<=y2;y++ )
            {
                board[y][x1] = board[y][x1]+1;
            }
        }



        // Horizontale lijn
        if ((y1 == y2))
        {
            if (x1 > x2)
            {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            for (int x= x1;x<=x2;x++ )
            {
                board[y1][x] = board[y1][x]+1;
                //printBoard();
            }
        }

    }


    private void printBoard() {
        for (int i = 0;i<DIMENTION;i++)
        {
            for (int j=0;j<DIMENTION;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private void fillBoard() {
        for (int i = 0;i<DIMENTION;i++)
        {
            for (int j=0;j<DIMENTION;j++)
            {
                board[i][j] = 0;
            }
        }
    }




    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day5\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
