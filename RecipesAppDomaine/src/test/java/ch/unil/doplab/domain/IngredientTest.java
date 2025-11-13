package ch.unil.doplab.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void shouldCreateIngredientWithValidValues() {
        Ingredient ingredient = new Ingredient("Tomate", 5);
        assertEquals("Tomate", ingredient.getNom());
        assertEquals(5, ingredient.getQuantite());
    }

    @Test
    void shouldAllowZeroQuantity() {
        Ingredient ingredient = new Ingredient("Sucre", 0);
        assertEquals(0, ingredient.getQuantite());
    }

    @Test
    void shouldNotAllowNegativeQuantity() {
        assertThrows(NullPointerException.class, () -> {
            new Ingredient("Sel", -1);
        });
    }

    @Test
    void shouldNotAllowNullName() {
        assertThrows(NullPointerException.class, () -> {
            new Ingredient(null, 10);
        });
    }

    @Test
    void shouldHaveCorrectToString() {
        Ingredient ingredient = new Ingredient("Farine", 1000);
        assertEquals("Ingredient : nom=Farine, quantite=1000", ingredient.toString());
    }

    @Test
    void shouldBeEqualWhenSameNameAndQuantity() {
        Ingredient i1 = new Ingredient("Lait", 1);
        Ingredient i2 = new Ingredient("Lait", 1);
        assertEquals(i1, i2);
        assertEquals(i1.hashCode(), i2.hashCode());
    }
}