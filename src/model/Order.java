package model;

import com.sun.org.apache.bcel.internal.generic.LoadClass;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Order {
    private String name;
    private ArrayList<OrderedItem> items;
    private double totalPrice;
    private LocalDate date;

    public Order(String name) {
        this.name = name;
        this.items = new ArrayList<OrderedItem>();
        this.totalPrice = 0;
        this.date = LocalDate.now();
        System.out.println(date.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OrderedItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderedItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (OrderedItem order : items) {
            total += order.getProduct().getPrice()*order.getQuantity();
        }
        this.totalPrice = total;
        return total;
    }

    public Comparator<Order> comparatorForUse(int chosenComparator){
        switch (chosenComparator){
            case 1:
                Comparator<Order> nameComparator = Comparator.comparing(order -> order.getName());
                return nameComparator;
            case 2:
                Comparator<Order> totalPriceComparator = Comparator.comparing(order -> order.getTotalPrice());
                return totalPriceComparator;
            case 3:
                Comparator<Order> dateComparator = Comparator.comparing(order -> order.getDate());
                return dateComparator;
        }
        return null;
    };

    public void addProduct(Product productFound, int quantity) {
        items.add(new OrderedItem(productFound, quantity));
    }

    public void eliminate(String product, int quantity) {
        OrderedItem remP = null;
        for(int i = 0; i< items.size(); i++){
            if(items.get(i).getProduct().getName().equals(product)){
                remP = items.get(i);
                break;
            }
        }
        if(remP!=null){
            if(remP.getQuantity()>quantity){
                remP.setQuantity(remP.getQuantity()-quantity);
            } else if (remP.getQuantity()==quantity){
                items.remove(remP);
            } else {
                System.out.println("You are removing more than you have");
            }
            return;
        }
        return;
    }

    public void cancelOrder(){
        items.clear();
        System.out.println("Pedido eliminado con exito");
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
