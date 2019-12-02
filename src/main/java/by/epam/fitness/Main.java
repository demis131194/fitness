package by.epam.fitness;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {  // FIXME: 30.11.2019 DELETE
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.debug("!debug!");
        logger.info("information!");
    }
}
