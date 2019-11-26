package by.epam.fitness.util;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ValidatorTest {

    @Test
    public void testCheckLogin() {
        boolean isValid = Validator.checkLogin("qwerty123");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckLogin1() {
        boolean isValid = Validator.checkLogin("qwe ty123");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckLogin2() {
        boolean isValid = Validator.checkLogin("qwety123#");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckPassword() {
        boolean isValid = Validator.checkPassword("password341");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckPassword1() {
        boolean isValid = Validator.checkPassword("pas<sword341");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckPassword2() {
        boolean isValid = Validator.checkPassword("pa41");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckPassword3() {
        boolean isValid = Validator.checkPassword("pa41 dssa");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckName() {
        boolean isValid = Validator.checkName("Name");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckName1() {
        boolean isValid = Validator.checkName("Nam e");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckName2() {
        boolean isValid = Validator.checkName("Name&");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckLastName() {
        boolean isValid = Validator.checkLastName("LastName");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckLastName1() {
        boolean isValid = Validator.checkLastName("Last Name");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckLastName2() {
        boolean isValid = Validator.checkLastName("LastName!");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckPhone1() {
        boolean isValid = Validator.checkPhone("3414578");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testCheckPhone2() {
        boolean isValid = Validator.checkPhone("+3414578");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckPhone1() {
        boolean isValid = Validator.checkPhone("!3414578");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckPhone2() {
        boolean isValid = Validator.checkPhone("348");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckCardNumber() {
        boolean isValid = Validator.checkCardNumber("1111222244445555");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckCardNumber1() {
        boolean isValid = Validator.checkCardNumber("11112272244445555");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckCardNumber2() {
        boolean isValid = Validator.checkCardNumber("11112t2244445555");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckCashAmount1() {
        boolean isValid = Validator.checkCashAmount("100");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testCheckCashAmount2() {
        boolean isValid = Validator.checkCashAmount("100.15");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckCashAmount1() {
        boolean isValid = Validator.checkCashAmount("100555");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckCashAmount2() {
        boolean isValid = Validator.checkCashAmount("100.341");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckCashAmount3() {
        boolean isValid = Validator.checkCashAmount("1030.34.5");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckCashAmount4() {
        boolean isValid = Validator.checkCashAmount("1t3");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckEmail() {
        boolean isValid = Validator.checkEmail("email@email.com");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckEmail1() {
        boolean isValid = Validator.checkEmail("emailemail.com");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckEmail2() {
        boolean isValid = Validator.checkEmail("email@email.comqw");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckEmail3() {
        boolean isValid = Validator.checkEmail("ema!l@email.com");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckDiscount1() {
        boolean isValid = Validator.checkDiscount(100);
        Assert.assertTrue(isValid);
    }

    @Test
    public void testCheckDiscount2() {
        boolean isValid = Validator.checkDiscount(0);
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckDiscount1() {
        boolean isValid = Validator.checkDiscount(-1);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testFalseCheckDiscount2() {
        boolean isValid = Validator.checkDiscount(101);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckDiscountLevel1() {
        boolean isValid = Validator.checkDiscountLevel(0);
        Assert.assertTrue(isValid);
    }

    @Test
    public void testCheckDiscountLevel2() {
        boolean isValid = Validator.checkDiscountLevel(3);
        Assert.assertTrue(isValid);
    }

    @Test
    public void testFalseCheckDiscountLevel1() {
        boolean isValid = Validator.checkDiscountLevel(4);
        Assert.assertFalse(isValid);
    }
}