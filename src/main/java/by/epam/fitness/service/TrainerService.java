package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;

import java.util.List;

/**
 * The interface Trainer service.
 */
public interface TrainerService {

    /**
     * Create trainer.
     *
     * @param trainer the trainer
     * @return the trainer
     * @throws ServiceException the service exception
     */
    Trainer create(Trainer trainer) throws ServiceException;

    /**
     * Update boolean.
     *
     * @param trainer the trainer
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Trainer trainer) throws ServiceException;

    /**
     * Find trainer.
     *
     * @param trainerId the trainer id
     * @return the trainer
     * @throws ServiceException the service exception
     */
    Trainer find(int trainerId) throws ServiceException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Trainer> findAllActive() throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Trainer> findAll() throws ServiceException;

    /**
     * Find all active by name and last name list.
     *
     * @param name     the name
     * @param lastName the last name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Trainer> findAllActiveByNameAndLastName(String name, String lastName) throws ServiceException;
}
