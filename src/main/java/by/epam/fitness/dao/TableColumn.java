package by.epam.fitness.dao;

public class TableColumn {

    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_ROLE = "role";

    public static final String ADMINS_ID = "adminId";
    public static final String ADMINS_NAME = "name";
    public static final String ADMINS_LAST_NAME = "lastName";

    public static final String ORDERS_ID = "id";
    public static final String ORDERS_USER_ID = "userId";
    public static final String ORDERS_TRAINER_ID = "trainerId";
    public static final String ORDERS_REGISTER_DATE = "registerDate";
    public static final String ORDERS_DESCRIPTION = "description";
    public static final String ORDERS_ACTIVE = "active";

    public static final String ASSIGNMENTS_ID = "id";
    public static final String ASSIGNMENTS_ORDER_ID = "orderId";
    public static final String ASSIGNMENTS_USER_ID = "userId";
    public static final String ASSIGNMENTS_TRAINER_ID = "trainerId";
    public static final String ASSIGNMENTS_REGISTER_DATE = "registerDate";
    public static final String ASSIGNMENTS_EXERCISES = "exercises";
    public static final String ASSIGNMENTS_NUTRITION = "nutrition";
    public static final String ASSIGNMENTS_START_DATE = "startDate";
    public static final String ASSIGNMENTS_END_DATE = "endDate";
    public static final String ASSIGNMENTS_ACTIVE = "active";
    public static final String ASSIGNMENTS_PRICE = "price";
    public static final String ASSIGNMENTS_ACCEPT = "accept";

    public static final String COMMENT_ID = "id";
    public static final String COMMENT_USER_ID = "userId";
    public static final String COMMENT_TRAINER_ID = "trainerId";
    public static final String COMMENT_REGISTER_DATE = "registerDate";
    public static final String COMMENT_COMMENT = "comment";
    public static final String COMMENT_ACTIVE = "active";


    private TableColumn(){}
}
