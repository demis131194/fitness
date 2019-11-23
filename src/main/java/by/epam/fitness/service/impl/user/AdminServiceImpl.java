package by.epam.fitness.service.impl.user;

import by.epam.fitness.dao.AdminDao;
import by.epam.fitness.dao.impl.AdminDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Admin;
import by.epam.fitness.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static AdminService adminService = new AdminServiceImpl();

    private AdminDao adminDao = AdminDaoImpl.getInstance();

    private AdminServiceImpl() {
    }

    public static AdminService getInstance() {
        return adminService;
    }

    @Override
    public boolean update(Admin admin) throws ServiceException {
        boolean isUpdated;
        try {
            adminDao.update(admin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean updateAdminPassword(int adminId, String password) throws ServiceException {
        boolean isUpdated;
        try {
            adminDao.updateAdminPassword(adminId, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Admin find(int adminId) throws ServiceException {
        Admin admin;
        try {
            admin = adminDao.find(adminId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() throws ServiceException {
        List<Admin> admins;
        try {
            admins = adminDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return admins;
    }
}
