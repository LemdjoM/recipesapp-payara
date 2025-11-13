package ch.unil.doplab.service.domain;

import ch.unil.doplab.domain.Recipes;
import ch.unil.doplab.domain.ShoppingList;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

/**
 * Service métier : gestion des recettes
 */
@ApplicationScoped
public class RecipesService {

    private final List<Recipes> recipes = new ArrayList<>();
    private final InventoryService inventoryService;

    public RecipesService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public boolean addRecipe(Recipes recipe) {
        // Vérifier les ingrédients disponibles
        for (var entry : recipe.getIngredients().entrySet()) {
            int required = entry.getValue();
            int available = inventoryService.getQuantite(entry.getKey());
            if (available < required) {
                return false; // Pas assez en stock
            }
        }
        return recipes.add(recipe);
    }

    public Recipes getRecipe(String name) {
        return recipes.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Recipes> getAllRecipes() {
        return List.copyOf(recipes);
    }

    public boolean updateRecipe(String name, Recipes updated) {
        Recipes existing = getRecipe(name);
        if (existing == null) return false;

        // Vérifier les nouveaux ingrédients
        for (var entry : updated.getIngredients().entrySet()) {
            if (inventoryService.getQuantite(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        existing.setName(updated.getName());
        // Note: ingredients est immuable → on recrée
        existing.getIngredients().clear();
        existing.getIngredients().putAll(updated.getIngredients());
        existing.setInstructions(updated.getInstructions());
        return true;
    }

    public boolean removeRecipe(String name) {
        return recipes.removeIf(r -> r.getName().equalsIgnoreCase(name));
    }

    /**
     * Génère une liste de courses intelligente
     */
    @ApplicationScoped
    public static class ShoppingListService {

        private final InventoryService inventoryService;

        public ShoppingListService(InventoryService inventoryService) {
            this.inventoryService = inventoryService;
        }

        public ShoppingList generateForRecipes(List<Recipes> recipes) {
            ShoppingList list = new ShoppingList();

            for (Recipes recipe : recipes) {
                for (var entry : recipe.getIngredients().entrySet()) {
                    String ingredient = entry.getKey();
                    int required = entry.getValue();
                    int available = inventoryService.getQuantite(ingredient);
                    int missing = required - available;

                    if (missing > 0) {
                        list.addItem(ingredient, missing);
                    }
                }
            }
            return list;
        }

        public ShoppingList generateForRecipe(Recipes recipe) {
            return generateForRecipes(List.of(recipe));
        }
    }
}