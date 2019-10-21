package by.epam.fitness.dao;

import by.epam.fitness.model.User;

public interface UserDao {

    User create(User user);
    boolean update(User user);
    boolean delete(int id);
    User findByNameAndLastName(String name, String lastName);
}
