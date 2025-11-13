package ch.unil.doplab.service.domain;
import ch.unil.doplab.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Service métier : gestion de l'inventaire
 */
@ApplicationScoped
public class InventoryService {

    private final List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(String nom, int quantite) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Nom d'ingrédient requis");
        }
        if (quantite < 0) {
            throw new IllegalArgumentException("Quantité négative interdite");
        }

        for (Ingredient i : ingredients) {
            if (i.getNom().equalsIgnoreCase(nom.trim())) {
                i.setQuantite(i.getQuantite() + quantite);
                return;
            }
        }
        ingredients.add(new Ingredient(nom.trim(), quantite));
    }

    public int getQuantite(String nom) {
        if (nom == null) return 0;
        return ingredients.stream()
                .filter(i -> i.getNom().equalsIgnoreCase(nom.trim()))
                .mapToInt(Ingredient::getQuantite)
                .findFirst()
                .orElse(0);
    }

    public List<Ingredient> getAllIngredients() {
        return List.copyOf(ingredients); // Vue immuable
    }

    public void removeIngredient(String nom) {
        ingredients.removeIf(i -> i.getNom().equalsIgnoreCase(nom));
    }

    public void modifyQuantity(String nom, int nouvelleQuantite) {
        if (nouvelleQuantite < 0) {
            throw new IllegalArgumentException("Quantité invalide");
        }
        for (Ingredient i : ingredients) {
            if (i.getNom().equalsIgnoreCase(nom)) {
                i.setQuantite(nouvelleQuantite);
                return;
            }
        }
    }
}

/* ============================= DTO POUR LA REQUÊTE ============================= */
class ItemRequest {
    private String name;
    private int quantity;

    public ItemRequest() {}  // JAX-RS en a besoin

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

/* ============================= DTO POUR LA RÉPONSE ============================= */
class QuantityResponse {
    private String name;
    private int quantity;

    public QuantityResponse(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
}