package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day14 {

    List<String> templates;
    List<String> rules;
    String template;

    public static void main(String[] args) {
        new Day14();
    }


    Day14() {
        readfile("input");
        template = templates.get(0);
     //   System.out.println(template);
        for (int i=0;i<10;i++)
        {
            template = runThroughRules(template);
   //         System.out.println(template);
        }
        findLeastAndMostCommon(template);
    }

    private void findLeastAndMostCommon(String template) {
        // voor 't gemak, alle letters van 't alphabet doen mee ;-)
        String tmp = "";
        Integer[] letters = new Integer[26];

        for (int i=0;i<26;i++)
        {
            tmp = template.replaceAll(getCharForNumber(i),"");
            letters[i] = template.length() - tmp.length();
        }

        int most = Integer.MIN_VALUE;
        int least = Integer.MAX_VALUE;
        for (int i=0;i<26;i++) {
            if (letters[i] !=0)
            {
                if (letters[i] > most)
                {
                    most = letters[i];
                }
                if (letters[i] < least)
                {
                    least = letters[i];
                }
            }
         //   System.out.println("MOST: " + getCharForNumber(i) + " " + most);
         //   System.out.println("LEAST: " + getCharForNumber(i) + " " + least);
        }
        System.out.println(most-least);
    }

    private String runThroughRules(String templatelocal) {
        StringBuilder result = new StringBuilder();
        for (int i=0;i<templatelocal.length()-1;i++)
        {
            String part = templatelocal.substring(i,i+2);
         //   System.out.print(i + " " + part + " ");
            searchForMatch:
            for (int j=0;j<rules.size();j++)
            {
                if (rules.get(j).substring(0,2).equals(part))
                {
                    result.append(templatelocal.substring(i,i+1));
                    result.append(rules.get(j).substring(rules.get(j).lastIndexOf(" ")+1));
            //        result.append(template.substring(i+1,i+2));
                    break searchForMatch;
                }
            }
       //     System.out.println(result);
        }
        result.append(templatelocal.substring(templatelocal.length()-1,templatelocal.length()));
     //   System.out.println(result.length());
        return result.toString();
    }

    private String getCharForNumber(int i) {
        return String.valueOf((char)(i + 64));
    }

    private void readfile(String filename) {
        try {
            templates = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day14\\" + filename + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rules = Files.readAllLines(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\Day14\\" + filename + "_rules.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
