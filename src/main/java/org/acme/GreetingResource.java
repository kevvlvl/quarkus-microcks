package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.GreetingsDto;

import java.time.LocalDateTime;

@Path("/api/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GreetingsDto hello() {
        return new GreetingsDto("Greetings and salutations from kevv", LocalDateTime.now());
    }
}
