package by.epam.fitness.model;

import by.epam.fitness.util.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * The type Fitness card.
 */
public class FitnessCard {
    private static Logger logger = LogManager.getLogger(FitnessCard.class);

    /**
     * The constant FITNESS_CARD.
     */
    public static Card FITNESS_CARD;

    private static final String CARD_PROPERTY_PATH = "card/card.properties";
    private static final String CARD_NUMBER_KEY = "card.number";

    /**
     * Init.
     */
    public static void init() {
        FITNESS_CARD = new Card();
        Properties properties = PropertyLoader.loadProperty(CARD_PROPERTY_PATH);
        if (properties.containsKey(CARD_NUMBER_KEY)) {
            FITNESS_CARD.setCardNumber(properties.getProperty(CARD_NUMBER_KEY));
        } else {
            logger.fatal("Fitness card name missing!");
        }
    }
}
