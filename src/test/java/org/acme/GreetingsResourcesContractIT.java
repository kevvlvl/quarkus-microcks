package org.acme;

import io.github.microcks.testcontainers.MicrocksContainer;
import io.github.microcks.testcontainers.model.TestRequest;
import io.github.microcks.testcontainers.model.TestResult;
import io.github.microcks.testcontainers.model.TestRunnerType;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class GreetingsResourcesContractIT {

    @ConfigProperty(name= "quarkus.http.test-port")
    int quarkusHttpPort;

    @ConfigProperty(name= "quarkus.microcks.default.http")
    String microcksContainerUrl;

    /**
     * Test the API itself by using the openapi contract as the source of truth
     */
    @Test
    void testGreetingsOpenApiContactTest() throws Exception {

        TestRequest testRequest = new TestRequest.Builder()
                .serviceId("Greetings Service:0.0.2")
                .runnerType(TestRunnerType.OPEN_API_SCHEMA.name())
                .testEndpoint("http://host.testcontainers.internal:" + quarkusHttpPort + "/api")
                .build();

        TestResult testResult = MicrocksContainer.testEndpoint(microcksContainerUrl, testRequest);

        assertThat(testResult.isSuccess()).isTrue();
        assertThat(testResult.getTestCaseResults().size()).isEqualTo(1);
    }
}