import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * ������� ���������� ������ �������<br/>
 * <p/>
 * ����� ������� ��� ���� ������:<br/>
 * 1) n - �������� ������ �� ��������� ����� n, n ����� ���� �������������<br/>
 * 2) balance - ������� �� ����� ��� ������<br/>
 * <p/>
 * P.S ������ �������� �� ���� ���������� ���� (�������� �����-���� ����� ��� ������������� �������)<br/>
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Transaction> transactions = new LinkedList<>();
        Source source = new XMLSource();

        while (true) {
            String inputString = "";
            String[] strings;
            try {
                inputString = reader.readLine();
                strings = inputString.split(" ");

                long delta = Long.parseLong(strings[0]);

                if (strings.length > 1) {
                    transactions.add(new Transaction(delta, strings[1]));
                } else {
                    transactions.add(new Transaction(delta, null));
                }

            } catch (NumberFormatException nfe) {
                switch (inputString.toLowerCase()) {
                    case "balance":
                        Printer.printBalance(transactions);
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    case "revenues":
                        Printer.printRevenues(transactions);
                        break;
                    case "expenses":
                        Printer.printExpenes(transactions);
                        break;
                    case "save":
                        source.saveToFile(transactions);
                        break;
                    case "load":
                        transactions = source.loadFromFile();
                        break;
                    default:
                        System.out.println(Printer.HELP);
                        break;
                }


            }
        }

    }

}
