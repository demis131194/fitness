package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Trainer;

import java.util.List;

/**
 * The interface Trainer dao.
 */
public interface TrainerDao {

    /**
     * Create trainer.
     *
     * @param trainer the trainer
     * @return the trainer
     * @throws DaoException the dao exception
     */
    Trainer create(Trainer trainer) throws DaoException;

    /**
     * Update boolean.
     *
     * @param trainer the trainer
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Trainer trainer) throws DaoException;

    /**
     * Find trainer.
     *
     * @param trainerId the trainer id
     * @return the trainer
     * @throws DaoException the dao exception
     */
    Trainer find(int trainerId) throws DaoException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Trainer> findAllActive() throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Trainer> findAll() throws DaoException;

    /**
     * Find all active by name and last name list.
     *
     * @param name    the name
     * @param lastNme the last nme
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Trainer> findAllActiveByNameAndLastName(String name, String lastNme) throws DaoException;
}
