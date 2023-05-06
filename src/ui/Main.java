package ui;

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
                "1. Agregar Producto\n"+
				"2. Buscar producto\n"+
				"0. Exit \n"+
				"Opcion: ");  
	}

		public void executeOption(int option){

			switch(option){
				case 1:
					addProduct();
					break;
				case 2:
					searchProduct();
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
	//test git
	public void searchProduct(){
		System.out.print("Dime por que caracteristica deseas buscar el producto "+
		"\n1. Nombre"+
		"\n2. Precio"+
		"\n3. Categoria"+
		"4. Numero de veces comprado\n"+
		"Opcion:");

		int type = reader.nextInt();

		switch(type){
			case 1:
			reader.nextLine();
			System.out.println("Dime el nombre del producto");
			String name = reader.nextLine();
			System.out.println(shop.lookForProduct(name));
			//System.out.println(controller.msjMethod(name));
			break;
		}
	}
}