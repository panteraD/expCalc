import java.util.List;

/**
 * Created by chern on 21.10.2015.
 */
public interface Source {
    void save(List<Transaction> transactions);

    List<Transaction> load();

    void close();
}
