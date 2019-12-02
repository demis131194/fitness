package by.epam.fitness.pool;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class ConnectionPoolTest {

    @BeforeClass
    public void initPoll() {
        ConnectionPool.initPool();
    }

    @Test
    public void connectionPoolTest() {
        ConnectionPool instance = ConnectionPool.getInstance();
        Assert.assertEquals(instance.size(), 8);
    }

}