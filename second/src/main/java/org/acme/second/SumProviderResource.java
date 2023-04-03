package org.acme.second;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sum")
public class SumProviderResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @Claim(standard = Claims.email)
    String email;

    @GET
    @Path("/{a}/{b}")
    @RolesAllowed({ "user" })
    @Produces(MediaType.TEXT_PLAIN)
    public long getSum(@PathParam("a") long a, @PathParam("b") long b) {
        long result = a + b;
        logger.info("User {} is summing {} + {} = {}", email, a, b, result);
        return result;
    }
}
