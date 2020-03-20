package main;

import profiler.Stopwatch;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stopwatch tempTimer = new Stopwatch();
        Stopwatch totalTimer = new Stopwatch();
        float inputTime, calcTime, outputTime, totalTime;

        try {
            totalTimer.start();

            tempTimer.start();
            File input = new File("wordlist.txt");
            ArrayList<String> dictionary = read(input);
            inputTime = tempTimer.stop();

            tempTimer.start();
            ArrayList<String> compoundWords = getCompoundWords(dictionary);
            calcTime = tempTimer.stop();

            tempTimer.start();
            new JOptionPaneScrollTextMessage("Compound Words", format(compoundWords));
            outputTime = tempTimer.stop();

            totalTime = totalTimer.stop();

            String statistics = getStatistics(inputTime, calcTime, outputTime, totalTime, compoundWords);

            JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error! Input file not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String format(ArrayList<String> compoundWords) {
        StringBuilder toReturn = new StringBuilder();
        int index = 1;
        for (String word : compoundWords) {
            toReturn.append(index).append(". ").append(word).append("\n");
            index++;
        }
        return toReturn.toString();
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

    private static String getStatistics(float inputTime, float calcTime, float outputTime, float totalTime, ArrayList<String> compoundWords) {
        return "Number of compound words: " + compoundWords.size() + "\n" +
                "Reading input took " + inputTime + " seconds.\n" +
                "Calculation took " + calcTime + " seconds.\n" +
                "Outputting results took " + outputTime + " seconds.\n" +
                "The program took " + totalTime + " seconds to run.\n";
    }

}
