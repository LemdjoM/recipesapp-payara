package ch.unil.doplab.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;

class RecipesTest {

    @Test
    void shouldCreateRecipeWithValidData() {
        Map<String, Integer> ingredients = Map.of("Farine", 500, "Eau", 300);
        Recipes recipe = new Recipes("Pain", ingredients, "Mélanger et cuire");

        assertEquals("Pain", recipe.getName());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(500, recipe.getIngredients().get("Farine"));
        assertEquals("Mélanger et cuire", recipe.getInstructions());
    }

    @Test
    void shouldRejectNullName() {
        Map<String, Integer> ingredients = Map.of("Sel", 10);
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipes(null, ingredients, "Cuire");
        });
    }

    @Test
    void shouldRejectEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipes("   ", Map.of(), "");
        });
    }

    @Test
    void shouldRejectNegativeIngredientQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, Integer> bad = Map.of("Sucre", -5);
            new Recipes("Gâteau", bad, "Mélanger");
        });
    }

    @Test
    void shouldBeImmutableAfterCreation() {
        // GIVEN
        Map<String, Integer> original = new HashMap<>();
        original.put("Tomate", 2);
        Recipes recipe = new Recipes("Salade", original, "Laver");

        // WHEN - essayer de modifier la map originale
        original.put("Concombre", 1);

        // THEN - la recette reste inchangée
        assertFalse(recipe.getIngredients().containsKey("Concombre"));
        assertEquals(2, recipe.getIngredients().get("Tomate"));

        // ET - modification directe → lance exception
        /*Map<String, Integer> fromRecipe = recipe.getIngredients();
        assertThrows(UnsupportedOperationException.class, () -> {
            fromRecipe.put("Poivre", 1); // LANCE L'EXCEPTION
        });*/
    }
}