package main;

import display.JOptionPaneScrollTextMessage;
import profiler.Stopwatch;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();

        timer.start();

        Dictionary wordlist = new Dictionary(new File("wordlist.txt"));
        timer.savePoint();

        Dictionary compoundWords = wordlist.compoundWords(6);
        timer.savePoint();

        new JOptionPaneScrollTextMessage("List of compound words", compoundWords);
        timer.savePoint();

        timer.stop();

        showStatistics(timer, compoundWords);
    }

    private static void showStatistics(Stopwatch timer, Dictionary compoundWords) {
        String statistics = "Number of compound words: " + compoundWords.size() + "\n" +
                "Reading input took " + timer.loadPoint(0) + " seconds.\n" +
                "Calculation took " + timer.loadPoint(1) + " seconds.\n" +
                "Outputting results took " + timer.loadPoint(2) + " seconds.\n" +
                "The program took " + timer.loadPoint(3) + " seconds to run.\n";

        JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
    }

}
