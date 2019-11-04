package by.epam.fitness.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    private static Logger logger = LogManager.getLogger(Encoder.class);
    private static final String algorithm = "MD5";

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
    }

    private Encoder(){}

    public static String encode(String line) {
        byte[] bytes = md5.digest(line.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }
}
