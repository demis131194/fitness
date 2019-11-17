package by.epam.fitness.service.impl.user;

import by.epam.fitness.dao.TrainerDao;
import by.epam.fitness.dao.impl.TrainerDaiImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    private static Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    private static TrainerService trainerService = new TrainerServiceImpl();

    private TrainerDao trainerDao = TrainerDaiImpl.getInstance();

    private TrainerServiceImpl() {
    }

    public static TrainerService getInstance() {
        return trainerService;
    }

    @Override
    public Trainer create(Trainer trainer) throws ServiceException {
        Trainer createdTrainer;
        try {
            trainer.setPassword(PasswordEncoder.encode(trainer.getPassword()));
            createdTrainer = trainerDao.create(trainer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdTrainer;
    }

    @Override
    public boolean update(Trainer trainer) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = trainerDao.update(trainer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public Trainer find(int trainerId) throws ServiceException {
        Trainer trainer;
        try {
            trainer = trainerDao.find(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainer;
    }

    @Override
    public List<Trainer> findAllActive() throws ServiceException {
        List<Trainer> trainers;
        try {
            trainers = trainerDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainers;
    }

    @Override
    public List<Trainer> findAll() throws ServiceException {
        List<Trainer> trainers;
        try {
            trainers = trainerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainers;
    }

    @Override
    public List<Trainer> findAllActiveByNameAndLastName(String name, String lastName) throws ServiceException {
        List<Trainer> trainers;
        try {
            trainers = trainerDao.findAllActiveByNameAndLastName(name, lastName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainers;
    }
}
