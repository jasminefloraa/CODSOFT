import java.util.Scanner;
class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        if(initialBalance < 0) {
            initialBalance = 0;
        }
        this.balance = initialBalance;
    }
    public double checkBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }
    public void withdraw(double amount) {
        if(amount > 0) {
            if(amount <= balance) {
                balance -= amount;
                System.out.println("Successfully withdrew $" + amount);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Withdrawal amount must be positive!");
        }
    }
}
public class ATM {
    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        sc = new Scanner(System.in);
    }
    private void showMenu() {
        System.out.println("===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }
    public void start() {
        boolean exit = false;

        while(!exit) {
            showMenu();
            System.out.print("Enter your choice: ");
            String input = sc.nextLine().trim();

            switch(input) {
                case "1":
                case "check balance":
                    System.out.println("Your balance is: $" + account.checkBalance());
                    break;

                case "2":
                case "deposit":
                    double depositAmount = getPositiveDouble("Enter amount to deposit: ");
                    account.deposit(depositAmount);
                    break;

                case "3":
                case "withdraw":
                    double withdrawAmount = getPositiveDouble("Enter amount to withdraw: ");
                    account.withdraw(withdrawAmount);
                    break;

                case "4":
                case "exit":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1-4 or option name.");
            }

            System.out.println();
        }
    }
    private double getPositiveDouble(String message) {
        double amount = 0;
        while(true) {
            try {
                System.out.print(message);
                amount = Double.parseDouble(sc.nextLine());
                if(amount <= 0) {
                    System.out.println("Amount must be positive. Try again.");
                } else {
                    break;
                }
            } catch(NumberFormatException e) {
                System.out.println("Invalid input! Enter a numeric value.");
            }
        }
        return amount;
    }
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // initial balance
        ATM atm = new ATM(account);
        atm.start();
    }
}

