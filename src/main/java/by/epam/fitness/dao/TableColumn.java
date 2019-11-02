package by.epam.fitness.dao;

public class TableColumn {

    public static final String USERS_ID = "id";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_ROLE = "role";
    public static final String USERS_ACTIVE = "active";

    public static final String ADMINS_ID = "adminId";
    public static final String ADMINS_NAME = "name";
    public static final String ADMINS_LAST_NAME = "lastName";

    public static final String TRAINER_ID = "trainerId";
    public static final String TRAINER_NAME = "name";
    public static final String TRAINER_LAST_NAME = "lastName";
    public static final String TRAINER_REGISTER_DATE = "registerDate";
    public static final String TRAINER_PHONE = "phone";
    public static final String TRAINER_ACTIVE = "active";

    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_LAST_NAME = "lastName";
    public static final String CLIENT_REGISTER_DATE = "registerDate";
    public static final String CLIENT_DISCOUNT = "discount";
    public static final String CLIENT_DISCOUNT_LEVEL = "discountLevel";
    public static final String CLIENT_PHONE = "phone";
    public static final String CLIENT_CASH = "cash";
    public static final String CLIENT_ACTIVE = "active";

    public static final String ORDERS_ID = "id";
    public static final String ORDERS_CLIENT_ID = "clientId";
    public static final String ORDERS_TRAINER_ID = "trainerId";
    public static final String ORDERS_REGISTER_DATE = "registerDate";
    public static final String ORDERS_EXERCISES = "exercises";
    public static final String ORDERS_NUTRITION = "nutrition";
    public static final String ORDERS_START_DATE = "startDate";
    public static final String ORDERS_END_DATE = "endDate";
    public static final String ORDERS_PRICE = "price";
    public static final String ORDERS_CLIENT_COMMENT = "clientComment";
    public static final String ORDERS_STATUS = "status";
    public static final String ORDERS_ACCEPT = "accept";
    public static final String ORDERS_ACTIVE = "active";

    public static final String COMMENT_ID = "id";
    public static final String COMMENT_CLIENT_ID = "userId";
    public static final String COMMENT_TRAINER_ID = "trainerId";
    public static final String COMMENT_REGISTER_DATE = "registerDate";
    public static final String COMMENT_COMMENT = "comment";
    public static final String COMMENT_ACTIVE = "active";


    private TableColumn(){}
}
