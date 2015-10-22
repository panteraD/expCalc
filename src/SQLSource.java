import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Андрей on 22.10.2015.
 */
public class SQLSource implements Source {


    @Override
    public void close() {
        closeDB();
    }

    public static final String TABLE_NAME = "transactions";
    public static final String COLUMN1_NAME = "id";
    public static final String COLUMN2_NAME = "value";
    public static final String COLUMN3_NAME = "info";
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public SQLSource() {
        connect();
        createDB();
    }

    public static void connect() {

        try {
            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("base has been connected!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createDB() {
        try {
            statement = connection.createStatement();
            statement.execute("create table if not exists '" + TABLE_NAME + "'" +
                    " ('" + COLUMN1_NAME + "' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " '" + COLUMN2_NAME + "' numeric," +
                    " '" + COLUMN3_NAME + "' varchar);");
            System.out.println("table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(List<Transaction> transactions) {
        try {
            for (Transaction transaction : transactions) {
                statement.execute("insert into '" + TABLE_NAME + "' ('" + COLUMN2_NAME + "','" + COLUMN3_NAME + "')" +
                        " values ('" + transaction.getValue().toString() + "','" + transaction.getInfo() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    @Override
    public List<Transaction> load() {
        List<Transaction> transactions = new LinkedList<>();
        try {
            resultSet = statement.executeQuery("SELECT * from " + TABLE_NAME);
            while (resultSet.next()) {
                transactions.add(new Transaction(Long.parseLong(resultSet.getString(COLUMN2_NAME)), resultSet.getString(COLUMN3_NAME)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return transactions;
        }
    }


}
