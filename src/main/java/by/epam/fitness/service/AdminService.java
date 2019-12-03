package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Admin;

import java.util.List;

/**
 * The interface Admin service.
 */
public interface AdminService {

    /**
     * Update boolean.
     *
     * @param admin the admin
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Admin admin) throws ServiceException;

    /**
     * Find admin.
     *
     * @param adminId the admin id
     * @return the admin
     * @throws ServiceException the service exception
     */
    Admin find(int adminId) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Admin> findAll() throws ServiceException;
}
