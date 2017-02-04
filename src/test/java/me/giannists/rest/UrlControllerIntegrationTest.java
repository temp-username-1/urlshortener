package me.giannists.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import me.giannists.rest.dto.UrlDto;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlControllerIntegrationTest {

    private static final String VALID_URL = "https://www.google.com/";

    @LocalServerPort
    int port;

    @Before
    public void init(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;
    }

    @Test
    public void postNewUrlShouldReturn200() {
        UrlDto dto = UrlDto.builder()
                .url(VALID_URL)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/urls")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("url", Matchers.is(VALID_URL))
                .body("retrievalKey", Matchers.notNullValue())
                .body("clicks", Matchers.is(0));
    }

}
