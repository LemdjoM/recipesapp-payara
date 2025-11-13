package ch.unil.doplab.service;

import ch.unil.doplab.domain.Ingredient;
import ch.unil.doplab.service.domain.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class InventoryServiceTest {

    private InventoryService inventory;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
    }

    @Test
    void shouldAddAndRetrieveIngredient() {
        inventory.addIngredient("Tomate", 10);
        assertEquals(10, inventory.getQuantite("Tomate"));
    }

    @Test
    void shouldAccumulateSameIngredient() {
        inventory.addIngredient("Pâtes", 200);
        inventory.addIngredient("Pâtes", 300);
        assertEquals(500, inventory.getQuantite("Pâtes"));
    }

    @Test
    void shouldBeCaseInsensitive() {
        inventory.addIngredient("Oeuf", 6);
        assertEquals(6, inventory.getQuantite("OEUF"));
        assertEquals(6, inventory.getQuantite("oeuf"));
    }

    @Test
    void shouldReturnZeroForUnknown() {
        assertEquals(0, inventory.getQuantite("Inconnu"));
    }

    @Test
    void shouldThrowOnNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            inventory.addIngredient("Sel", -5);
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
        inventory.modifyQuantity("Huile", 400);
        assertEquals(400, inventory.getQuantite("Huile"));
    }

    @Test
    void shouldReturnImmutableList() {
        inventory.addIngredient("Poivre", 50);
        List<Ingredient> list = inventory.getAllIngredients();
        assertThrows(UnsupportedOperationException.class, () -> {
            list.add(new Ingredient("Sel", 10));
        });
    }
}