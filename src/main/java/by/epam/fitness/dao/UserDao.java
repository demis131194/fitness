package by.epam.fitness.dao;

import by.epam.fitness.model.User;

public interface UserDao {

    User create(User user);
    User update(User user);
    boolean delete(int id);
    User get(int id);
}
