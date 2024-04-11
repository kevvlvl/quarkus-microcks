package org.cars;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CarsMockApiIT {

    @ConfigProperty(name= "quarkus.microcks.default.http")
    String microcksContainerUrl;

    /**
     * Test the mocked API loaded in Microcks using its open api contract.
     * This is to show how we can write tests to call mocked services using their openapi contracts to perform integration testing
     */
    @Test
    public void testMockedCarApi() throws IOException, InterruptedException {

        given()
                .log().all()
                .pathParams("owner", "kev")
                .urlEncodingEnabled(false)
                .accept(ContentType.JSON)
                .when().get(microcksContainerUrl + "/rest/OpenAPI+Car+API/1.0.0/owner/{owner}/car")
                .then()
                .assertThat()
                .statusCode(is(200))
                .and()
                .contentType(ContentType.JSON);
    }
}
