package by.epam.fitness.util;

/**
 * The type Validator.
 */
public class Validator {
    private static final String LOGIN_REGEX = "^[\\w]{3,16}$";
    private static final String PASSWORD_REGEX = "^[\\w]{5,18}$";
    private static final String NAME_LAST_NAME_REGEX = "^[\\p{IsAlphabetic}\\-]{3,20}$";
    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";
    private static final String CARD_NUMBER_REGEX = "^\\d{16}$";
    private static final String CASH_AMOUNT_REGEX = "^\\d{1,4}(\\.\\d{2})?$";
    private static final String EMAIL_REGEX = "^[\\w]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}$";

    private Validator(){}

    /**
     * Check login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    /**
     * Check password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    /**
     * Check name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean checkName(String name) {
        return name.matches(NAME_LAST_NAME_REGEX);
    }

    /**
     * Check last name boolean.
     *
     * @param lastName the last name
     * @return the boolean
     */
    public static boolean checkLastName(String lastName) {
        return lastName.matches(NAME_LAST_NAME_REGEX);
    }

    /**
     * Check phone boolean.
     *
     * @param phone the phone
     * @return the boolean
     */
    public static boolean checkPhone(String phone) {
        return phone == null || phone.matches(PHONE_REGEX);
    }

    /**
     * Check card number boolean.
     *
     * @param cardNumber the card number
     * @return the boolean
     */
    public static boolean checkCardNumber(String cardNumber) {
        return cardNumber.matches(CARD_NUMBER_REGEX);
    }

    /**
     * Check cash amount boolean.
     *
     * @param cashAmount the cash amount
     * @return the boolean
     */
    public static boolean checkCashAmount(String cashAmount) {
        return cashAmount.matches(CASH_AMOUNT_REGEX);
    }

    /**
     * Check email boolean.
     *
     * @param mail the mail
     * @return the boolean
     */
    public static boolean checkEmail(String mail) {
        return mail.matches(EMAIL_REGEX);
    }

    /**
     * Check discount boolean.
     *
     * @param discount the discount
     * @return the boolean
     */
    public static boolean checkDiscount(Integer discount) {
        return discount>=0 && discount <=100;
    }

    /**
     * Check discount level boolean.
     *
     * @param discountLevel the discount level
     * @return the boolean
     */
    public static boolean checkDiscountLevel(Integer discountLevel) {
        return discountLevel>=0 && discountLevel <=3;
    }
}
