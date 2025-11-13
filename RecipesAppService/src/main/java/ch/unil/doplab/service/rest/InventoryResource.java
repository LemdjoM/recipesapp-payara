package ch.unil.doplab.service.rest;

import ch.unil.doplab.service.domain.InventoryService;
import ch.unil.doplab.domain.Ingredient;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/inventory")
public class InventoryResource {
    @Inject
    private InventoryService inventory;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInventory() {
        try {
            List<Ingredient> list = inventory.getAllIngredients();
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.status(400).entity("Erreur: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(ItemRequest req) {
        try {
            inventory.addIngredient(req.getName(), req.getQuantity());
            return Response.status(201).entity("OK").build();
        } catch (Exception e) {
            return Response.status(400).entity("Erreur: " + e.getMessage()).build();
        }
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuantity(@PathParam("name") String name) {
        try {
            int qty = inventory.getQuantite(name);
            return Response.ok(qty).build(); // renvoie la quantité en JSON
        } catch (Exception e) {
            return Response.status(400).entity("Erreur: " + e.getMessage()).build();
        }
    }


}



/* ============================= DTO POUR LA RÉPONSE ============================= */
/*public class QuantityResponse {
    private String name;
    private int quantity;

    public QuantityResponse(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
}*/


