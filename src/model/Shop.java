package model;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;


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

    public <T> void binarySearchP(String attributeName, T value) throws NoSuchFieldException, IllegalAccessException {
        int comp = 0;
        switch (attributeName){
            case "name":
                comp =1;
                break;
            case "price":
                comp = 2;
                break;
            case "category":
                comp = 3;
                break;
            case "totalSales":
                comp = 4;
                break;
        }
        binarySearchP(attributeName, value, inventory.get(0).comparatorForUse(comp));
        return;
    };

    public <T> int binarySearchP(String attributeName, T value, Comparator<Product> comparator) throws NoSuchFieldException, IllegalAccessException {
        int initialIndex = 0;
        int finalIndex = inventory.size() - 1;

        while (initialIndex <= finalIndex) {
            int middleIndex = (initialIndex + finalIndex) / 2;

            Product middleObject = (Product) inventory.get(middleIndex);

            Field field = middleObject.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);

            Object middleValue = field.get(middleObject);

            int comparison;
            if (middleValue instanceof String && value instanceof String) {
                comparison = ((String) middleValue).compareTo((String) value);
            } else if (middleValue instanceof Integer && value instanceof Integer) {
                comparison = Integer.compare((Integer) middleValue, (Integer) value);
            } else {
                throw new IllegalArgumentException("Cannot compare " + middleValue.getClass().getSimpleName() + " with " + value.getClass().getSimpleName());
            }
            if (comparison == 0) {
                System.out.println(inventory.get(middleIndex).toString());
                return middleIndex;
            } else if (comparison < 0) {
                initialIndex = middleIndex + 1;
            } else {
                finalIndex = middleIndex - 1;
            }
        }

        return -1;
    }

    public void binarySearchO(String attributeName, Order value) throws NoSuchFieldException, IllegalAccessException {
        int comp = 0;
        switch (attributeName){
            case "name":
                comp =1;
                break;
            case "totalPrice":
                comp = 2;
                break;
            case "date":
                comp = 3;
                break;
        }
        binarySearchO(attributeName, value, value.comparatorForUse(comp));
        return;
    };

    public <Order> int binarySearchO(String attributeName, Order value, Comparator<Order> comparator) throws NoSuchFieldException, IllegalAccessException {
        int initialIndex = 0;
        int finalIndex = orders.size() - 1;

        while (initialIndex <= finalIndex) {
            int middleIndex = (initialIndex + finalIndex) / 2;

            Order middleObject = (Order) orders.get(middleIndex);

            Field field = middleObject.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);

            Object middleValue = field.get(middleObject);

            int comparison = comparator.compare(middleObject, value);

            if (comparison == 0) {
                return middleIndex;
            } else if (comparison < 0) {
                initialIndex = middleIndex + 1;
            } else {
                finalIndex = middleIndex - 1;
            }
        }

        return -1;
    }



}
