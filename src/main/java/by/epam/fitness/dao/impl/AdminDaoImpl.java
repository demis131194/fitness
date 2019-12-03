package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.AdminDao;
import by.epam.fitness.dao.TableColumnName;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Admin;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Admin dao.
 */
public class AdminDaoImpl implements AdminDao {
    private static Logger logger = LogManager.getLogger(AdminDaoImpl.class);
    private static final String USERS_UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";
    private static final String ADMINS_UPDATE_QUERY = "UPDATE admins SET name = IFNULL(?, name), lastName = IFNULL(?, lastName), mail = IFNULL(?, mail) WHERE adminId = ?";
    private static final String FIND_QUERY = "SELECT adminId, name, lastName, mail FROM admins WHERE adminId = ?";
    private static final String FIND_ALL_QUERY = "SELECT adminId, name, lastName, mail FROM admins";

    private static AdminDao adminDao = new AdminDaoImpl();;

    private AdminDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AdminDao getInstance() {
        return adminDao;
    }

    @Override
    public boolean update(Admin admin) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADMINS_UPDATE_QUERY)) {
            if (admin.getName() != null) {
                statement.setString(1, admin.getName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (admin.getLastName() != null) {
                statement.setString(2, admin.getLastName());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (admin.getMail() != null) {
                statement.setString(3, admin.getMail());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            statement.setInt(4, admin.getId());
            isUpdated = statement.executeUpdate() == 1;
            logger.debug("UpdateAdmin, admin - {}", admin);
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
        admin.setId(resultSet.getInt(TableColumnName.ADMINS_ID));
        admin.setName(resultSet.getString(TableColumnName.ADMINS_NAME));
        admin.setLastName(resultSet.getString(TableColumnName.ADMINS_LAST_NAME));
        admin.setMail(resultSet.getString(TableColumnName.ADMINS_MAIL));
        return admin;
    }
}
