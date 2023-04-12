package model;
import java.util.ArrayList;


public class Controller {



    private ArrayList<Product> products;


    public Controller(){
        this.products = new ArrayList<>();
    }



    public boolean addProduct(String name, String description, int price, int quantity, int typeOfProduct, int totalSales){
        if(products.add(new Product(name, description, price, quantity, typeOfProduct, totalSales))){
            return true;
        }else{
            return false;
        }
    }
}
