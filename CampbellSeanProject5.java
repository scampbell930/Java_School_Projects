
import java.util.Scanner;

/*
 * Sean Campbell, CS1450 - RL2, Program 5
 */

public class CampbellSeanProject5 {

	public static void main(String[] args) {
		
		// Creating a stack to store Cars
		Lstack carStack = new Lstack();

		// Creating a scanner to read input
		Scanner input = new Scanner(System.in);

		// Checking that the number of cars is less than 7
		while (carStack.size() < 7) {

			// Reading input
			System.out.print("Enter license plate number: ");
			String licensePlateNumber = input.nextLine();

			// Adding license plate to Car
			Car car = new Car(licensePlateNumber);

			// Adding car to stack
			carStack.push(car);

		}

		// Displaying carStack
		System.out.println("");
		System.out.println("Car Stack");
		carStack.display();
		System.out.println("");

		// Creating a queue to store Cars
		Lqueue carQueue = new Lqueue();

		// Checking that size is less than 5 then adding car
		while (carQueue.size() < 5) {

			// Reading input
			System.out.print("Enter license plate number: ");
			String licensePlateNumber = input.nextLine();

			// Adding license plate to Car
			Car car = new Car(licensePlateNumber);

			// Adding car to queue
			carQueue.insert(car);

		}

		// Displaying carQueue
		System.out.println("");
		System.out.println("Car Queue");
		carQueue.display();
		System.out.println("");

		// Reversing and displaying carQueue
		System.out.println("Reversed Car Queue");
		carQueue.reverseQueue();
		carQueue.display();
		System.out.println("");

		// Reversing and displaying carStack
		System.out.println("Reversed Car Stack");
		carStack.reverseStack();
		carStack.display();

		// Closing input
		input.close();

	} //main
	
} //CampbellSeanProject5

class Car {
	
	private String licensePlate;	//license plate number of car
	
	// Constructor for Car object
	public Car(String licensePlate) {
		
		// Checking to see if valid license plate number was input
		if (!setLicensePlate(licensePlate)) {
			throw new RuntimeException("Invalid license plate number: " + licensePlate);
		}
		
	} //Car constructor
	
	
	// Checks to see if a valid license plate number was given
	public boolean setLicensePlate(String licensePlate) {
		
		// Default return boolean
		boolean result = false;
		
		// Checking if a String was entered ignoring spaces
		if (licensePlate.replace(" ", "").length() > 0) {
			// Checking if String needs to be truncated and converting to uppercase
			if (licensePlate.length() > 6) {
				this.licensePlate = licensePlate.substring(0, 6).toUpperCase();
			}
			else {
				this.licensePlate = licensePlate.toUpperCase();
			}
			result = true;
		}
		
		return result;
		
	} //setLicensePlate
	
	
	// Getter for licensePlate that returns licensePlate
	public String getLicensePlate() {
		
		return licensePlate;
		
	} //getLicensePlate
	
} //Car

class Node {
	
	private Node next;	// Node referenced by current Node
	private Car car;	// Car stored in Node
	
	public Node(Car car, Node next) {
		
		this.car = car;
		this.next = next;
		
	} //Node constructor
	
	
	// Returns car
	public Car getCar() {
		
		return car;
		
	} //getCar
	
	
	// Returns next
	public Node getNext() {
		
		return next;
		
	} //getNext
	
	
	// Setter for next
	public void setNext(Node next) {
		
		this.next = next;
		
	} //setNext
	
} //Node

class Lstack {
	
	private Node top;		// Node being referenced in linked list
	private int numCars;	// Tracks the number of cars currently in stack
	
	// Constructor for stack
	public Lstack() {
		
		top = null;
		numCars = 0;
		
	} //Lstack constructor
	
	
	// Adds car to top of stack
	public void push(Car car) {
		
		top = new Node(car, top);
		numCars++;
		
	} //push
	
	
	// Removes last added car from stack and returns it
	public Car pop() {
		// Default return
		Car result;
		
		// Checking if stack is empty
		if (!isEmpty()) {
			result = top.getCar();
			top = top.getNext();
			numCars--;
		}
		else {
			result = null;
		}
		
		return result;
		
	} //pop
	

