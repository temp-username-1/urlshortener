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
import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlControllerIntegrationTest {

    private static final String VALID_URL = "https://www.google.com/";
    private static final String INVALID_URL = "h^ttps://www.google.com/";

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
                .body("redirections", Matchers.is(0));
    }

    @Test
    public void postInvalidUrlShouldReturn400() {
        UrlDto dto = UrlDto.builder()
                .url(INVALID_URL)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/urls")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void findByRetrievalKeyOfExistingUrlShouldReturn200() {
        UrlDto dto = UrlDto.builder()
                .url(VALID_URL)
                .build();

        String retrievalKey = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/urls")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("url", Matchers.is(VALID_URL))
                .body("retrievalKey", Matchers.notNullValue())
                .body("redirections", Matchers.is(0))
                .extract()
                .path("retrievalKey");

        when()
                .get("/urls/" + retrievalKey)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("url", Matchers.is(VALID_URL))
                .body("retrievalKey", Matchers.notNullValue())
                .body("redirections", Matchers.is(0));
    }

    @Test
    public void findByNonExistingRetrievalKeyShouldReturn404() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/urls/invalidKey")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
