package model;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shop {
    private ArrayList<Product> inventory;

    private ArrayList<Order> orders;


    public Shop(){
        this.inventory = new ArrayList<>();
        this.orders = new ArrayList<>();
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

    

   

    public void addProductToOrder(int index, String productName, int quantity){
        Order order = orders.get(index);

        if(order == null){
            System.out.println("The buyer does not have a open order");
            return;
        }
        Product productFound = null;
        for(Product product : inventory){
            if(product.getName().equalsIgnoreCase(productName)){
                productFound = product;
                break;
            }
        }
        if(productFound == null){
            System.out.println("This Product is not available right now");
            return;
        }

        order.addProduct(productFound, quantity);
        System.out.println("Product added");

    };


    public void removeProductToOrder(int index, String eliminatedProduct, int quantity) {
        Order order = orders.get(index);

        if (order == null) {
            System.out.println("The buyer does not have an open order.");
            return;
        }
        order.eliminate(eliminatedProduct, quantity);
    }
        
    public void checkOrder(int index) {
        Order order = orders.get(index);
    
        if (order == null) {
            System.out.println("The buyer doesnt have a order open");
            return;
        }
    
        order.calculateTotalPrice();
        System.out.println(order.toString());
    
        List<OrderedItem> products = order.getItems();
        for (OrderedItem product : products) {
            System.out.println(product.productData().toString());
        }
    }

    public void deleteOrder(int index) {
        Order orderFound = orders.get(index);
    
        if (orderFound != null) {
            orders.remove(orderFound);
            System.out.println("Pedido eliminado correctamente.");
        } else {
            System.out.println("El pedido no existe.");
        }
    }
    
    
    

    
    public boolean stockCheck(int orderP){
        Order order = orders.get(orderP);
        List<OrderedItem> products = order.getItems();
        for (OrderedItem product : products) {
            for(int i = 0; i<inventory.size(); i++){
                if(product.getProduct().getName().equals(inventory.get(i).getName())){
                    if(inventory.get(i).getQuantity()<product.getQuantity()){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    };

    public void processOrder(int orderP){
        Order order = orders.get(orderP);
        List<OrderedItem> products = order.getItems();
        for (OrderedItem product : products) {
            for(int i = 0; i<inventory.size(); i++){
                if(product.getProduct().getName().equals(inventory.get(i).getName())){
                    if(inventory.get(i).getQuantity()-product.getQuantity()>0){
                        inventory.get(i).setQuantity(inventory.get(i).getQuantity()-product.getQuantity());
                    } else {
                        inventory.remove(inventory.get(i));
                    }
                    break;
                }
            }
        }
        order.calculateTotalPrice();
        return;
    };

    public <T> void binarySearchO(int nameBuyer, T value, T value2) throws NoSuchFieldException, IllegalAccessException {
        int comp = 0;
        int index;
        String attName = "";
        switch (nameBuyer){
            case 1:
                attName = "name";
                printOrder(binarySearchPSO(attName, value,  orders.get(0).comparatorForUse(nameBuyer)), ((String) value));
                break;

            case 2:
                attName = "totalPrice";
                printTotalPrice(binarySearchPMinO(attName, value, orders.get(0).comparatorForUse(nameBuyer)), ((Double)value2));
                break;

            case 3:
                attName = "totalSales";
                printRangeSales(binarySearchPMin(attName, value, inventory.get(0).comparatorForUse(nameBuyer)), ((Double)value2));
                break;
        }
        return;
    };

    public int searchOrdersByDate(java.util.Date dateMin, java.util.Date dateMax) {
        int inicio = 0;
        int fin = orders.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            java.util.Date fechaPedido = orders.get(medio).getDate();

            if (fechaPedido.equals(dateMin) || fechaPedido.equals(dateMax)) {
                return medio; // Pedido encontrado en los extremos del rango
            }

            if (fechaPedido.after(dateMin) && fechaPedido.before(dateMax)) {
                return medio; // Pedido encontrado dentro del rango
            }

            if (fechaPedido.compareTo(dateMax) > 0) {
                fin = medio - 1; // Buscar en la mitad izquierda
            } else {
                inicio = medio + 1; // Buscar en la mitad derecha
            }
        }

        return -1; // Pedido no encontrado
    }





    public <T> int binarySearchPSO(String attributeName, T value, Comparator<Order> comparator) throws NoSuchFieldException, IllegalAccessException {
        int initialIndex = 0;
        int finalIndex = orders.size();
        Collections.sort(orders,comparator);

        while (initialIndex <= finalIndex) {
            int middleIndex = (initialIndex + finalIndex) / 2;

            Order middleObject = (Order) orders.get(middleIndex);

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

    
    public <T> int binarySearchPMinO(String attributeName, T value, Comparator<Order> comparator) throws NoSuchFieldException, IllegalAccessException {
        int initialIndex = 0;
        int finalIndex = orders.size()-1;
        Collections.sort(orders,comparator);
        int minIndex=-1;
        while (initialIndex <= finalIndex) {
            int middleIndex = (initialIndex + finalIndex) / 2;

            Order middleObject = (Order) orders.get(middleIndex);

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

    public void printTotalPrice(int index, double max){
        System.out.println(index);
        if(index == -1){
            return;
        }
        int minIndex=index;
        for(int i = minIndex; i<orders.size(); i++){
            if(orders.get(i).getTotalPrice()<=max){
                System.out.println(orders.get(i).toString());
            } else {
                break;
            }
        }
    }

    public void printOrder(int index, String name){
        int minIndex=index;
        for(int i = index-1; i>0; i--){
            if(orders.get(i).getName().equals(name)){
                minIndex = i;
            } else {
                break;
            }
        }

        for(int i = minIndex; i<orders.size(); i++){
            if(orders.get(i).getName().equals(name)){
                System.out.println(orders.get(i).toString());
            } else {
                System.out.println(orders.get(i).toString());
                break;
            }
        }
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