package by.epam.fitness.util;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PasswordEncoderTest {
    private static final String ENCODED_LINE = "password102QD2l";
    private static final String EXPECTED_ENCODED_LINE = "1DEAFAC557A11E1FA46A648CA3B16D70";

    @Test
    public void testEncode() {
        String actualEncodedLine =  PasswordEncoder.encode(ENCODED_LINE);
        Assert.assertEquals(actualEncodedLine, EXPECTED_ENCODED_LINE);
    }
}