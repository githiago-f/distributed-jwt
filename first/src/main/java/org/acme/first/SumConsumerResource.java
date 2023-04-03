package org.acme.first;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;
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
public class SumConsumerResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @RestClient
    MyRemoteService myRemoteService;

    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    @GET
    @Path("/{a}/{b}")
    @RolesAllowed({"user"})
    @Produces(MediaType.TEXT_PLAIN)
    public long sum(@PathParam("a") long a, @PathParam("b") long b) {
        logger.info(
            "{} asked for the sum of {} + {}. Remote service will process", fullName, a, b);
        long result = myRemoteService.getSum(a, b);
        logger.info("Remote service summed {} + {} and returned {}", a, b, result);
        return result;
    }
}
