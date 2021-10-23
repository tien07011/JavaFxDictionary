package DictionaryApplication.DictionaryCommandLine;

public class Word {
    //tu moi
    private String word_target;
    //giai nghia
    private String word_explain;

    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word = (Word) o;

        if (getWord_target() != null ? !getWord_target().equals(word.getWord_target()) : word.getWord_target() != null)
            return false;
        return getWord_explain() != null ? getWord_explain().equals(word.getWord_explain()) : word.getWord_explain() == null;
    }

    @Override
    public int hashCode() {
        int result = getWord_target() != null ? getWord_target().hashCode() : 0;
        result = 31 * result + (getWord_explain() != null ? getWord_explain().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Word{" + "word_target=" + word_target + ",word_explain=" + word_explain + '}';
    }
}
