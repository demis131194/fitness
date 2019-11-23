package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Admin;

import java.util.List;

public interface AdminDao {

    boolean update(Admin admin) throws DaoException;
    boolean updateAdminPassword(int adminId, String password) throws DaoException;
    Admin find(int adminId) throws DaoException;
    List<Admin> findAll() throws DaoException;
}
