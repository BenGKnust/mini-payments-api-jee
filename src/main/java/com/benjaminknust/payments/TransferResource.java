package com.benjaminknust.payments;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transfers")
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransferResource {

    @Inject
    TransferService service;

    @POST
    public Response transfer(@Valid TransferRequest request) {
        service.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        return Response.noContent().build();
    }
}