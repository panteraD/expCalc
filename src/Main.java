import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * ѕростой консольный трекер бюджета<br/>
 * <p/>
 * ћожно вводить два вида команд:<br/>
 * 1) n - измен€ет баланс на указанное число n, n может быть отрицательным<br/>
 * 2) balance - выводит на экран ваш баланс<br/>
 * <p/>
 * P.S ћожете добавить на свое усмотрение фичи (например какой-нить алерт при отрицательном балансе)<br/>
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Transaction> transactions = new LinkedList<>();
        // Source source = new XMLSource();
        Source source = new SQLSource();

        while (true) {
            String inputString = "";
            String[] strings;
            try {
                inputString = reader.readLine();
                strings = inputString.split(" ");

                long delta = Long.parseLong(strings[0]);

                if (strings.length > 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 1; i < strings.length; i++) {
                        stringBuilder.append(strings[i]);
                    }
                    transactions.add(new Transaction(delta, stringBuilder.toString()));
                } else {
                    transactions.add(new Transaction(delta, null));
                }

            } catch (NumberFormatException nfe) {
                switch (inputString.toLowerCase()) {
                    case "balance":
                        Printer.printBalance(transactions);
                        break;
                    case "exit":
                        source.close();
                        System.exit(0);
                        break;
                    case "revenues":
                        Printer.printRevenues(transactions);
                        break;
                    case "expenses":
                        Printer.printExpenes(transactions);
                        break;
                    case "save":
                        source.save(transactions);
                        break;
                    case "load":
                        transactions = source.load();
                        break;
                    default:
                        System.out.println(Printer.HELP);
                        break;
                }
            }
        }
    }
}
