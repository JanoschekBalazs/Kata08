package words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Dictionary {

    private ArrayList<Word> wordlist;

    public Dictionary() {
        wordlist = new ArrayList<>();
    }

    public Dictionary(File input) throws IOException {
        wordlist = read(input);
    }

    public Dictionary(List<CompoundWord> wordlist) {
        this.wordlist = new ArrayList<>(wordlist);
    }

    private ArrayList<Word> read(File from) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(from));
        ArrayList<Word> toReturn = new ArrayList<>();

        String line;
        while ((line = in.readLine()) != null)
            toReturn.add(new Word(line));
        in.close();

        if (toReturn.size() == 0) throw new IllegalArgumentException("Input file is empty.");
        else return toReturn;
    }

    public void add(Word word) {
        wordlist.add(word);
    }

    public ArrayList<Word> getWordlist() {
        return wordlist;
    }

    public Dictionary compoundWords(int wordLength) {

        HashSet<Word> suitableWords = new HashSet<>();
        HashSet<Word> possibleComponents = new HashSet<>();

        for (Word word : wordlist) {
            if (word.length() == wordLength) suitableWords.add(word);
            else if (word.length() < wordLength) possibleComponents.add(word);
        }

        return new Dictionary(findCompoundWords(suitableWords, possibleComponents));
    }

    private ArrayList<CompoundWord> findCompoundWords(HashSet<Word> suitableWords, HashSet<Word> possibleComponents) {

        ArrayList<CompoundWord> toReturn = new ArrayList<>();
        CompoundWord[] temp;

        for (Word word : suitableWords) {
            temp = word.possibleCompounds();
            for (CompoundWord compound : temp) {
                if (possibleComponents.contains(compound.getPrefix()) && possibleComponents.contains(compound.getSuffix()))
                    toReturn.add(compound);
            }
        }
        return toReturn;
    }

    public int size() {
        return wordlist.size();
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Word word : wordlist) {
            toReturn.append(word).append("\n");
        }
        return toReturn.toString();
    }
}
