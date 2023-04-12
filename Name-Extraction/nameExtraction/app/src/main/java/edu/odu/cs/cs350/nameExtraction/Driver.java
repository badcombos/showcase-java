package edu.odu.cs.cs350.nameExtraction;

//file reading
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

//regex
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Driver {

    /**
     * @author cody
     * @param java string, filename of input file
     * @return java ArrayList<String>, all text in input file is split between <NRE> </NRE> tags and saved into seperate indexes of ArrayList
     * reads text from input file and returns as string
     */
    public static ArrayList<String> parseAllBlocks(String filename){
        ArrayList<String> AllBLocks = new ArrayList<String>();

        String INPUT = readFile(filename);
        String REGEX = "(?:.*)\\<NER>(.*)\\<\\/NER>";

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);

        while(matcher.find()) {
            //System.out.println(matcher.group(1));
            AllBLocks.add(matcher.group(1));
        }

        if(AllBLocks.size()==0){
            System.out.println("cannot find any matches");
            System.exit(1);
        }

        return AllBLocks;
    }

    /**
     * @author cody
     * @param java string, filename of input file
     * @return java string, text of input file
     * reads text from input file and returns as string
     */
    public static String readFile(String filename){
        Path path = Paths.get(filename);
        String s = null;
        try {
            s = Files.readString(path);
        }
        catch (IOException e){
            System.out.println("invalid selection: cannot find file!");
            e.printStackTrace();
            System.exit(1);
        }
        return s;
    }

    //for testing
    private static void output(){
        ArrayList<String> al = parseAllBlocks("input.txt");
        for( String s : al){
            System.out.println(s+"\n");
        }
    }
    public static void main(String[] args) {
        output();
    }
}
