package ch.unil.doplab.recipesapp;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@ApplicationScoped
public class RecipesAppService {
    private static final String BASE_URL = "http://localhost:8081/RecipesAppService-1.0-SNAPSHOT/api";
    private WebTarget serviceTarget;

    @PostConstruct
    public void init() {
        System.out.println("StuddyBuddyService created: " + this.hashCode());
        Client client = ClientBuilder.newClient();
        serviceTarget = client.target(BASE_URL).path("recipesapp");
    }


    public UUID authenticate(String username, String password, String role) {
        var response = serviceTarget
                .path("authenticate")
                .path(username)
                .path(password)
                .path(role)
                .request(MediaType.APPLICATION_JSON)
                .get(UUID.class);
        return response;
    }
}
