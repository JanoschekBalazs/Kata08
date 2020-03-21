package main;

import display.OutputWindow;
import profiler.Stopwatch;
import words.Dictionary;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CompoundWords {

    Stopwatch timer;

    Dictionary compoundWords;
    String filename;
    int wordlength;

    public CompoundWords() {
        try {
            timer = new Stopwatch();

            displayInput();

            timer.start();

            Dictionary wordlist = new Dictionary(new File(filename));
            timer.savePoint();

            compoundWords = wordlist.compoundWords(wordlength);
            timer.savePoint();

            displayCompoundWords();
            timer.stop();

            displayStatistics();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Input reading unsuccessful! Invalid file name!", "Error", JOptionPane.ERROR_MESSAGE);
            new CompoundWords();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input reading unsuccessful! Word length must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
            new CompoundWords();
        }
    }

    private void displayInput() {
        filename = JOptionPane.showInputDialog(null, "Please type in the name and location of your dictionary:", "Compound words", JOptionPane.PLAIN_MESSAGE);
        String wordlengthString = JOptionPane.showInputDialog(null, "Please type in the length (positive integer) of the compound words to look for:", "Compound words", JOptionPane.PLAIN_MESSAGE);
        if (wordlengthString.equals("")) throw new NumberFormatException("Input must be a positive integer.");
        wordlength = Integer.parseInt(wordlengthString);
        if (wordlength <= 0) throw new NumberFormatException("Input must be a positive integer.");
    }

    private void displayCompoundWords() {

        new OutputWindow<>("List of compound words", compoundWords);
    }

    private void displayStatistics() {
        String statistics = "Number of compound words: " + compoundWords.size() + "\n" +
                "File reading took " + timer.loadPoint(0) + " seconds.\n" +
                "Calculation took " + (timer.loadPoint(1) - timer.loadPoint(0)) + " seconds.\n" +
                "Outputting results took " + (timer.loadPoint(2) - timer.loadPoint(1)) + " seconds.\n" +
                "The program was running for " + timer.loadPoint(2) + " seconds.\n";

        JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}
