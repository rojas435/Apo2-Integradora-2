package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import ui.*;

import java.util.ArrayList;

public class TestAplication {

    public Shop setupStage1(){
        Shop shop = new Shop();
        shop.addProduct("Book 1", "A great book", 10.99, 5, 1, 0);
        shop.addProduct("Phone", " ", 999, 10, 2, 20);
        return shop;
    };

    public Shop setupStage2(){
        Shop shop = new Shop();
        shop.addProduct("Book 1", "A great book", 10.99, 5, 1, 0);
        shop.addProduct("Phone", " ", 999, 10, 2, 20);
        shop.createOrder("Felipe");
        shop.addProductToOrder(0, "Phone", 2);
        shop.addProductToOrder(0, "Book 1", 2);
        return shop;
    }

    @Test
    public void addProductTestWorks(){
      Shop shop = setupStage1();
      ArrayList<Product> products = new ArrayList();
      Product product = new Product("Book 1", "A great book", 10.99, 5, 1, 0);
      Product product2 = new Product("Phone", " ", 999, 10, 2, 20);
      products.add(product);
      products.add(product2);
      assertEquals(products.get(0).getName(), shop.showInv().get(0).getName());
      assertEquals(products.get(1).getName(), shop.showInv().get(1).getName());
    };

    @Test
    public void removeProductTestWorks(){
        Shop shop = setupStage1();
        shop.eliminateProduct("Book 1");
        Product product2 = new Product("Phone", " ", 999, 10, 2, 20);
        assertEquals(product2.getName(), shop.showInv().get(0).getName());
    };

    @Test
    public void searchProductName() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage1();
        assertEquals(1, shop.binarySearchPS("name", "Phone", shop.showInv().get(0).comparatorForUse(1)));
    };
    @Test
    public void searchProductCategory() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage1();
        assertEquals(1, shop.binarySearchPS("categories", Categories.fromInt(((Integer)2)), shop.showInv().get(0).comparatorForUse(3)));
    };
    @Test
    public void searchProductPrice() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage1();
        assertEquals(1, shop.binarySearchPMin("price", 200.0, shop.showInv().get(0).comparatorForUse(2)));
    };
    @Test
    public void searchProductSales() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage1();
        assertEquals(1, shop.binarySearchPMin("totalSales", 1.0, shop.showInv().get(0).comparatorForUse(4)));
    };

    @Test
    public void searchOrderName() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage2();
        Order order = new Order("Jacob");
        assertEquals(0, shop.binarySearchOS("name", "Felipe", order.comparatorForUse(1)));
    };

    @Test
    public void searchOrderNameNull() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage2();
        Order order = new Order("Jacob");
        Order order2 = new Order("Chris");
        assertEquals(-1, shop.binarySearchOS("name", "Francis", order.comparatorForUse(1)));
    };

    @Test
    public void searchOrderTotalPrice(){

    };

    @Test
    public void searchOrderDate(){

    };

    @Test
    public void createOrder(){};

}
