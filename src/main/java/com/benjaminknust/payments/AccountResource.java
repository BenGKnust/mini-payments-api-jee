package com.benjaminknust.payments;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountResource {

    @Inject
    AccountRepository repository;

    @POST
    @Transactional
    public Response create(@Valid CreateAccountRequest request) {
        Account saved = repository.save(new Account(request.getOwner(), request.getBalance()));
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return Response.ok(account).build();
    }
}