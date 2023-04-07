import java.util.Scanner;
import model.*;

public class Esqueleto {
    private Scanner reader;
	

	public Esqueleto() {
		reader = new Scanner(System.in); 
        //private "Nombre del controlador" "Palabra clave para representar el controlador"
	}
	public static <ET> void printArray(ET[] inputArray){
		for(ET element: inputArray){
			System.out.print(element + "");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Integer[] intArray = {1,2,3,4,5};
		Double[] doubleArray = {1.1,1.2,1.3};
		Character[] charArray = {'N','i','c','o'};


		System.out.println("InterArray contais: ");
		printArray(intArray);
		System.out.println("Double array contains: ");
		printArray(doubleArray);
		System.out.println();
		
	}
}	