package org.acme.users;

import java.util.Set;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class AuthResource {
    @GET
    @Path("/jwt")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String signedJwt() {
        String email = "test.user@test.com";
        return Jwt.issuer("user_service")
            // User principal name
            .upn(email)
            .groups(Set.of("user"))
            .claim(Claims.full_name, "Usuário teste")
            .claim(Claims.email, email)
            .innerSign()
            .encrypt();
    }
}
