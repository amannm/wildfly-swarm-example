package com.amannmalik.service.example;


import com.amannmalik.service.test.RestfulTestGateway;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * Created by Amann Malik (amannmalik@gmail.com) on 6/26/2015.
 */
public class TestExample {

    private static final String BASE_URL = "http://localhost:8080/api";

    @BeforeClass
    public static void startService() {
        try {
            Application.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
        JsonObject messageJson;
        try {
            messageJson = RestfulTestGateway.readExistent(BASE_URL + "/operation");
        } catch (IOException e) {
            Assert.fail(e.toString());
            return;
        }
        Assert.assertTrue(messageJson.containsKey("message"));
        Assert.assertEquals("Hello World", messageJson.getString("message"));
    }
}
