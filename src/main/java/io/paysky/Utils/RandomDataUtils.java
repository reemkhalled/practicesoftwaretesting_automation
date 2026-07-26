package io.paysky.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class RandomDataUtils {

    private static final Random random = new Random();

    private static final String[] FIRST_NAME = {
            "Ahmed", "Mohamed", "Heba", "Eman", "Alaa", "Youssef", "Omar", "Layla"
    };

    private static final String[] LAST_NAME = {
            "Hassan", "Ali", "Ibrahim", "Mostafa", "Adel", "Kamal", "Khaled"
    };

    private static final String[] CITY = {
            "Cairo", "Paris", "Berlin", "Aman", "Berlin" , "Riyad"
    };

    private static final String[] COUNTRY = {
            "Egypt", "France", "Germany", "Jordan", "Lebanon" , "Saudi Arabia"
    };

    public static String generateFirstName() {
        return FIRST_NAME[random.nextInt(FIRST_NAME.length)];
    }

    public static String generateLastName() {
        return LAST_NAME[random.nextInt(LAST_NAME.length)];
    }

    public static String generateEmail(String firstName, String lastName) {
        String uniquePart = UUID.randomUUID().toString().substring(0, 6);
        return (firstName + "." + lastName + "." + uniquePart + "@test.com").toLowerCase();
    }

    public static String generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";

        StringBuilder password = new StringBuilder();
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        String all = upper + lower + digits + special;
        for (int i = 0; i < 6; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }
        return password.toString();
    }

    public static String generatePhoneNumber() {
        StringBuilder phone = new StringBuilder("01");
        for (int i = 0; i < 9; i++) {
            phone.append(random.nextInt(10));
        }
        return phone.toString();
    }

    public static String generateDob() {
        int year = 1980 + random.nextInt(20); // من 1980 لـ 1999
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);
        LocalDate date = LocalDate.of(year, month, day);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String generateStreetAddress() {
        int number = 1 + random.nextInt(200);
        return number + " Random Street";
    }

    public static String generateCity() {
        return CITY[random.nextInt(CITY.length)];
    }

    public static String generateState() {
        return CITY[random.nextInt(CITY.length)];
    }

    public static String generateCountry() {
        return COUNTRY[random.nextInt(COUNTRY.length)];
    }

    public static String generatePostalCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public static String generateHouseNumber() {
        int number = 1 + random.nextInt(200);
        return String.valueOf(number);
    }
}
