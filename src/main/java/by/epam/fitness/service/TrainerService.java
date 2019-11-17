package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;

import java.util.List;

public interface TrainerService {

    Trainer create(Trainer trainer) throws ServiceException;
    boolean update(Trainer trainer) throws ServiceException;
    Trainer find(int trainerId) throws ServiceException;
    List<Trainer> findAllActive() throws ServiceException;
    List<Trainer> findAll() throws ServiceException;
    List<Trainer> findAllActiveByNameAndLastName(String name, String lastName) throws ServiceException;
}
