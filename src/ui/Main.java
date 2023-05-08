package ui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;




public class Main {

    private Scanner reader;

	private Shop shop;
	

	public Main() {
		reader = new Scanner(System.in);
		this.shop = new Shop();

	}

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
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
				"7. Show Inventory\n"+
				"0. Exit \n"+
				"Opcion: ");  
	}

		public void executeOption(int option) throws NoSuchFieldException, IllegalAccessException {

			switch(option){
				case 1:
					addProduct();
					shop.showInv();
					break;
				case 2:
					eliminateProduct();
					shop.showInv();
					break;
				case 3:
					search();
					break;
				case 4:
					createOrder();
					break;
				case 5:
					shop.jsonInventoryInfo("inventoryInfo.json");
					break;
				case 6:
					shop.writeGsonInventory();
					break;
				case 7:
					shop.showInv();
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
	public int validateIntegerOption() {
		int option = 0;

		if (reader.hasNextInt()) {
			option = reader.nextInt();
		} else {
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
		"\n1. BOOKS"+
		"\n2. ELECTRONICS"+
		"\n3. CLOTHES"+
		"\n4. ACCESORIES"+
		"\n5. FOOD"+
		"\n6. DRINKS"+
		"\n7. STATIONERY"+
		"\n8. SPORTS"+
		"\n9. BEAUTY"+
		"\n10. PERSONAL"+
		"\n11. TOYS "+
		"\n12. GAMES"+
		"\n13. KIDS\n"+
		"Opcion:");

		int typeOfProduct = reader.nextInt();

		System.out.println("Dime cuantas veces se han comprando este producto");
		int totalSales = reader.nextInt();

		System.out.println("If product is already in inventory do you wish to just add amount");
		int option = reader.nextInt();

		if(shop.addProduct(name, description, price, quantity, typeOfProduct,totalSales, option)){
			System.out.println("Se ha añadido exitosamente");
		}else{
			System.out.println("Error, no se ha podido añadir");
		}
	}



	public void search() throws NoSuchFieldException, IllegalAccessException {
		System.out.println("Tell me what you want to search"+
		"\n1. Search Product\n"+
		"2. Search Order\n"+
		"Option: ");
		int option = reader.nextInt();

		switch(option){
			case 1:
			System.out.print("What characteristic to you wanna search for: "+
			"\n1. Name"+
			"\n2. Price"+
			"\n3. Category"+
			"\n4. Number of times of buying"+
			"5. Return to option\n"+
			"Option:");
			int type = reader.nextInt();
			switch(type){
				case 1:
					reader.nextLine();
					System.out.println("product name");
					String valueN = reader.nextLine();
					System.out.println("In (1) ascending or (2) descending order:");
					int order = reader.nextInt();
					shop.binarySearchP(type, valueN, null, order);
					break;
				case 2:
					reader.nextLine();
					System.out.println("Min Price");
					double valueMinP = reader.nextInt();
					System.out.println("Max Price");
					double valueMaxP = reader.nextInt();
					System.out.println("In (1) ascending or (2) descending order:");
					order = reader.nextInt();
					shop.binarySearchP(type, valueMinP, valueMaxP, order);
					break;
				case 3:
					reader.nextLine();
					System.out.println("What category");
					int valueC = reader.nextInt();
					System.out.println("In (1) ascending or (2) descending order:");
					order = reader.nextInt();
					shop.binarySearchP(type, valueC, null, order);
					break;
				case 4:
					reader.nextLine();
					System.out.println("Min Sales");
					double valueMinS = reader.nextInt();
					System.out.println("Max Sales");
					double valueMaxS = reader.nextInt();
					System.out.println("In (1) ascending or (2) descending order:");
					order = reader.nextInt();
					shop.binarySearchP(type, valueMinS, valueMaxS, order);
					break;
				case 5:
					break;

			}
			break;

			case 2:
			System.out.println("Tell me for what characteristics you want to search: "+
			"\n1. Name of buyer\n"+
			"2. Total Price\n"+
			"3. Date of purchase\n"+
			"Option");

			int option2 = reader.nextInt();

			switch(option2){
				case 1:
				System.out.println("Tell me the name of the buyer: ");
				reader.nextLine();
				String nameBuyer = reader.nextLine();
				System.out.println("In (1) ascending or (2) descending order:");
				int order = reader.nextInt();
				shop.binarySearchO(option2,nameBuyer,null, order);
				break;

				case 2:
				reader.nextLine();
				System.out.println("Min Price");
				double valueMinP = reader.nextInt();
				System.out.println("Max Price");
				double valueMaxP = reader.nextInt();
				System.out.println("In (1) ascending or (2) descending order:");
				order = reader.nextInt();
				shop.binarySearchO(option2, valueMinP, valueMaxP, order);
				break;

				case 3:
				System.out.println("Tell me date of the purchase:");
				int day = reader.nextInt();
				int month = reader.nextInt();
				int year = reader.nextInt();
				LocalDate date = LocalDate.of(year,month,day);
				System.out.println("In (1) ascending or (2) descending order:");
				order = reader.nextInt();
				shop.binarySearchO(option2, date, null, order);
				break;

			}

			break;
		}
		return;
		
	}

	public void eliminateProduct(){
		System.out.println("\nPlease choose a product to eliminate");
		shop.showInv();
		reader.nextLine();
		String productName = reader.nextLine();
		System.out.println("\nHow many do you wish to remove");
		int amount = reader.nextInt();
		shop.eliminateProduct(productName, amount);
	};

	public void createOrder() {
		reader.nextLine();
		System.out.println("Whats your name:");
		String name = reader.nextLine();
		int index = shop.createOrder(name);
		int leave = 0;
		while (leave!=5){
			System.out.println("\nRealizar pedido: \n"+
			"1. Add product to order\n"+
			"2. Remove producto from order\n"+
			"3. Check current order\n"+
			"4. Process order\n"+
			"5. Cancel order\n"+
			"6. Show inventory\n"+
			"Option:");

			int orderOpcion = reader.nextInt();
			int quantity = 0;

			switch (orderOpcion) {
				case 1:
					System.out.println("\nProduct Name:");
					reader.nextLine();
					String addedProduct = reader.nextLine();
					System.out.println("Quantity of Product:");
					quantity = reader.nextInt();
					shop.addProductToOrder(index, addedProduct, quantity);
					break;
				case 2:
					System.out.println("\nProduct Name:");
					reader.nextLine();
					String eliminatedProduct = reader.nextLine();
					System.out.println("Quantity of Product:");
					quantity = reader.nextInt();
					shop.removeProductToOrder(index, eliminatedProduct, quantity);
					break;
				case 3:
					shop.checkOrder(index);
					break;
				case 4:
					boolean pass = shop.stockCheck(index);
					if (pass==true) {
						shop.processOrder(index);
						shop.showInv();
						leave = 5;
					}else if (pass==false){
						System.out.println("Theres more products in your order than in the inventory");
					}
					break;
				case 5:
					shop.deleteOrder(index);
					break;
				case 6:
					shop.showInv();
					break;
			}
		};
		return;
	}
}