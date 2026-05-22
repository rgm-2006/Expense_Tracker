import java.io.*;
import java.util.*;

class Expense {
    String date;
    String category;
    double amount;
    String description;

    Expense(String date, String category, double amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String toString() {
        return date + "," + category + "," + amount + "," + description;
    }
}

public class ExpenseTracker {

    static final String FILE_NAME = "expenses.txt";

    static void addExpense() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        Expense expense = new Expense(date, category, amount, description);

        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(expense.toString() + "\n");
        fw.close();

        System.out.println("Expense added successfully!");
    }

    static void viewExpenses() throws Exception {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No expenses found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        System.out.println("\nDate\tCategory\tAmount\tDescription");
        System.out.println("-------------------------------------------");

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println(data[0] + "\t" + data[1] + "\t" + data[2] + "\t" + data[3]);
        }
        br.close();
    }

    static void totalExpense() throws Exception {
        File file = new File(FILE_NAME);
        double total = 0;

        if (!file.exists()) {
            System.out.println("No expenses found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            total += Double.parseDouble(data[2]);
        }
        br.close();

        System.out.println("Total Expense = ₹" + total);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== EXPENSE TRACKER ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total Expense");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    totalExpense();
                    break;
                case 4:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
