package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import ui.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestAplication {

    public Shop setupStage1(){
        Shop shop = new Shop();
        shop.addProduct("Book 1", "A great book", 10.99, 5, 1, 0, 1);
        shop.addProduct("Phone", " ", 999, 10, 2, 20, 1);
        return shop;
    };

    public Shop setupStage2(){
        Shop shop = new Shop();
        shop.addProduct("Book 1", "A great book", 10.99, 5, 1, 0, 1);
        shop.addProduct("Phone", " ", 999, 10, 2, 20, 1);
        shop.createOrder("Felipe");
        shop.addProductToOrder(0, "Phone", 2);
        shop.addProductToOrder(0, "Book 1", 2);
        shop.checkOrder(0);
        shop.createOrder("Jacob");
        shop.addProductToOrder(1, "Phone", 2);
        shop.addProductToOrder(1, "Book 1", 2);
        shop.createOrder("Chris");
        shop.addProductToOrder(2, "Phone", 2);
        shop.addProductToOrder(2, "Book 1", 2);
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
    public void addProductTestDontAdd(){
        Shop shop = setupStage1();
        assertEquals(false, shop.addProduct("Book 1", "", 20.0, 1, 11, 0, 0));
    };

    @Test
    public void removeProductTestWorks(){
        Shop shop = setupStage1();
        shop.eliminateProduct("Book 1", 5);
        Product product2 = new Product("Phone", " ", 999, 10, 2, 20);
        assertEquals(product2.getName(), shop.showInv().get(0).getName());
    };

    @Test
    public void removeProductTestDontAdd(){
        Shop shop = setupStage1();
        assertEquals(false, shop.eliminateProduct("Book 1", 6));
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
        Order order = new Order("Francesca");
        assertEquals(1, shop.binarySearchOS("name", "Felipe", order.comparatorForUse(1)));
    };

    @Test
    public void searchOrderNameNull() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage2();
        Order order = new Order("Jacob");
        Order order2 = new Order("Chris");
        assertEquals(-1, shop.binarySearchOS("name", "Francis", order.comparatorForUse(1)));
    };

    @Test
    public void searchOrderTotalPrice() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage2();
        Order order = new Order("Francis");
        assertEquals(2, shop.binarySearchOMin("totalPrice", 1000.0, order.comparatorForUse(2)));
    };

    @Test
    public void searchOrderDate() throws NoSuchFieldException, IllegalAccessException {
        Shop shop = setupStage2();
        Order order = new Order("Francis");
        LocalDate date = LocalDate.of(2023,5,7);
        assertEquals(0, shop.binarySearchODate("date", date, order.comparatorForUse(3)));
    };

    @Test
    public void createOrderEnoughStock(){
        Shop shop = setupStage1();
        shop.createOrder("Felipe");
        shop.addProductToOrder(0, "Phone", 2);
        assertEquals(true, shop.stockCheck(0));
    };

    @Test
    public void createOrderNoteEnoughStock(){
        Shop shop = setupStage1();
        shop.createOrder("Felipe");
        shop.addProductToOrder(0, "Phone", 21);
        assertEquals(false, shop.stockCheck(0));
    };

}
