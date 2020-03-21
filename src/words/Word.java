package words;

import java.util.ArrayList;
import java.util.Objects;

public class Word {

    private String word;

    public Word(String word) {
        if (word.contains("\t") || word.contains(" ") || word.equals(""))
            throw new IllegalArgumentException("Input string isn't a word.");
        this.word = word;
    }

    public int length() {
        return word.length();
    }

    public String toString() {
        return word;
    }

    public ArrayList<CompoundWord> possibleCompounds() {
        int count = word.length() - 1;
        ArrayList<CompoundWord> toReturn = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            toReturn.add(new CompoundWord(word, i + 1));
        }
        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
