package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.AdminDao;
import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Admin;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private static Logger logger = LogManager.getLogger(AdminDaoImpl.class);
    private static final String USERS_UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";
    private static final String ADMINS_UPDATE_QUERY = "UPDATE admins SET name = ?, lastName = ? WHERE adminId = ?";
    private static final String FIND_QUERY = "SELECT adminId, name, lastName FROM admins WHERE adminId = ?";
    private static final String FIND_ALL_QUERY = "SELECT adminId, name, lastName FROM admins";

    private static AdminDao adminDao;

    private AdminDaoImpl() {
    }

    public static AdminDao getInstance() {
        if (adminDao == null) {
            adminDao = new AdminDaoImpl();
            logger.debug("AdminDao created");
        }
        return adminDao;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADMINS_UPDATE_QUERY)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getLastName());
            statement.setInt(3, admin.getId());
            isUpdated = statement.executeUpdate() == 1;
            logger.debug("UpdateAdmin, admin - {}", admin);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateAdminPassword(int adminId, String password) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(USERS_UPDATE_PASSWORD_QUERY)) {
            statement.setString(1, password);
            statement.setInt(2, adminId);
            isUpdated = statement.executeUpdate() == 1;
            logger.debug("UpdateAdmin, idAdmin - {}", adminId);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public Admin find(int adminId) throws DaoException {
        Admin admin = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                admin = getAdminFromResultSet(resultSet);
            }

            logger.debug("Find, admin - {}", admin);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() throws DaoException {
        List<Admin> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Admin admin = getAdminFromResultSet(resultSet);
                result.add(admin);
            }

            logger.debug("FindAll, admins - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private Admin getAdminFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt(TableColumn.ADMINS_ID));
        admin.setName(resultSet.getString(TableColumn.ADMINS_NAME));
        admin.setLastName(resultSet.getString(TableColumn.ADMINS_LAST_NAME));
        return admin;
    }
}
