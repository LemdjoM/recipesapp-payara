package ch.unil.doplab.service.rest;

/* ============================= DTO POUR LA REQUÊTE ============================= */
public class ItemRequest {
    private String name;
    private int quantity;

    public ItemRequest() {}  // JAX-RS en a besoin

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
