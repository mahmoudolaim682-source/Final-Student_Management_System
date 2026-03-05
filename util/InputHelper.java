package util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner input = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    public static int readIntSafe(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(input.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    public static double readDoubleSafe(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(input.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid grade. Please try again.");
            }
        }
    }

    public static boolean confirm(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = input.nextLine().trim().toUpperCase();
            if (response.equals("Y"))
                return true;
            if (response.equals("N"))
                return false;
            System.out.println("Please enter Y for Yes or N for No.");
        }
    }

    public static void close() {
        input.close();
    }
}
