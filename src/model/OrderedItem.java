package model;

public class OrderedItem {
    private Product product;
    private int quantity;


    public OrderedItem(Product producto, int quantity) {
        this.product = producto;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product producto) {
        this.product = producto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = quantity;
    }

    public double calcularSubtotal() {
        return quantity * product.getPrice();
    }

    public String productData(){
        return "Order{" +
                "name='" + product.getName() + '\'' +
                ", quantity=" + quantity +
                ", price=" + product.getPrice() +
                '}';
    }
}
