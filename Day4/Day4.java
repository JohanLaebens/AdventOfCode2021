package Day4;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Day4 {

    List<String> allLines;
    String[][] boards;
    String[][] marked;

    int lastNumberDrawn = 0;

    public static void main(String[] args) {
        new Day4();
    }


    Day4() {
        readfile("input.txt");
        String[] drawNumbers = allLines.get(0).split(",");
        //remove the first 2 lines
        allLines.remove(0);
        allLines.remove(0);

        for (int i=0;i< allLines.size();i++)
        {
            if (allLines.get(i).equals(""))
            {
                allLines.remove(i);
            }
        }

        boards = new String[allLines.size()][5];
        marked = new String[allLines.size()][5];


        fillBoards();

        for (int i=0;i<drawNumbers.length;i++)
        {
            findNumberOnBoards(drawNumbers[i]);
        }

    }

    private void findNumberOnBoards(String drawNumber) {
        lastNumberDrawn = Integer.parseInt(drawNumber);
        for (int i=0;i<boards.length;i++)
        {
            for(int j=0;j<boards[j].length;j++)
            {
                if (boards[i][j].equals(drawNumber))
                {
                    marked[i][j] = "M";
 //                   boards[i][j] = drawNumber;
                    isThereABingo();
                }
            }
        }
    }

    private void isThereABingo() {
        //horizontalRows
        for (int i=0;i<marked.length;i++)
        {
            if (marked[i][0].equals("M")&& marked[i][1].equals("M") &&
                    marked[i][2].equals("M")&& marked[i][3].equals("M") &&
                    marked[i][4].equals("M"))
            {
                System.out.println("BINGO - HORIZONTAL");
                for (int z=0;z<5;z++)
                {
                    System.out.print(boards[i][z] + " - ");
                }
                //System.out.println(boards[i]);
                calculateScore(i);
                System.exit(0);
            }
        }

        //per board
        for (int j=0;j<marked.length/5;j++)
        {
            for (int k=0;k<5;k++) {
                if (marked[j*5][k].equals("M") && marked[j*5+1][k].equals("M") &&
                        marked[j*5+2][k].equals("M") && marked[j*5+3][k].equals("M") &&
                        marked[j*5+4][k].equals("M")) {
                    System.out.println("BINGO - vertical");
                    for (int z=0;z<5;z++)
                    {
                        System.out.print(boards[j*5+z][k] + " - ");
                    }
                    calculateScore(j*5);
                    System.exit(0);
                    //System.out.print(boards.get(i));
                }
            }
        }
    }

    private void calculateScore(int bingoline) {
        // sum of all unmarked numbers of the board
        int startline = (bingoline / 5)*5;
        int sum = 0;
        for (int i = startline;i<startline+5;i++) // rows
        {
            for (int j =0;j<5;j++)  // columns
            {
                if(! marked[i][j].equals("M"))
                {
                    sum = sum + Integer.parseInt( marked[i][j]);
                }
            }
        }
        System.out.println("SUM NON MARKED = " + sum);
        System.out.println("lastnumberDrawn = " + lastNumberDrawn);
        System.out.println("RESULT: "+ sum*lastNumberDrawn);
    }

    private void fillBoards() {

        for (int i=0;i<allLines.size();i++) {
            if (!allLines.get(i).equals("")) {
                allLines.set(i,allLines.get(i).trim());
                allLines.set(i,allLines.get(i).replaceAll("  "," "));
                String[] row = allLines.get(i).split(" ");
                for (int j=0;j<5;j++)
                {
                    boards[i][j] = row[j];
                    marked[i][j]=row[j];
                }
            }
        }
       // System.arraycopy(boards,0,marked,0,boards.length);
    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day4\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
