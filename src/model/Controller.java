package model;
import java.util.ArrayList;


public class Controller {



    private ArrayList<Product> products;


    public Controller(){
        this.products = new ArrayList<>();
    }



    public boolean addProduct(String name, String description, double price, int quantity, int typeOfProduct, int totalSales){
        if(products.add(new Product(name, description, price, quantity, typeOfProduct, totalSales))){
            return true;
        }else{
            return false;
        }
    }



    public String lookForProduct(String name){
        String msj = "";
        for(int i = 0; i<products.size(); i++){
            if(products.get(i).getName().equalsIgnoreCase(name)){
                return name;
            }
        }

        return msj;
    }



    //Este metodo me quedo rarito pero funciona, retorna el objeto LITERALMENTE
    public Product lookForProduct2(String name){
        for(int i = 0; i<products.size(); i++){
            if(products.get(i).getName().equalsIgnoreCase(name)){
                return products.get(i);
            }
        }
        return null;
    }
    //Este metodo solo hace que se despliegue como String
    public String msjMethod(String name){
        String msj = "";
        Product products = (Product)lookForProduct2(name);
        if(products != null){
            msj+= products;
        }else{
            return "Ese producto no existe";
        }
        return msj;
    }


}
