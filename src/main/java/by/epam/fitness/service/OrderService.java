package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Create order.
     *
     * @param order the order
     * @return the order
     * @throws ServiceException the service exception
     */
    Order create(Order order) throws ServiceException;

    /**
     * Update boolean.
     *
     * @param order the order
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Order order) throws ServiceException;

    /**
     * Delete boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(int orderId) throws ServiceException;

    /**
     * Find order.
     *
     * @param orderId the order id
     * @return the order
     * @throws ServiceException the service exception
     */
    Order find(int orderId) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAll() throws ServiceException;

    /**
     * Find all with filter list.
     *
     * @param filter the filter
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllWithFilter(Order filter) throws ServiceException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllActive() throws ServiceException;

    /**
     * Find all active by trainer list.
     *
     * @param trainerId the trainer id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllActiveByTrainer(int trainerId) throws ServiceException;

    /**
     * Find all active by client list.
     *
     * @param clientId the client id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllActiveByClient(int clientId) throws ServiceException;
}
