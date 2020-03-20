package main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

    ArrayList<Word> dictionary;

    public Dictionary(File input) {
        try {
            dictionary = read(input);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error! Input file not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public <E extends Word> Dictionary(List<E> wordlist) {
        dictionary = new ArrayList<>(wordlist);
    }

    private ArrayList<Word> read(File from) throws FileNotFoundException {
        Scanner in;
        in = new Scanner(from);
        ArrayList<Word> toReturn = new ArrayList<>();
        while (in.hasNextLine())
            toReturn.add(new Word(in.nextLine()));
        in.close();
        return toReturn;
    }

    public Dictionary compoundWords(int lengthNeeded) {

        HashSet<Word> wordsNeeded = new HashSet<>();
        HashSet<Word> possibleComponents = new HashSet<>();
        HashSet<CompoundWord> compoundWords = new HashSet<>();

        for (Word word : dictionary) {
            if (word.length() == lengthNeeded) wordsNeeded.add(word);
            else if (word.length() < lengthNeeded) possibleComponents.add(word);
        }

        CompoundWord[] temp;
        for (Word word : wordsNeeded) {
            temp = word.possibleCompounds();
            for (CompoundWord compound : temp) {
                if (possibleComponents.contains(compound.getPrefix()) && possibleComponents.contains(compound.getSuffix()))
                    compoundWords.add(compound);
            }
        }
        return new Dictionary(new ArrayList<>(compoundWords));
    }

    public int size() {
        return dictionary.size();
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Word word : dictionary) {
            toReturn.append(word).append("\n");
        }
        return toReturn.toString();
    }

}
