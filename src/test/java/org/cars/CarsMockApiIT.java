package org.cars;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CarsMockApiIT {

    @ConfigProperty(name= "quarkus.microcks.default.http")
    String microcksContainerUrl;

    /**
     * Test the a mocked API loaded in Microcks using its open api contract.
     * This can be used to mock dependencies instead of injecting and managing mocks programmatically
     */
    @Test
    public void testMockedCarApi() {

        given()
                .when().get(microcksContainerUrl + "/rest/OpenAPI+Car+API/1.0.0/owner/kevv/car")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
