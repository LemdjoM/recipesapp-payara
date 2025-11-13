package ch.unil.doplab.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class ShoppingListTest {

    @Test
    void shouldAccumulateSameItems() {
        ShoppingList list = new ShoppingList();
        list.addItem("Pommes", 3);
        list.addItem("Pommes", 2);
        assertEquals(5, list.getQuantity("Pommes"));
    }

    @Test
    void shouldIgnoreZeroOrNegativeQuantity() {
        ShoppingList list = new ShoppingList();
        list.addItem("Bananes", 0);
        list.addItem("Bananes", -5);
        assertEquals(0, list.getQuantity("Bananes"));
    }

    @Test
    void shouldDetectEmptyList() {
        ShoppingList list = new ShoppingList();
        assertTrue(list.isEmpty());
        list.addItem("Orange", 1);
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldHaveCorrectToString() {
        ShoppingList list = new ShoppingList();
        list.addItem("Lait", 2);
        list.addItem("Pain", 1);

        String expected = """
            === Liste de courses ===
            - Lait: 2
            - Pain: 1
            """.trim();

        assertTrue(list.toString().contains("Lait: 2"));
        assertTrue(list.toString().contains("Pain: 1"));
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        ShoppingList l1 = new ShoppingList(Map.of("A", 1, "B", 2));
        ShoppingList l2 = new ShoppingList(Map.of("B", 2, "A", 1));
        assertEquals(l1, l2);
    }
}