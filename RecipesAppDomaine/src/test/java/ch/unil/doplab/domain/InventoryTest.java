
package ch.unil.doplab.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void shouldAddNewIngredient() {
        inventory.addIngredient("Riz", 1000);
        assertEquals(1000, inventory.getQuantite("Riz"));
    }

    @Test
    void shouldAccumulateQuantityForSameIngredient() {
        inventory.addIngredient("Pâtes", 500);
        inventory.addIngredient("Pâtes", 300);
        assertEquals(800, inventory.getQuantite("Pâtes"));
    }

    @Test
    void shouldBeCaseInsensitive() {
        inventory.addIngredient("Oeuf", 12);
        assertEquals(12, inventory.getQuantite("oeuf"));
        assertEquals(12, inventory.getQuantite("OEUF"));
    }

    @Test
    void shouldReturnZeroForUnknownIngredient() {
        assertEquals(0, inventory.getQuantite("Inconnu"));
    }

    @Test
    void shouldNotAllowNegativeQuantity() {
        inventory.addIngredient("Lait", 1000);
        assertThrows(IllegalArgumentException.class, () -> {
            inventory.addIngredient("Lait", -500);
        });
    }

    @Test
    void shouldRemoveIngredient() {
        inventory.addIngredient("Beurre", 250);
        inventory.removeIngredient("Beurre");
        assertEquals(0, inventory.getQuantite("Beurre"));
    }

    @Test
    void shouldModifyQuantity() {
        inventory.addIngredient("Huile", 1000);
        inventory.modifyQuantity("Huile", 500);
        assertEquals(500, inventory.getQuantite("Huile"));
    }

    @Test
    void shouldReturnImmutableList() {
        inventory.addIngredient("Sel", 100);
        var list = inventory.getIngredients();
        assertThrows(UnsupportedOperationException.class, () -> {
            list.add(new Ingredient("Poivre", 50));
        });
    }
}
