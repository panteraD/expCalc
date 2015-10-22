import java.util.List;


public class Printer {

    public static final String HELP = "Available commands: balance, exit, revenues, expenses, save, load";

    public static void printBalance(List<Transaction> transactions) {
        long balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getValue();
        }
        System.out.println(balance);
    }

    public static void printRevenues(List<Transaction> transactions) {
        if (transactions != null) {
            System.out.println("revenues:");
            for (Transaction t : transactions) {
                if (t.getValue() > 0) {
                    System.out.printf("%d %s\n", t.getValue(), t.getInfo());
                }
            }
        }
    }

    public static void printExpenes(List<Transaction> transactions) {
        if (transactions != null) {
            System.out.println("expenses:");
            for (Transaction t : transactions) {
                if (t.getValue() < 0) {
                    System.out.printf("%d %s\n", t.getValue(), t.getInfo());
                }
            }
        }
    }
}
