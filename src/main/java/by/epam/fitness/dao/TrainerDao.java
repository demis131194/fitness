package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Trainer;

import java.util.List;

public interface TrainerDao {

    Trainer create(Trainer client) throws DaoException;
    boolean update(Trainer client) throws DaoException;
    boolean delete(int trainerId) throws DaoException;
    Trainer find(int trainerId) throws DaoException;
    List<Trainer> findAllActive() throws DaoException;
    List<Trainer> findAll() throws DaoException;
    List<Trainer> findAllActiveByName(String name) throws DaoException;
    List<Trainer> findAllActiveByLastName(String lastName) throws DaoException;
}
