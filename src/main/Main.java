package main;

import profiler.Stopwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        float elapsedTime;
        stopwatch.start();

        File input = new File("wordlist.txt");

        try {
            ArrayList<String> words = read(input);

            elapsedTime = stopwatch.checkTime();
            System.out.println("Reading input took " + elapsedTime + " seconds.");

            ArrayList<String> output = getCompoundWords(words);

            elapsedTime = stopwatch.checkTime() - elapsedTime;
            System.out.println("Calculation took " + elapsedTime + " seconds.");

        } catch (FileNotFoundException e) {
            System.out.println("Error! Input file not found!");
        }
    }

    private static ArrayList<String> read(File from) throws FileNotFoundException {
        Scanner in;
        in = new Scanner(from);
        ArrayList<String> toReturn = new ArrayList<>();
        while (in.hasNextLine())
            toReturn.add(in.nextLine());
        in.close();
        return toReturn;
    }

    private static ArrayList<String> getCompoundWords(ArrayList<String> words) {

        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<ArrayList<String>> compounds;

        for (String word : words) {
            if (word.length() == 6) {
                compounds = getCompounds(word);
                for (ArrayList<String> compound : compounds) {
                    if (words.contains(compound.get(0)) && words.contains(compound.get(1))) {
                        String s = compound.get(0) + " + " + compound.get(1) + " = " + word;
                        toReturn.add(s);
                    }
                }
            }
        }
        return toReturn;
    }

    private static ArrayList<String> split(int breakpoint, String s) {
        ArrayList<String> toReturn = new ArrayList<>(2);
        toReturn.add(s.substring(0, breakpoint));
        toReturn.add(s.substring(breakpoint));
        return toReturn;
    }

    private static ArrayList<ArrayList<String>> getCompounds(String s) {
        ArrayList<ArrayList<String>> toReturn = new ArrayList<>(5);
        for (int i = 1; i < 6; i++) {
            toReturn.add(split(i, s));
        }
        return toReturn;
    }
}
