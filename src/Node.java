import java.util.Objects;
public class Node {
    String word;
    int priority;
    int insertionOrder;

    public Node(String word, int priority, int insertionOrder){
        this.word = word;
        this.priority = priority;
        this.insertionOrder = insertionOrder;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return word.equals(node.word);
    }

    @Override
    public int hashCode(){
        return Objects.hash(word);
    }
}