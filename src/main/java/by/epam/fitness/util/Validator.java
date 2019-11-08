package by.epam.fitness.util;

import by.epam.fitness.exception.ValidatorExcepton;

public class Validator {
    private static final String LOGIN_REGEX = "^[\\w_]{3,16}$";
    private static final String PASSWORD_REGEX = "^[\\w_]{6,18}$";
    private static final String NAME_LAST_NAME_REGEX = "^[\\p{IsAlphabetic}\\-]{3,20}$";
    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";
    private static final String MONEY_VALUE_REGEX = "^\\d{1,4}(\\.\\d{2})?$";

    private Validator(){}

    public static void checkLoginAndPassword(String login, String password) throws ValidatorExcepton {
        if (!login.matches(LOGIN_REGEX) && !password.matches(PASSWORD_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    public static void checkLogin(String login) throws ValidatorExcepton {
        if (!login.matches(LOGIN_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_LOGIN);
        }
    }

    public static void checkPassword(String password) throws ValidatorExcepton {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_PASSWORD);
        }
    }

    public static void checkPassword(String password, String repeatedPassword) throws ValidatorExcepton {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_PASSWORD);
        }
        if (!password.equals(repeatedPassword)) {
            throw new ValidatorExcepton(ErrMessageKey.PASSWORDS_NOT_EQUAL);
        }
    }

    public static void checkName(String name) throws ValidatorExcepton {
        if (!name.matches(NAME_LAST_NAME_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_NAME);
        }
    }

    public static void checkLastName(String lastName) throws ValidatorExcepton {
        if (!lastName.matches(NAME_LAST_NAME_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_LAST_NAME);
        }
    }

    public static void checkPhone(String phone) throws ValidatorExcepton {
        if (phone!= null && !phone.matches(PHONE_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_PHONE);
        }
    }

    public static void checkMoneyValue(String moneyValue) throws ValidatorExcepton {
        if (!moneyValue.matches(MONEY_VALUE_REGEX)) {
            throw new ValidatorExcepton(ErrMessageKey.INVALID_MONEY_VALUE);
        }
    }
}
