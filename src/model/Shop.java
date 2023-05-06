package model;
import java.util.ArrayList;


public class Shop {
    private ArrayList<Product> inventory;

    private ArrayList<Order> orders;


    public Shop(){
        this.inventory = new ArrayList<>();
    }

    public void showInv(){
        int i = 0;
        while(i< inventory.size()){
            System.out.println(inventory.get(i).toString());
        }
        return;
    }



    public boolean addProduct(String name, String description, double price, int quantity, int typeOfProduct, int totalSales){
        if(inventory.add(new Product(name, description, price, quantity, typeOfProduct, totalSales))){
            return true;
        }else{
            return false;
        }
    }

    public void eliminateProduct(String name){
        for(int i = 0; i< inventory.size()-1; i++){
            if(inventory.get(i)!=null){
                if(inventory.get(i).getName().equals(name)){
                    inventory.remove(inventory.get(i));
                    return;
                }
            }
        }
        return;
    };

    public int createOrder(String name){
        Order order = new Order(name);
        orders.add(order);
        return orders.size()-1;
    };

    public void addProductToOrder(int orderP, String productName, int quantity){

    };

    public void removeProductToOrder(int orderP, String productName, int quantity){

    };

    public void checkOrder(int orderP){
        orders.get(orderP).calculateTotalPrice();
        System.out.println(orders.get(orderP).toString());
        for(int i = 0; i<orders.get(orderP).getItems().size()-1;i++){
            System.out.println(orders.get(orderP).getItems().get(i).productData());
        }
        return;
    };

    public boolean stockCheck(int orderP){
        return false;
    };

    public void processOrder(int orderP){
        return;
    };


    //Este metodo me quedo rarito pero funciona, retorna el objeto LITERALMENTE
    public Product searchP(String name){
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
        Product inventory = (Product)searchP(name);
        if(inventory != null){
            msj+= inventory;
        }else{
            return "Ese producto no existe";
        }
        return msj;
    }


}
