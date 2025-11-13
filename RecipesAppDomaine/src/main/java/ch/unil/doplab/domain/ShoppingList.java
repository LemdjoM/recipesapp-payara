package ch.unil.doplab.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Représente une liste de courses générée à partir de recettes et de l'inventaire.
 */
public class ShoppingList {

    private final Map<String, Integer> itemsToBuy; // <nom ingrédient, quantité à acheter>

    public ShoppingList() {
        this.itemsToBuy = new HashMap<>();
    }

    public ShoppingList(Map<String, Integer> itemsToBuy) {
        this.itemsToBuy = itemsToBuy != null ? new HashMap<>(itemsToBuy) : new HashMap<>();
    }

    // === Getters ===
    public Map<String, Integer> getItemsToBuy() {
        return new HashMap<>(itemsToBuy); // Copie défensive
    }

    // === Méthodes utilitaires ===
    public void addItem(String ingredient, int quantity) {
        if (quantity <= 0) return;
        itemsToBuy.merge(ingredient, quantity, Integer::sum);
    }

    public int getQuantity(String ingredient) {
        return itemsToBuy.getOrDefault(ingredient, 0);
    }

    public boolean isEmpty() {
        return itemsToBuy.isEmpty();
    }

    @Override
    public String toString() {
        if (itemsToBuy.isEmpty()) {
            return "Liste de courses vide (tout est en stock !)";
        }
        StringBuilder sb = new StringBuilder("=== Liste de courses ===\n");
        itemsToBuy.forEach((nom, qte) -> sb.append("- ").append(nom).append(": ").append(qte).append("\n"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingList)) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(itemsToBuy, that.itemsToBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemsToBuy);
    }
}