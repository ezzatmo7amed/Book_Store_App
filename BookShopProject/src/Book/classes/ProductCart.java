package Book.classes;

public class ProductCart {
    private Products product;
    private int quantity;
    
    public ProductCart(){
        
    }

    public ProductCart(Products product, int quantity) {
        
        this.product = product;
        this.quantity = quantity;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Products getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
