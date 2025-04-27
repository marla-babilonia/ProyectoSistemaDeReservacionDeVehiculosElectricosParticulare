package information;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TransactionsHandler {

    private static final Scanner scanner = new Scanner(System.in);

    // LinkedList for transactions
    private static final LinkedList<Transaction> transactions = new LinkedList<>();

    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction added successfully:\n" + transaction);
    }

    public static void removeTransaction() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to remove.");
            return;
        }

        showAllTransactions();

        System.out.print("Select transaction number to remove: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > transactions.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Transaction removed = transactions.remove(choice - 1);
        System.out.println("Transaction removed:\n" + removed);
    }

    public static void showAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("All Transactions:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    public static List<Transaction> getAllTransactions() {
        return new LinkedList<>(transactions); // Return a copy
    }
}
