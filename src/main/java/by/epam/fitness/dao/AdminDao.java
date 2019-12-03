package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Admin;

import java.util.List;

/**
 * The interface Admin dao.
 */
public interface AdminDao {

    /**
     * Update boolean.
     *
     * @param admin the admin
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Admin admin) throws DaoException;

    /**
     * Find admin.
     *
     * @param adminId the admin id
     * @return the admin
     * @throws DaoException the dao exception
     */
    Admin find(int adminId) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Admin> findAll() throws DaoException;
}
