package by.epam.fitness.util;

public class Validator {
    private static final String LOGIN_REGEX = "^[\\w]{3,16}$";
    private static final String PASSWORD_REGEX = "^[\\w]{5,18}$";
    private static final String NAME_LAST_NAME_REGEX = "^[\\p{IsAlphabetic}\\-]{3,20}$";
    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";
    private static final String CARD_NUMBER_REGEX = "^\\d{16}$";
    private static final String CASH_AMOUNT_REGEX = "^\\d{1,4}(\\.\\d{2})?$";
    private static final String EMAIL_REGEX = "^[\\w]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}$";

    private Validator(){}

    public static boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
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

    public static boolean checkEmail(String mail) {
        return mail.matches(EMAIL_REGEX);
    }

    public static boolean checkDiscount(Integer discount) {
        return discount>=0 && discount <=100;
    }

    public static boolean checkDiscountLevel(Integer discountLevel) {
        return discountLevel>=0 && discountLevel <=3;
    }
}
