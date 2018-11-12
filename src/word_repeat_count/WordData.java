package word_repeat_count;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WordData {
    private final StringProperty word;
    private final StringProperty count;

    public WordData(String word, String count) {
        this.word = new SimpleStringProperty(word);
        this.count = new SimpleStringProperty(count);
    }

    public WordData() {
        this.word = null;
        this.count = null;
    }

    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public StringProperty wordProperty() {
        return word;
    }

    public String getCount() {
        return count.get();
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public StringProperty countProperty() {
        return count;
    }
}
