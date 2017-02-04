package me.giannists.rest;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UrlControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Before
    public void init(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;
    }



}
