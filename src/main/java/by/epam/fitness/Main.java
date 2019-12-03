package by.epam.fitness;

import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.impl.CommentDaoImpl;
import by.epam.fitness.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {  // FIXME: 30.11.2019 DELETE
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        CommentDao instance = CommentDaoImpl.getInstance();

        try {
            int count = instance.countAll(null);
            System.out.println(count);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