	// Checks if stack is empty, returns true or false
	public boolean isEmpty() {
		
		return top == null;
		
	} //isEmpty
	
	
	// Returns the number of elements currently in stack
	public int size() {
		
		return numCars;
		
	} //size
	
	
	// Prints stack license number horizontally with top indicator on left side
	public void display() {
		// Initializing another pointer
		Node currentNode = top;
		
		// Checking if stack is empty
		if (!isEmpty()) {	
			System.out.println("---------------------------------------------------");
			System.out.print("TOP |");
			
			// Looping through stack from top to bottom to print Cars
			while (currentNode != null) {
				// Printing license plate
				System.out.printf(" %s |", currentNode.getCar().getLicensePlate());
				currentNode = currentNode.getNext();
			}
			
			System.out.println("BOTTOM");
			System.out.println("---------------------------------------------------");
			}
		
		else {
			System.out.println("Car stack is empty");
			}
		
	} //display
	
	
	// Reverses the stack using a temporary queue
	public void reverseStack() {
		
		// Creating temporary queue to hold Cars
		Lqueue tempQueue = new Lqueue();

		// Removing Cars from stack and adding them to queue using a while loop
		while (!isEmpty()) {
			tempQueue.insert(pop());
		}

		// Removing Cars from queue and adding them back to stack using another while loop
		while (!tempQueue.isEmpty()) {
			push(tempQueue.remove());
		}
		
	} //reverseStack
	
} //Lstack

class Lqueue {
	
	private Node front, rear;	// pointers to the front and rear of the queue
	
	// Constructor for Lqueue
	public Lqueue() {

		front = null;
		rear = null;
		
	} //Lqueue constructor
	
	
	// Adds Car to the rear of the queue
	public void insert(Car car) {
		
		// Checking if queue is currently empty
		if (front == null) {
			// Set front to new Node and make rear point to the same Node as front
			front = new Node(car, null);
			rear = front;
		}
		else {
			rear.setNext(new Node(car, null));
			rear = rear.getNext();
		}
		
	} //insert
	
	
	// Removes the front car from the queue and returns it
	public Car remove() {
		// Creating a temporary pointer for front car
		Node result;
		
		// Checking if queue is empty
		if (!isEmpty()) {
			result = front;
			front = front.getNext();
		}
		else {
			result = null;
		}
		
		return result.getCar();
		
	} //remove
	
	
	// Returns true if queue is currently empty
	public boolean isEmpty() {
		
		return front == null;
		
	} //isEmpty
	
	
	// Returns the number of Cars in queue
	public int size() {
		// Initializing counter
		int count = 0;
		
		// Temp node pointing to front
		Node currentNode = front;
		
		// Loop from front to rear
		while (currentNode != null) {
			count++;
			currentNode = currentNode.getNext();
		}
		
		return count;
		
	} //size
	
	
	// Prints the queue license numbers horizontally with front at the far left
	public void display() {

		// Checking if queue is empty
		if (!isEmpty()) {
			
			// Temp node
			Node currentNode = front;
			
			System.out.println("---------------------------------------");
			System.out.print("Front |");
			
			// Looping from front to rear
			while (currentNode != null) {
				System.out.printf(" %s |", currentNode.getCar().getLicensePlate());
				currentNode = currentNode.getNext();
			}
			System.out.println("REAR");
			System.out.println("---------------------------------------");

		}
		else {
			System.out.println("Car queue is empty");
		}

	} // display
	
	
	// Reverses Car queue using temporary car stack
	public void reverseQueue() {

		// Temp stack to hold Cars
		Lstack tempStack = new Lstack();

		// Loop to iterate through queue and add Car to stack
		while (!isEmpty()) {
			tempStack.push(remove());
		}

		// Another while loop to add Cars back to queue
		while (!tempStack.isEmpty()) {
			insert(tempStack.pop());
		}
		
	} //reverseQueue
	
} //Lqueue