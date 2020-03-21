package main;

import display.OutputWindow;
import profiler.Stopwatch;
import words.Dictionary;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CompoundWords {

    Dictionary compoundWords;

    public CompoundWords() {
        try {
            Stopwatch timer = new Stopwatch();

            timer.start();

            Dictionary wordlist = new Dictionary(new File("wordlist.txt"));
            timer.savePoint();

            compoundWords = wordlist.compoundWords(6);
            timer.savePoint();

            displayCompoundWords();
            timer.savePoint();

            timer.stop();

            displayStatistics(timer);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Input reading unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStatistics(Stopwatch timer) {
        String statistics = "Number of compound words: " + compoundWords.size() + "\n" +
                "Reading input took " + timer.loadPoint(0) + " seconds.\n" +
                "Calculation took " + (timer.loadPoint(1) - timer.loadPoint(0)) + " seconds.\n" +
                "Outputting results took " + (timer.loadPoint(2) - timer.loadPoint(1)) + " seconds.\n" +
                "The program took " + timer.loadPoint(3) + " seconds to run.\n";

        JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayCompoundWords() {

        new OutputWindow<>("List of compound words", compoundWords);
    }
}
