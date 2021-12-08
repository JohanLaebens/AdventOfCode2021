package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day8 {

    List<String> allLines;
    String[] numbers1though10 = new String[10];
    String[] actualNumbers = new String[10];
    String[] numberToSolve = new String[4];
    int globalcounter = 0;


    String a= "",b= "",c= "",d= "",e= "",f= "",g = "";
    String number0 ="",number1 = "", number2 = "",number3 = "",number4 = "",number5 = "",number6 = "",number7 = "",number8 = "", number9="";

    public static void main(String[] args) {
        new Day8();
    }


    Day8() {
        readfile("example2.txt");
        for (int i=0;i<allLines.size();i++)
        {
            addNumbers1Though10(allLines.get(i).substring(0,allLines.get(i).indexOf("|")));
            addNumbersToSolve(allLines.get(i).substring(allLines.get(i).indexOf("|")+2));
            findWhichNumberIsWhich();
            decodeMessage();
            System.out.println("TOTAL:" + globalcounter);
        }
    }

    private void decodeMessage() {
        for (int i=0;i<numberToSolve.length;i++)
        {
            for (int j=0;j<10;j++)
            {
                if (numberToSolve[i].equals(actualNumbers[j]))
                {
                    System.out.println(numberToSolve[i] + " " + actualNumbers[j] + "("+ j +")");
                    if (j == 1 || j == 4 || j== 7 || j== 8)
                    {
                        globalcounter++;
                    }
                }
            }
        }
    }

    private void findWhichNumberIsWhich() {
        // de 1 is de gene met 2
        // de 3 is de 2 met 1 veschillend (e en f)
        // de 4 is de enige met 4
        // de 5 is de 6 zonder de e
        // de 7 is de 1 met top erbij (en de enige me 3)
        // de 9 is 8 zonder de e
        // de 6 if 1 verschillend met de 0 (d en c)



        for (int i= 0;i<numbers1though10.length;i++) {
            if (numbers1though10[i].length() == 2) // nummer 1
            {
                number1 = numbers1though10[i];
                actualNumbers[1] = numbers1though10[i];
                break;
            }
        }
        for (int i= 0;i<numbers1though10.length;i++) {
            if (numbers1though10[i].length() == 3) // nummer 7
            {
                number7 = numbers1though10[i];
                actualNumbers[7] = numbers1though10[i];
                a = number7.replaceFirst(number1, "");
                System.out.println("a:" + a);
                break;
            }
        }
        for (int i= 0;i<numbers1though10.length;i++) {
            if (numbers1though10[i].length() == 4) // nummer 4
            {
                number4 = numbers1though10[i];
                actualNumbers[4] = numbers1though10[i];
                break;
            }
        }
        for (int i= 0;i<numbers1though10.length;i++) {
            if (numbers1though10[i].length() == 7) // nummer 8 // enige met alle digits
            {
                number8 = numbers1though10[i];
                actualNumbers[8] = numbers1though10[i];
                break;
            }
        }
        findLetters:
        for (int i= 0;i<numbers1though10.length;i++) {
            if (numbers1though10[i].length() == 6) // 0 , 6 en 9
            {
               // System.out.println(numbers1though10[i]);

               // System.out.println("Nummer1:" + number1);
                if (findNumber6(number1,numbers1though10[i]))
                {
                    number6 = numbers1though10[i];
                    actualNumbers[6] = numbers1though10[i];
                    // enige die ontbreekt in een 6 tov 9 is de c
                    c = findC(number8, number6);
                    System.out.println("c:" + c);

                    f = findF(number1,c);
                    System.out.println("f:" + f);

                    number2 = findNumber2(f);
                    actualNumbers[2] = number2;

                    b = findB(number2,number8,f);
                    System.out.println("b:" + b);

                    d = findD(number4, b,c,f);
                    System.out.println("d:" + d);

                    number0 = findNumber0(number8,d);
                    actualNumbers[0] = number0;

                    number9 = findNumber9(number6,number0);
                    actualNumbers[9] = number9;
                    e = findE(number8,number9);
                    System.out.println("e:" + e);

                    number5 = findNumber5(number9,c);
                    actualNumbers[5] = number5;

                    number3 = findNumber3(number5,b,c);
                    actualNumbers[3] = number3;

                    g = findG(a,b,c,d,e,f,number9);
                    break findLetters;
                }
            }
        }

    }

    private String findG(String a, String b, String c, String d, String e, String f, String number9) {
        for (int i = 0;i<number9.length();i++)
        {
            if (!(number9.substring(i,i+1).equals(a))  &&
                    !(number9.substring(i,i+1).equals(a)) &&
            !(number9.substring(i,i+1).equals(b))&&
            !(number9.substring(i,i+1).equals(c))&&
            !(number9.substring(i,i+1).equals(d))&&
            !(number9.substring(i,i+1).equals(e))&&
            !(number9.substring(i,i+1).equals(f)))
            {
                return number9.substring(i,i+1);
            }
        }
        return "";
    }

    private String findNumber3(String number5, String b, String c) {
        String temp;
        temp= sortString(number5.replaceAll(b,"") + c);
        return temp;
    }

    private String findE(String number8, String number9) {
        if (!number9.contains("g") && (number8.contains("g")))
        {
            return "g";
        }
        for (int i= 0;i<number8.length();i++) {
            if (!number8.substring(i,i+1).equals(number9.substring(i,i+1)))
            {
                return number8.substring(i,i+1);
            }
        }
        return "";
    }

    private String findNumber9(String number6, String number0) {
        for (int i= 0;i<numbers1though10.length;i++) {
            if (numbers1though10[i].length() == 6) // 0 , 6 en 9
            {
                if (!numbers1though10[i].equals(number0) && !numbers1though10[i].equals(number6))
                    return  numbers1though10[i];
            }
        }
        return "";
    }

    private String findNumber0(String number8, String d) {
        for (int i = 0;i<numbers1though10.length;i++)
        {
            if (number8.equals(sortString(numbers1though10[i] +d)))
            {
                return numbers1though10[i];
            }
        }
        return "";
    }

    private String findD(String number4, String b, String c, String f) {
        for (int i = 0;i<number4.length();i++)
        {
            if (!number4.substring(i,i+1).equals(b) && !number4.substring(i,i+1).equals(c) && !number4.substring(i,i+1).equals(f))
            {
                return number4.substring(i,i+1);
            }
        }

        return "";
    }

    private String findB(String number2, String number8, String f) {
        // de b is het verschil tussen de 8 en 2 die GEEN f is
        for (int i= 0;i<number8.length();i++) {
            if (!(number8.substring(i,i+1).equals(f)))
            {
                if (!number2.contains(number8.substring(i,i+1)))
                {
                    return number8.substring(i,i+1);
                }
            }
        }
        return "";
    }

    private String findNumber2(String f) {
        // de 2 is de enige zonder "f"
        for (int i= 0;i<numbers1though10.length;i++) {
            if (!numbers1though10[i].contains(f)) // nummer 8 // enige met alle digits
            {
               return numbers1though10[i];
            }
        }
        return "";
    }

    private String findF(String number1, String c) {
        return number1.replaceAll(c,"");
    }

    private String findNumber5(String number9, String c) {
        return number9.replaceAll(c,"");
    }

    private String findC(String number8,String number6) {
    // welkeen zit er in 8 maar niet in 6, dat is "c"
    for (int i=0;i<number8.length();i++)
    {
        if (!number6.substring(i,i+1).equals(number8.substring(i,i+1)))
        {
            return number8.substring(i,i+1);
        }
    }
    return "";
    }

    private boolean findNumber6(String number1, String potentialNumber6) {
        // de 6 is de enige die NIET beide digits heeft als de 1;
        if (!potentialNumber6.contains(number1.substring(0,1)) && potentialNumber6.contains(number1.substring(1,2)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void addNumbersToSolve(String substring) {
        String[] temp = substring.split(" ");
        for (int i =0;i<4;i++)
        {
            numberToSolve[i] = sortString(temp[i]);
        }
    }

    private void addNumbers1Though10(String substring) {
        String[] temp = substring.split(" ");
        for (int i =0;i<10;i++)
        {
            numbers1though10[i] = sortString(temp[i]);
        }
    }

    private String sortString(String value)
    {
        char tempArray[] = value.toCharArray();

        // Sorting temp array using
        Arrays.sort(tempArray);

        // Returning new sorted string
        return new String(tempArray);
    }


    private  void readfile(String filename)
    {
        try {
            allLines = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day8\\" + filename));

//            for (String line : allLines) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
