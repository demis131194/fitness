package by.epam.fitness.util;

public class Validator {
    private static final String LOGIN_REGEX = "^[\\w_]{3,16}$";
    private static final String PASSWORD_REGEX = "^[\\w_]{5,18}$";
    private static final String NAME_LAST_NAME_REGEX = "^[\\p{IsAlphabetic}\\-]{3,20}$";
    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";
    private static final String CARD_NUMBER_REGEX = "^\\d{16}$";
    private static final String CASH_AMOUNT_REGEX = "^\\d{1,4}(\\.\\d{2})?$";

    private Validator(){}

    public static boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean checkPassword(String password, String repeatedPassword) {
        return checkPassword(password) && password.equals(repeatedPassword);
    }

    public static boolean checkName(String name) {
        return name.matches(NAME_LAST_NAME_REGEX);
    }

    public static boolean checkLastName(String lastName) {
        return lastName.matches(NAME_LAST_NAME_REGEX);
    }

    public static boolean checkPhone(String phone) {
        return phone == null || phone.matches(PHONE_REGEX);
    }

    public static boolean checkCardNumber(String cardNumber) {
        return cardNumber.matches(CARD_NUMBER_REGEX);
    }

    public static boolean checkCashAmount(String cashAmount) {
        return cashAmount.matches(CASH_AMOUNT_REGEX);
    }
}
