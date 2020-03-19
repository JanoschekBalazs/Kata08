package main;

import profiler.Stopwatch;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        float inputTime, calcTime, outputTime, totalTime;

        stopwatch.start();

        File input = new File("wordlist.txt");

        try {
            StringBuilder statistics = new StringBuilder();

            ArrayList<String> dictionary = read(input);
            inputTime = stopwatch.checkTime();

            ArrayList<String> compoundWords = getCompoundWords(dictionary);
            calcTime = stopwatch.checkTime() - inputTime;

            new JOptionPaneScrollTextMessage("Compound Words", format(compoundWords));

            outputTime = stopwatch.checkTime() - inputTime - calcTime;
            totalTime = stopwatch.stop();

            statistics.append("Number of compound words: ").append(compoundWords.size()).append("\n");
            statistics.append("Reading input took ").append(inputTime).append(" seconds.\n");
            statistics.append("Calculation took ").append(calcTime).append(" seconds.\n");
            statistics.append("Outputting results took ").append(outputTime).append(" seconds.\n");
            statistics.append("The program took ").append(totalTime).append(" seconds to run.\n");

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

    static class JOptionPaneScrollTextMessage extends JFrame {
        public JOptionPaneScrollTextMessage(String title, String msg) {

            JTextArea jta = new JTextArea(50, 20);
            jta.setText(msg);
            jta.setEditable(false);
            JScrollPane jsp = new JScrollPane(jta);
            setLayout(new BorderLayout());
            add(jsp, BorderLayout.CENTER);

            setTitle(title);
            setSize(1024, 768);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
    }
}
