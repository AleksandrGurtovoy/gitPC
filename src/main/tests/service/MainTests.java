package service;

import org.junit.Assert;
import org.junit.Test;

public class MainTests {

    @Test(expected = NullPointerException.class)
    public void getUser_fail() {
        Assert.assertNotNull(Main.getInstance().getUser());
    }
    @Test
    public void getInstance_fail(){
        Assert.assertNull(Main.getInstance());
    }
}
