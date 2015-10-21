import java.util.LinkedList;

/**
 * Created by chern on 21.10.2015.
 */
public abstract class Source {
    public abstract void saveToFile(LinkedList<Transaction> transactions);
    public abstract LinkedList<Transaction>  loadFromFile();
}
