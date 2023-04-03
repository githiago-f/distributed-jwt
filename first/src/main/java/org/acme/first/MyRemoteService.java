package org.acme.first;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.oidc.token.propagation.AccessToken;

/**
 * To use it via injection.
 *
 * {@code
 *     @Inject
 *     @RestClient
 *     MyRemoteService myRemoteService;
 *
 *     public long doSomething() {
 *          return myRemoteService.hello(1l, 1l);
 *     }
 * }
 */
@AccessToken
@RegisterRestClient(baseUri = "https://localhost:8445/sum")
public interface MyRemoteService {
    @GET
    @Path("/{a}/{b}")
    @RolesAllowed({"user"})
    @Produces(MediaType.TEXT_PLAIN)
    public long getSum(@PathParam("a") long a, @PathParam("b") long b);
}
