package by.epam.fitness.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Password encoder.
 */
public class PasswordEncoder {
    private static Logger logger = LogManager.getLogger(PasswordEncoder.class);
    private static final String ALGORITHM = "MD5";
    private static final String HEX_FORMAT = "%02X";

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
    }

    private PasswordEncoder(){}

    /**
     * Encode string.
     *
     * @param line the line
     * @return the string
     */
    public static String encode(String line) {
        byte[] bytes = md5.digest(line.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format(HEX_FORMAT, b));
        }
        return builder.toString();
    }
}
