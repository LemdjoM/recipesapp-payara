package ch.unil.doplab.domain;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Ingredient> ingredients;

    public Inventory() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(String nom, int quantite) {
        if (nom == null || nom.trim().isEmpty()) throw new IllegalArgumentException("Nom requis");
        if (quantite < 0) throw new IllegalArgumentException("Quantité négative interdite");

        for (Ingredient i : ingredients) {
            if (i.getNom().equalsIgnoreCase(nom)) {
                i.setQuantite(i.getQuantite() + quantite);
                return;
            }
        }
        ingredients.add(new Ingredient(nom.trim(), quantite));
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients); // Immutable
    }

    public int getQuantite(String nom) {
        for (Ingredient i : ingredients) {
            if (i.getNom().equalsIgnoreCase(nom)) {
                return i.getQuantite();
            }
        }
        return 0;
    }

    public void afficherInventaire() {
        System.out.println("Inventory :");
        for (Ingredient i : ingredients) {
            System.out.println(i);
        }
    }

    public void removeIngredient(String name) {
        ingredients.removeIf(i -> i.getNom().equalsIgnoreCase(name));
    }

    public void modifyQuantity(String name, int quantity) {
        for (Ingredient i : ingredients) {
            if (i.getNom().equalsIgnoreCase(name)) {
                i.setQuantite(quantity);
                return;
            }
        }
    }

    /*public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }*/

    @Override
    public String toString() {
        return "Inventory: " + ingredients.toString();
    }
}