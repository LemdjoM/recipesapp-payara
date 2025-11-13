package ch.unil.doplab.service;

import ch.unil.doplab.domain.Recipes;
import ch.unil.doplab.domain.ShoppingList;
import ch.unil.doplab.service.domain.InventoryService;
import ch.unil.doplab.service.domain.RecipesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

class ShoppingListServiceTest {

    private InventoryService inventory;
    private RecipesService.ShoppingListService shoppingListService;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
        shoppingListService = new RecipesService.ShoppingListService(inventory);
    }

    @Test
    void shouldListMissingIngredients() {
        inventory.addIngredient("Tomate", 1);
        inventory.addIngredient("Fromage", 50);

        Recipes pizza = new Recipes("Pizza", Map.of("Tomate", 3, "Fromage", 100), "");

        ShoppingList list = shoppingListService.generateForRecipe(pizza);

        assertEquals(2, list.getQuantity("Tomate"));
        assertEquals(50, list.getQuantity("Fromage"));
    }

    @Test
    void shouldReturnEmptyListWhenAllInStock() {
        inventory.addIngredient("Pâtes", 500);

        Recipes bolo = new Recipes("Bolo", Map.of("Pâtes", 400), "");

        ShoppingList list = shoppingListService.generateForRecipe(bolo);

        assertTrue(list.isEmpty());
    }

    @Test
    void shouldAggregateMultipleRecipes() {
        inventory.addIngredient("Tomate", 0);
        inventory.addIngredient("Pâtes", 200);

        Recipes pizza = new Recipes("Pizza", Map.of("Tomate", 2), "");
        Recipes bolo = new Recipes("Bolo", Map.of("Tomate", 3, "Pâtes", 400), "");

        ShoppingList list = shoppingListService.generateForRecipes(List.of(pizza, bolo));

        assertEquals(5, list.getQuantity("Tomate")); // 2 + 3
        assertEquals(200, list.getQuantity("Pâtes")); // 400 - 200
    }

    @Test
    void shouldHandleEmptyRecipeList() {
        ShoppingList list = shoppingListService.generateForRecipes(List.of());
        assertTrue(list.isEmpty());
    }
}