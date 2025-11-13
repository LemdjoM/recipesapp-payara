package ch.unil.doplab.service;

import ch.unil.doplab.domain.Recipes;
import ch.unil.doplab.service.domain.InventoryService;
import ch.unil.doplab.service.domain.RecipesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class RecipeServiceTest {

    private InventoryService inventory;
    private RecipesService recipesService;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
        recipesService = new RecipesService(inventory);
    }

    @Test
    void shouldAddRecipeWhenEnoughStock() {
        inventory.addIngredient("Tomate", 5);
        inventory.addIngredient("Fromage", 200);

        Recipes pizza = new Recipes("Pizza", Map.of("Tomate", 2, "Fromage", 100), "Cuire");

        assertTrue(recipesService.addRecipe(pizza));
        assertEquals("Pizza", recipesService.getRecipe("Pizza").getName());
    }

    @Test
    void shouldNotAddRecipeWhenNotEnoughStock() {
        inventory.addIngredient("Tomate", 1);

        Recipes pizza = new Recipes("Pizza", Map.of("Tomate", 3), "Cuire");

        assertFalse(recipesService.addRecipe(pizza));
    }

    @Test
    void shouldGetRecipeByName() {
        inventory.addIngredient("Pâtes", 500);
        Recipes carbonara = new Recipes("Carbonara", Map.of("Pâtes", 200), "Mélanger");
        recipesService.addRecipe(carbonara);

        assertEquals("Carbonara", recipesService.getRecipe("carbonara").getName());
    }

    @Test
    void shouldReturnNullForUnknownRecipe() {
        assertNull(recipesService.getRecipe("Inconnue"));
    }

    @Test
    void shouldRemoveRecipe() {
        inventory.addIngredient("Riz", 1000);
        Recipes riz = new Recipes("Riz Cantonais", Map.of("Riz", 300), "");
        recipesService.addRecipe(riz);

        assertTrue(recipesService.removeRecipe("Riz Cantonais"));
        assertNull(recipesService.getRecipe("Riz Cantonais"));
    }

    @Test
    void shouldReturnAllRecipes() {
        inventory.addIngredient("Tomate", 10);
        inventory.addIngredient("Pâtes", 500);

        recipesService.addRecipe(new Recipes("Pizza", Map.of("Tomate", 2), ""));
        recipesService.addRecipe(new Recipes("Bolo", Map.of("Pâtes", 200), ""));

        assertEquals(2, recipesService.getAllRecipes().size());
    }
}