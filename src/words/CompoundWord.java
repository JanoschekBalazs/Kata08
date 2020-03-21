package words;

import java.util.Objects;

public class CompoundWord extends Word {

    private Word prefix;
    private Word suffix;

    public CompoundWord(String word, int breakpoint) {
        super(word);
        split(breakpoint);
    }

    public CompoundWord(Word word, int breakpoint) {
        super(word.toString());
        split(breakpoint);
    }

    private void split(int breakpoint) {
        prefix = new Word(super.toString().substring(0, breakpoint));
        suffix = new Word(super.toString().substring(breakpoint));
    }

    public Word getPrefix() {
        return prefix;
    }

    public Word getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return prefix + " + " + suffix + " => " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompoundWord)) return false;
        if (!super.equals(o)) return false;
        CompoundWord that = (CompoundWord) o;
        return prefix.equals(that.prefix) &&
                suffix.equals(that.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prefix, suffix);
    }
}
