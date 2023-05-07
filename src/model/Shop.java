package model;
import sun.util.resources.cldr.tg.CalendarData_tg_Cyrl_TJ;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.smartcardio.ATR;


public class Shop {
    private ArrayList<Product> inventory;

    private ArrayList<Order> orders;


    public Shop(){
        this.inventory = new ArrayList<>();
    }

    public void showInv(){
        int i = 0;
        if(inventory!=null) {
            while (i < inventory.size()) {
                System.out.println(inventory.get(i).toString());
                i++;
            }
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

    public <T> void binarySearchP(int attributeName, T value, T value2) throws NoSuchFieldException, IllegalAccessException {
        int comp = 0;
        int index;
        String attName = "";
        switch (attributeName){
            case 1:
                attName = "name";
                printRangeName(binarySearchPS(attName, value, inventory.get(0).comparatorForUse(attributeName)), ((String) value));
                break;
            case 2:
                attName = "price";
                printRangePrice(binarySearchPMin(attName, value, inventory.get(0).comparatorForUse(attributeName)), ((Double)value2));
                break;
            case 3:
                 attName = "categories";

                 printRangeCategory(binarySearchPS(attName, Categories.fromInt(((Integer)value)), inventory.get(0).comparatorForUse(attributeName)), Categories.fromInt(((Integer)value)));
                break;
            case 4:
                attName = "totalSales";
                printRangeSales(binarySearchPMin(attName, value, inventory.get(0).comparatorForUse(attributeName)), ((Double)value2));
                break;
        }
        return;
    };

    public <T> int binarySearchPS(String attributeName, T value, Comparator<Product> comparator) throws NoSuchFieldException, IllegalAccessException {
        int initialIndex = 0;
        int finalIndex = inventory.size();
        Collections.sort(inventory,comparator);

        while (initialIndex <= finalIndex) {
            int middleIndex = (initialIndex + finalIndex) / 2;

            Product middleObject = (Product) inventory.get(middleIndex);

            Field field = middleObject.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);

            Object middleValue = field.get(middleObject);

            int comparison;
            if (middleValue instanceof String && value instanceof String) {
                comparison = ((String) middleValue).compareTo((String) value);
            } else if (middleValue instanceof Categories && value instanceof Categories) {
                comparison = ((Categories) middleValue).compareTo((Categories) value);
            } else {
                throw new IllegalArgumentException("Cannot compare " + middleValue.getClass().getSimpleName() + " with " + value.getClass().getSimpleName());
            }
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

    public <T> int binarySearchPMin(String attributeName, T value, Comparator<Product> comparator) throws NoSuchFieldException, IllegalAccessException {
        int initialIndex = 0;
        int finalIndex = inventory.size()-1;
        Collections.sort(inventory,comparator);
        int minIndex=-1;
        while (initialIndex <= finalIndex) {
            int middleIndex = (initialIndex + finalIndex) / 2;

            Product middleObject = (Product) inventory.get(middleIndex);

            Field field = middleObject.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);

            Object middleValue = field.get(middleObject);

            int comparison;
            if (middleValue instanceof Double && value instanceof Double) {
                comparison = Double.compare((Double) middleValue, (Double) value);
            }else {
                throw new IllegalArgumentException("Cannot compare " + middleValue.getClass().getSimpleName() + " with " + value.getClass().getSimpleName());
            }
            if (comparison >= 0) {
                minIndex = middleIndex;
                finalIndex = middleIndex - 1;
            } else {
                initialIndex = middleIndex + 1;
            }
        }
        return minIndex;
    };
    public void printRangeCategory(int index, Categories category){
        int minIndex=index;
        for(int i = index-1; i>0; i--){
            if(inventory.get(i).getCategories()!=category){
                break;
            } else {
             minIndex = i;
            }
        }
        for(int i = minIndex; i<inventory.size(); i++){
            if(inventory.get(i).getCategories()!=category){
                break;
            } else {
                System.out.println(inventory.get(i).toString());
            }
        }
    };
    public void printRangeName(int index, String name){
        int minIndex=index;
        for(int i = index-1; i>0; i--){
            if(inventory.get(i).getName().equals(name)){
                minIndex = i;
            } else {
                break;
            }
        }
        for(int i = minIndex; i<inventory.size(); i++){
            if(inventory.get(i).getName().equals(name)){
                System.out.println(inventory.get(i).toString());
            } else {
                System.out.println(inventory.get(i).toString());
                break;
            }
        }
    };

    public void printRangePrice(int index, double max){
        if(index == -1){
            return;
        }
        int minIndex=index;
        for(int i = minIndex; i<inventory.size(); i++){
            if(inventory.get(i).getPrice()<=max){
                System.out.println(inventory.get(i).toString());
            } else {
                break;
            }
        }
    }
    public void printRangeSales(int index, double max){
        if(index == -1){
            return;
        }
        int minIndex=index;
        for(int i = minIndex; i<inventory.size(); i++){
            if(inventory.get(i).getTotalSales()<=max){
                System.out.println(inventory.get(i).toString());
            } else {
                break;
            }
        }
    }

}
