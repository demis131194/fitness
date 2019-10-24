package by.epam.fitness;

import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.UserDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws DaoException, ServiceException {

        UserDao userDao = UserDaoImpl.getInstance();

        User user = userDao.findByLogin("vova");
        System.out.println(user);
        System.out.println("--------");
        User user1 = userDao.findByLoginAndPassword("vova", "vova");
        System.out.println(user1);

    }
}
