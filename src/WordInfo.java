public class WordInfo implements Comparable<WordInfo> {
    public String word;
    public int moves;
    public String history;
    public int priority;

    public WordInfo(String word, int moves, String history) {
        this.word = word;
        this.moves = moves;
        this.history = history;
    }
    public WordInfo(String word, int moves, int priority, String history) {
        this.word = word;
        this.moves = moves;
        this.priority = priority;
        this.history = history;
    }

    public String toString(){
        return "Word " + word    + " Moves " + moves  + " History ["+ history +"]";
    }

    public String toString2() {
        return "Word " + word    + " Moves " + moves + " Priority " + priority  + " History ["+ history +"]";
    }

    @Override
    public int compareTo(WordInfo o) {
        if (o.priority > this.priority) return -1;
        else if (o.priority < this.priority) return 1;
        return 0;
    }
}
