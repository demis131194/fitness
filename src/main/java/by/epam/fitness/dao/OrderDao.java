package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao {
    /**
     * Create order.
     *
     * @param order the order
     * @return the order
     * @throws DaoException the dao exception
     */
    Order create(Order order) throws DaoException;

    /**
     * Update boolean.
     *
     * @param order the order
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Order order) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(int orderId) throws DaoException;

    /**
     * Find order.
     *
     * @param orderId the order id
     * @return the order
     * @throws DaoException the dao exception
     */
    Order find(int orderId) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAll() throws DaoException;

    /**
     * Find all with filter list.
     *
     * @param filter the filter
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllWithFilter(Order filter) throws DaoException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllActive() throws DaoException;

    /**
     * Find all active by trainer list.
     *
     * @param trainerId the trainer id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllActiveByTrainer(int trainerId) throws DaoException;

    /**
     * Find all active by client list.
     *
     * @param clientId the client id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllActiveByClient(int clientId) throws DaoException;
}
