package Test;

import model.*;
import ui.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAplication {
    
    @Test
    void testAddProduct() {
        Shop shop = new Shop();
        assertTrue(shop.addProduct("Book 1", "A great book", 10.99, 5, 1, 0));
        assertEquals(1, shop.showInv().size());
        assertTrue(shop.addProduct("Phone", "An awesome phone", 999.99, 10, 2, 0));
        assertEquals(2, shop.showInv(Void));
    }
    
    @Test
    void testAddProductInvalidInput() {
        Shop shop = new Shop();
        assertFalse(shop.addProduct("", "A great book", 10.99, 5, 1, 0));
        assertEquals(0, shop.showInv().size());
        assertFalse(shop.addProduct("Book 1", "", 10.99, 5, 1, 0));
        assertEquals(0, shop.showInv().size());
        assertFalse(shop.addProduct("Book 1", "A great book", -10.99, 5, 1, 0));
        assertEquals(0, shop.showInv().size());
        assertFalse(shop.addProduct("Book 1", "A great book", 10.99, -5, 1, 0));
        assertEquals(0, shop.showInv().size());
        assertFalse(shop.addProduct("Book 1", "A great book", 10.99, 5, 100, 0));
        assertEquals(0, shop.showInv().size());
        assertFalse(shop.addProduct("Book 1", "A great book", 10.99, 5, -1, 0));
        assertEquals(0, shop.showInv().size());
    }

    private Shop shop;
    private Product p1;
    private Product p2;
    private Product p3;
    
    @BeforeEach
    public void setup() {
        shop = new Shop();
        p1 = new Product("product1", "description1", 10.0, 5, 1, 0.0);
        p2 = new Product("product2", "description2", 20.0, 3, 2, 0.0);
        p3 = new Product("product3", "description3", 30.0, 7, 3, 0.0);
        shop.addProduct(p1);
        shop.addProduct(p2);
        shop.addProduct(p3);
    }
    
    @Test
    public void testEliminateProduct() {
        shop.eliminateProduct("product1");
        List<Product> expectedInventory = new ArrayList<>();
        expectedInventory.add(p2);
        expectedInventory.add(p3);
        Assertions.assertEquals(expectedInventory, shop.showInv());
    }
    
    @Test
    public void testEliminateNonExistingProduct() {
        shop.eliminateProduct("product4");
        List<Product> expectedInventory = new ArrayList<>();
        expectedInventory.add(p1);
        expectedInventory.add(p2);
        expectedInventory.add(p3);
        Assertions.assertEquals(expectedInventory, shop.showInv());
    }

    @Test
    void searchProduct_returnsExistingProduct() {
        // Arrange
        Product p1 = new Product("ABC123", "Shampoo", 5.0);
        Product p2 = new Product("DEF456", "Conditioner", 5.0);
        Product[] products = {p1, p2};
        
        // Act
        Product result = Product.searchProduct("ABC123", products);
        
        // Assert
        assertEquals(p1, result);
    }
    
    @Test
    void searchProduct_returnsNullForNonExistingProduct() {
        // Arrange
        Product p1 = new Product("ABC123", "Shampoo", 5.0);
        Product p2 = new Product("DEF456", "Conditioner", 5.0);
        Product[] products = {p1, p2};
        
        // Act
        Product result = Product.searchProduct("XYZ789", products);
        
        // Assert
        assertNull(result);
    }

    @Test
    public void testSearchOrderByName() throws NoSuchFieldException, IllegalAccessException {
        Order order1 = new Order(1, new Date(), "John Doe", 123.45);
        Order order2 = new Order(2, new Date(), "Jane Smith", 67.89);
        Order order3 = new Order(3, new Date(), "Bob Brown", 34.56);
        Order order4 = new Order(4, new Date(), "John Doe", 98.76);
        ArrayList<Order> orders = new ArrayList<>(Arrays.asList(order1, order2, order3, order4));
        Inventory inventory = new Inventory();
        inventory.setOrders(orders);

        int index = inventory.searchOrder(1, "Bob Brown");
        assertEquals(-1, index);

        index = inventory.searchOrder(1, "John Doe");
        assertEquals(0, index);

        index = inventory.searchOrder(1, "Jane Smith");
        assertEquals(1, index);

        index = inventory.searchOrder(1, "John Doe", "98.76");
        assertEquals(3, index);
    }

    @Test
    public void testSearchOrderByDate() {
        Order order1 = new Order(1, new Date(1234567), "John Doe", 123.45);
        Order order2 = new Order(2, new Date(2345678), "Jane Smith", 67.89);
        Order order3 = new Order(3, new Date(3456789), "Bob Brown", 34.56);
        Order order4 = new Order(4, new Date(4567890), "John Doe", 98.76);
        ArrayList<Order> orders = new ArrayList<>(Arrays.asList(order1, order2, order3, order4));
        Inventory inventory = new Inventory();
        inventory.setOrders(orders);

        int index = inventory.searchOrdersByDate(new Date(0), new Date(1234567));
        assertEquals(-1, index);

        index = inventory.searchOrdersByDate(new Date(0), new Date(1234568));
        assertEquals(0, index);

        index = inventory.searchOrdersByDate(new Date(1234567), new Date(2345678));
        assertEquals(0, index);

        index = inventory.searchOrdersByDate(new Date(0), new Date(4567890));
        assertEquals(orders.size() - 1, index);
    }

    private Order order;
    private Product product;
    
    @BeforeEach
    void setUp() throws Exception {
        product = new Product("iPhone", "Apple iPhone", 1000.0);
        order = new Order("John Doe");
    }

    @Test
    void testCreateOrder() {
        int orderId = order.createOrder("John Doe");
        assertEquals(1, orderId);
        assertEquals(1, order.getOrders().size());
    }
    
    @Test
    void testCreateOrderWithItems() {
        int orderId = order.createOrder("John Doe");
        OrderedItem item = new OrderedItem(product, 2);
        order.getOrders().get(orderId).getItems().add(item);
        assertEquals(1, orderId);
        assertEquals(1, order.getOrders().size());
        assertEquals(1, order.getOrders().get(orderId).getItems().size());
        assertEquals(2000.0, order.getOrders().get(orderId).getTotalPrice());
    }

}




