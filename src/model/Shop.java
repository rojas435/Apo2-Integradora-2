package model;
import java.util.ArrayList;


public class Shop {
    private ArrayList<Product> inventory;

    private ArrayList<Order> orders;


    public Shop(){
        this.inventory = new ArrayList<>();
    }



    public boolean addProduct(String name, String description, double price, int quantity, int typeOfProduct, int totalSales){
        if(inventory.add(new Product(name, description, price, quantity, typeOfProduct, totalSales))){
            return true;
        }else{
            return false;
        }
    }



    public String lookForProduct(String name){
        String msj = "";
        for(int i = 0; i<inventory.size(); i++){
            if(inventory.get(i).getName().equalsIgnoreCase(name)){
                return name;
            }
        }

        return msj;
    }



    //Este metodo me quedo rarito pero funciona, retorna el objeto LITERALMENTE
    public Product lookForProduct2(String name){
        for(int i = 0; i<inventory.size(); i++){
            if(inventory.get(i).getName().equalsIgnoreCase(name)){
                return inventory.get(i);
            }
        }
        return null;
    }
    //Este metodo solo hace que se despliegue como String
    public String msjMethod(String name){
        String msj = "";
        Product inventory = (Product)lookForProduct2(name);
        if(inventory != null){
            msj+= inventory;
        }else{
            return "Ese producto no existe";
        }
        return msj;
    }


}
