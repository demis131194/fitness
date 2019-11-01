package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Admin;

import java.util.List;

public interface AdminService {

    boolean updateAdmin(Admin admin) throws ServiceException;
    boolean updateAdminPassword(int adminId, String password) throws ServiceException;
    Admin find(int adminId) throws ServiceException;
    List<Admin> findAll() throws ServiceException;
}
