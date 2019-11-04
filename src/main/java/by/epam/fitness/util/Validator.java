package by.epam.fitness.util;

import java.math.BigDecimal;

public class Validator {
    private static final String LOGIN_REGEX = "^[\\w_]{3,16}$";
    private static final String PASSWORD_REGEX = "^[\\w_]{6,18}$";
    private static final String NAME_LAST_NAME_REGEX = "^[\\p{IsAlphabetic}\\-]{3,20}$";
    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";
    private static final String MONEY_VALUE_REGEX = "^\\d{1,4}(\\.\\d{2})?$";

    private Validator(){}

    public static  boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static  boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static  boolean checkNameOrLastName(String password) {
        return password.matches(NAME_LAST_NAME_REGEX);
    }

    public static  boolean checkPhone(String password) {
        return password.matches(PHONE_REGEX);
    }

    public static  boolean checkMoneyValue(String moneyValue) {
        return moneyValue.matches(MONEY_VALUE_REGEX);
    }
}
