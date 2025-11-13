package ch.unil.doplab.domain;
import java.util.Objects;

public class Ingredient {
    private String nom;
    private int quantite;

    // Constructeurs, getters, setters, toString
    public Ingredient() {}
    public Ingredient(String nom, int quantite) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new NullPointerException("Le nom de l'ingrédient ne peut pas être nul");
        }
        if (quantite < 0) {
            throw new NullPointerException("La quantité ne peut pas être négative");
        }
        this.nom = nom;
        this.quantite = quantite;
    }
    // ... getters/setters/toString
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Ingredient : nom=" + nom + ", quantite=" + quantite ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return quantite == that.quantite &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, quantite);
    }
}