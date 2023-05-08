package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import model.*;
import ui.*;

import java.util.ArrayList;

public class TestAplication {

    public void setupStage1(){
        Shop shop = new Shop();
        shop.addProductToOrder();
    };
    public void setupStage2(){};
    public void setupStage3(){};
    public void setupStage4(){};
    public void setupStage5(){};
    public void setupStage6(){};
    @Test
    void testAddProduct() {
        Shop shop = new Shop();
        assertTrue(shop.addProduct("Book 1", "A great book", 10.99, 5, 1, 0));
        assertEquals(1, shop.showInv().size());
        assertTrue(shop.addProduct("Phone", "An awesome phone", 999.99, 10, 2, 0));
        assertEquals(2, shop.showInv().size());
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

}
