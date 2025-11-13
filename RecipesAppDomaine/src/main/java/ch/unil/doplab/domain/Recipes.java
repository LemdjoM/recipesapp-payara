package ch.unil.doplab.domain;

import java.util.HashMap;
import java.util.Map;

public class Recipes {
    private String name;
    private Map<String, Integer> ingredients = new HashMap<>();
    private String instructions;

    // Constructeurs, getters, setters
    public Recipes() {}
    public Recipes(String name, Map<String, Integer> ingredients, String instructions) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la recette est requis");
        }
        this.name = name.trim();

        // Copie défensive + immuable
        this.ingredients = new HashMap<>();
        if (ingredients != null) {
            for (var entry : ingredients.entrySet()) {
                if (entry.getKey() == null || entry.getKey().trim().isEmpty()) {
                    throw new IllegalArgumentException("Nom d'ingrédient invalide");
                }
                if (entry.getValue() == null || entry.getValue() < 0) {
                    throw new IllegalArgumentException("Quantité invalide");
                }
                this.ingredients.put(entry.getKey().trim(), entry.getValue());
            }
        }

        this.instructions = instructions != null ? instructions : "";
    }
    // ... getters/setters/toString
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Recipes [name=" + name + ", ingredients=" + ingredients + ", instructions=" + instructions;
    }
}