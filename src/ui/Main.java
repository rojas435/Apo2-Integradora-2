package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;




public class Main {

    private Scanner reader;

	private Shop shop;
	

	public Main() {
		reader = new Scanner(System.in); 
        Shop shop = new Shop();
	}

	public static void main(String[] args) {
			Main main = new Main();

			int option = -1; 
			do{
				option = main.getOptionShowMenu(); 
				main.executeOption(option);

			}while(option != 0);

		}

	public int getOptionShowMenu(){
			int option = 0; 
			printMenu();

			option = validateIntegerOption(); 

			return option; 
	}

	public void printMenu(){
			System.out.print(
                "\n<<<<< Welcome to Mercado Libre >>>>>\n"+	
                "1. Add Product\n"+
				"2. Eliminate product\n"+
				"3. Search\n"+
				"4. Create Order\n"+
				"5. Load Inventory\n"+
				"6. Save Inventory\n"+
				"0. Exit \n"+
				"Opcion: ");  
	}

		public void executeOption(int option){

			switch(option){
				case 1:
					addProduct();
					break;
				case 2:
					eliminateProduct();
					break;
				case 3:
					search();
					break;
				case 4:
					createOrder();
					break;
				case 5:
					loadInventory();
					break;
				case 6:
					saveInventory();
					break;
				case 0:
					System.out.println("Exit program.");
					break;
				default:
					System.out.println("Invalid Option");
					break;
			}
		}
	
	/**
	 * @param: Option that gives the user
	 * @return: Validates the option and if the user gives a number that doesnt exist will give (Invalid Option) or even with letters (Invalid Option)
	 */	
	public int validateIntegerOption(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	public void addProduct(){
		reader.nextLine();
		System.out.println("Ingresame el nombre del producto: ");
		String name  = reader.nextLine();

		System.out.println("Dame una descripcion del producto: ");
		String description = reader.nextLine();

		System.out.println("Dame el precio del producto: ");
		double price = reader.nextDouble();
		reader.nextLine();

		System.out.println("Dame cuantos productos se van a ingresar: ");
		int quantity = reader.nextInt();

		System.out.print("Dime el tipo de producto que es"+
		"\n1. Libro"+
		"\n2. Electronico"+
		"\n3. Ropa"+
		"\n4. Accesorios"+
		"\n5. Alimentos"+
		"\n6. Bebidas"+
		"\n7. Papeleria"+
		"\n8. Deportes"+
		"\n9. Productos de belleza"+
		"\n10. Cuidado personal"+
		"\n11. Juguetes y juegos \n"+
		"Opcion:");

		int typeOfProduct = reader.nextInt();

		System.out.println("Dime cuantas veces se han comprando este producto");
		int totalSales = reader.nextInt();

		if(shop.addProduct(name, description, price, quantity, typeOfProduct,totalSales)){
			System.out.println("Se ha añadido exitosamente");
		}else{
			System.out.println("Error, no se ha podido añadir");
		}
	}



	public void search(){
		System.out.println("Tell me what you want to search"+
		"\n1. Search Product"+
		"2. Search Order\n"+
		"Option: ");
		int option = reader.nextInt();

		switch(option){
			case 1:
			System.out.print("Dime por que caracteristica deseas buscar el producto "+
			"\n1. Name"+
			"\n2. Price"+
			"\n3. Categorie"+
			"\n4. Number of times of buying"+
			"5. Return to option\n"+
			"Option:");
			int type = reader.nextInt();
			switch(type){
				case 1:
				reader.nextLine();
				System.out.println("Dime el nombre del producto");
				String name = reader.nextLine();
				
				break;


			}

			case 2:
			System.out.println("Tell me for what characteristics you want to search: "+
			"\n1. Name of buyer"+
			"\n2. Total Price"+
			"3. Date of purchase\n"+
			"Option");

			int option2 = reader.nextInt();

			switch(option2){
				case 1:
				System.out.println("Tell me the name of the buyer: ");
				String nameBuyer = reader.nextLine();
				System.out.println();
				break;

				case 2:
				System.out.println("");
				System.out.println();
				break;

				case 3:
				System.out.println("Tell me date of the purchase");
				String date = reader.nextLine();
				System.out.println();
				break;

			}

			break;
		}
		
	}

	public void eliminateProduct(){
		System.out.println("\nPlease choose a product to eliminate");
		shop.showInv();
		String productName = reader.nextLine();
		shop.eliminateProduct(productName);
		System.out.println("Producto eliminado exitosamente.");
	};

	public void createOrder() {
		System.out.println("Whats your name:");
		String name = reader.nextLine();
		int orderP = shop.createOrder(name);
		int orderOpcion = 0;
		do {
			System.out.println("\nRealizar pedido:");
			System.out.println("1. Agregar producto al pedido");
			System.out.println("2. Eliminar producto del pedido");
			System.out.println("3. Ver pedido actual");
			System.out.println("4. Realizar pedido");
			System.out.println("5. Cancelar pedido");

			orderOpcion = reader.nextInt();
			int quantity = 0;

			switch (orderOpcion) {
				case 1:
					System.out.println("\nProduct Name:");
					String addedProduct = reader.nextLine();
					System.out.println("Quantity of Product:");
					quantity = reader.nextInt();
					shop.addProductToOrder(orderP, addedProduct, quantity);
					break;
				case 2:
					System.out.println("\nProduct Name:");
					String eliminatedProduct = reader.nextLine();
					System.out.println("Quantity of Product:");
					quantity = reader.nextInt();
					shop.removeProductToOrder(orderP, eliminatedProduct, quantity);
					break;
				case 3:
					shop.checkOrder(orderP);
					break;
				case 4:
					if (shop.stockCheck(orderP)==true) {
						shop.processOrder(orderP);
					}else{
						System.out.println("Theres more products in your order than in the inventory");
						orderOpcion=0;
					} ;
				case 5:
					break;
			}
		} while (orderOpcion != 5 || orderOpcion != 4);
		return;
	}

	public void loadInventory(){};

	public void saveInventory(){};
}