package hospital;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }

    public String readPhone(String prompt) {
        while (true) {
            String phone = readRequired(prompt);
            if (phone.matches("\\d{10}")) {
                return phone;
            }
            System.out.println("Phone number must contain exactly 10 digits.");
        }
    }
}
