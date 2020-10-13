package project;

import java.util.Scanner;

/**
 * Shopping class, this class handles user input and prints the  output to Console.
 * The run method is called from the RunProject1 Class, the
 * driver class for the entire project. The run method then scans the user input
 * and passes it to the processUserInput method which contains a while loop
 * which continues to process input until the command "Q" is entered.
 * 
 * @author Mohamed Moussa, Christian Rodriguez
 */
public class Shopping {
	public static int NUMBER_OF_ITEMS_IN_BAG = 0; // Static variable that holds number of items in the bag.
	public static final int NUMBER_OF_INPUTS_FOR_ADD_REMOVE = 4; // Constant used to check if desired inputs
																			// are given

	/**
	 * Instantiates a ShoppingBag object along with Scanner instance to begin reading input.
	 * ShoppingBag object and Scanner are passed to processUserInput to begin processing input.
	 * Run() is called from the driver class, RunProject1, 
	 * and sets up the variables to be used in the
	 * other methods.
	 */
	public void run() {
		System.out.println("Lets start shopping!");
		ShoppingBag customerBag = new ShoppingBag();
		Scanner scanner = new Scanner(System.in);
		processUserInput(scanner, customerBag);
		System.out.println("Thanks for Shopping with us!");
		scanner.close();
	}

	/**
	 * Reads and processes user input from console using Scanner. 
	 * The scanner method used returns an array with white spaces as elements in the array.
	 * Thus reOrderInputs is used following any input read in order to ensure that the
	 * white spaces do not appear in the first four indices of the array. This is
	 * necessary as the commands are accessed in the while loop from indices [0-3]
	 * in a while loop is used to process user input until the command "Q" is used;
	 * inside the while loop the commands are dealt with in subsequent methods. If
	 * statements inside the while loop are used to determine method calls which
	 * then separate Invalid and valid commands.
	 * 
	 * @param scanner      object that processes Input from System.in
	 * @param customerBag, user's shopping bag
	 */
	private void processUserInput(Scanner scanner, ShoppingBag customerBag) {
		String inputs[] = scanner.nextLine().split(" ");
		reorderInputs(inputs); // Reorders input array as necessary

		while (true) {
			if (inputs.length == 0) { // Checks if user does not input a value to avoid errors in loop execution
				System.out.println("Bad command!");
				inputs = scanner.nextLine().split(" ");
				reorderInputs(inputs);
				continue;
			}
			String command = inputs[0];
			if (command.equals("P") || command.equals("Q") || command.equals("C")) { // Checks if the single letter
																						// command is valid
				evaulateSingleLetterCommand(customerBag, command);

				if (command.equals("C")) {
					customerBag = new ShoppingBag(); // Empties current ShoppingBag by instantiating a new Object
					NUMBER_OF_ITEMS_IN_BAG = 0;
				}

				if (command.equals("Q")) {
					break; // User input is no longer processed, program exits
				}
			}

			else {
				evaulateAddorRemove(scanner, customerBag, inputs, command);
			}

			inputs = scanner.nextLine().split(" ");
			reorderInputs(inputs);
		}
	}

	/**
	 * Reorders user inputs to the first four indices of the inputs array. 
	 * Assuming that if first input is valid then the rest of the inputs are valid, based off
	 * of Project Write up, then at most the first four indices contain our inputs.
	 * If it is four lettered command, else only the first index contains our input
	 * for single letter commands. This method is called exclusively from
	 * processUserInput().
	 * @param inputs array containing user inputs and possible white spaces
	 */
	private void reorderInputs(String[] inputs) {
		int i;
		int inputPosition = 0;
		for (i = 0; i < inputs.length; i++) {

			if (!inputs[i].equals("")) {
				inputs[inputPosition] = inputs[i];
				inputPosition++;
			}
		}
	}

	/**
	 * Determines which one of the single letter commands P,Q, or C was inputed.
	 * It will flag any other letter(s) as invalid input. Each command
	 * is then called by it's respective method which determines which action to
	 * take. This method is called exclusively from processUserInput().
	 * @param customerBag user's shopping bag
	 * @param command,the given user input
	 * 
	 */
	private void evaulateSingleLetterCommand(ShoppingBag customerBag, String command) {
		if (command.equals("P")) {
			evaulateP(customerBag);

		} else if (command.equals("Q")) {
			return;

		} else if (command.equals("C")) {
			evaulateC(customerBag);

		} else {
			System.out.println("Bad command!"); // If command isn't P,Q, or C then it isn't a valid single Letter
												// Command
		}
	}

	/**
	 * Evaluates Checkout command and checks out the items in current ShoppingBag.
	 * Splits the Checkout Process into three Steps:
	 * Printing Number of items in the Bag. Printing the items in the bag, and
	 * printing the sales total and tax which is handled in finalizeCheckout Uses
	 * non static print() method to print the contents of the ShoppingBag and The
	 * bag is emptied in the userProcessInput method, caller.
	 * @param customerBag object that contains current Customer's customerBag
	 */
	private void evaulateC(ShoppingBag customerBag) {
		if (NUMBER_OF_ITEMS_IN_BAG == 0) {
			System.out.println("Unable to check out, the bag is empty!");
			return;

		} else if (NUMBER_OF_ITEMS_IN_BAG == 1) {
			System.out.println("**Checking out 1 item: ");

		} else {
			System.out.println("**Checking out " + NUMBER_OF_ITEMS_IN_BAG + " items. ");
		}
		customerBag.print();
		finalizeCheckout(customerBag);
	}

	/**
	 * Determines the sales total, sales tax, and the total price to be output.
	 * Called by evaulateC() after the first two steps of the
	 * Checkout Process have been completed.
	 * @param customerBag user's Shopping bag
	 */
	private void finalizeCheckout(ShoppingBag customerBag) {
		double salesTotal = customerBag.salesPrice();
		double salesTax = customerBag.salesTax();
		double totalPrice = totalPrice(customerBag);
		String formattedSalesTotal = formattedSales(salesTotal);
		String formattedSalesTax = formattedSales(salesTax);
		String formattedTotalPrice = formattedSales(totalPrice);
		System.out.println("*Sales total: " + formattedSalesTotal);
		System.out.println("*Sales tax: " + formattedSalesTax);
		System.out.println("*Total amount paid: " + formattedTotalPrice);
	}

	/**
	 * Converts a double to a string and formats to the form. $xx.xx.
	 * This method is used by finalizeCheckout when the sales are being
	 * prepared to be output to the User.
	 * @param sales double value to formatted to monetary format
	 * @return String representation of the double properly formatted in monetary
	 *         format
	 */
	private String formattedSales(double sales) {
		String formattedSales = String.format("%.2f", sales);
		formattedSales = "$" + formattedSales;
		return formattedSales;
	}

	/**
	 * Calculates the total price Customer must pay.
	 * Uses ShoppingBag methods salesPrice() and salexTax() to determine sales Total.
	 * Helper Method to finalize Checkout
	 * @param customerBag user's Shopping bag
	 * @return double value of the total price of all items in the user's Shopping
	 *         bag, sales tax included
	 */
	private double totalPrice(ShoppingBag customerBag) {
		return customerBag.salesPrice() + customerBag.salesTax();
	}

	/**
	 * Evaluates Print comment based on number of Items in the ShoppingBag object.
	 * Prints number of item's in the bag using Global variable followed by printing
	 * each item in the ShoppingBag using the non-static print method of the
	 * ShoppingBag object to print each item in the ShoppingBag
	 * @param customerBag user's shopping bag
	 * 
	 */
	private void evaulateP(ShoppingBag customerBag) {
		if (NUMBER_OF_ITEMS_IN_BAG == 0) {
			System.out.println("The bag is empty!");
			return;
		} else if (NUMBER_OF_ITEMS_IN_BAG == 1) {
			System.out.println("**You have 1 item in the bag.");
		} else {
			System.out.println("**You have " + NUMBER_OF_ITEMS_IN_BAG + " items in the bag.");
		}
		customerBag.print();
		System.out.println("**End of list");
	}

	/**
	 * Determines which FourLetterInput is inputed,Add or Remove. 
	 * The respective method that evaluates the FourLetterInput is called,
	 * if it is an Invalid command an error is output.
	 *Assumes that inputs Following A/R are valid as stated in WriteUp.
	 * @param scanner     instance that is used to scan System.in input
	 * @param customerBag user's shopping bag
	 * @param inputs      array of inputs given by the user
	 * @param command     given command by the user
	 * 
	 */
	private void evaulateAddorRemove(Scanner scanner, ShoppingBag customerBag, String[] inputs, String command) {
		if (command.equals("A")) {
			evaulateA(scanner, customerBag, inputs);
		}

		else if (command.equals("R")) {
			evaulateR(scanner, customerBag, inputs);
		}

		else {
			System.out.println("Bad command!");
		}

	}

	/**
	 * Evaluates Add command creates GroceryItem instance based on User Input.
	 * The GroceryItem is then added to the ShoppingBag instance using the non-static add method.
	 * Since we call reOrderInputs in proccessUserInputs as necessary the inputs are in indices
	 * 0,1,2,3 respectively.
	 * Calls inputLengthChecker to determine if desired inputs are given.
	 * @param scanner       instance that is used to scan System.in input
	 * @param customerBag   user's shopping bag
	 * @param scannerInputs array of inputs given by the user
	 */
	private void evaulateA(Scanner scanner, ShoppingBag customerBag, String[] scannerInputs) {
		String[] validInputs = inputLengthChecker(scanner, scannerInputs);
		String itemName = validInputs[1];
		String itemPrice = validInputs[2];
		String itemTax = validInputs[3];
		double price = Double.parseDouble(itemPrice);
		boolean tax = Boolean.parseBoolean(itemTax);
		customerBag.add(new GroceryItem(itemName, price, tax));
		System.out.println(itemName + " added to the bag.");
		NUMBER_OF_ITEMS_IN_BAG++; // increases for each Addition of an item to keep track of current number of
									// items

	}

	/**
	 * Determines if the original Console Input for 4 letter Command is met.
	 * If not calls scanFutherInputs which scans further inputs until all 4 inputs are
	 * given and returns the inputs in a String array.
	 * If all the inputs are inputed on the first attempt the return argument,
	 * is the original scanned String array.
	 * Since the scanner inputs are always reOrdered we check sequentially,
	 * for inputs in the String array.
	 * @param scanner instance that is used to scan System.in input
	 * @param inputs  string array containing scanned inputs
	 * @return String array with the four inputs required for Add or Remove
	 */
	private String[] inputLengthChecker(Scanner scanner, String[] inputs) {
		int inputsScanned = 1; // Starts at 1 since the first Command A/R has been scanned already
		int i;
		for (i = 1; i < inputs.length; i++) {
			if (inputsScanned == NUMBER_OF_INPUTS_FOR_ADD_REMOVE) {
				return inputs;
			}
			if (!inputs[i].equals("")) {
				inputsScanned++; // String array Either contains "" or inputs, thus we increment when the inputs
									// are relevant.
			} else {
				return scanFurtherInputs(scanner, inputs, inputsScanned); // If the input isn't relevant we continue
																			// scanning

			}
		}
		if (inputsScanned == NUMBER_OF_INPUTS_FOR_ADD_REMOVE) {
			return inputs;
		}

		return scanFurtherInputs(scanner, inputs, inputsScanned);
	}

	/**
	 * Scans further inputs from user while keeping track of previous inputs.
	 * Continues to Scan until all needed inputs have been provided. Copy is used to
	 * copy the valid inputs from the scanned array to the stored array.
	 * @param scanner            instance that is used to scan System.in input
	 * @param scannerInputs      original String array containing the scanned inputs
	 * @param numOfInputsScanned number of inputs scanned
	 * @return String array containing all the inputs from the user
	 */
	private String[] scanFurtherInputs(Scanner scanner, String[] scannerInputs, int numOfInputsScanned) {
		String[] storedInputs = new String[4];
		copy(scannerInputs, storedInputs, 0, numOfInputsScanned); // Copies the inputs originally scanned to the stored
																	// array, offset is set to 0.

		while (numOfInputsScanned != NUMBER_OF_INPUTS_FOR_ADD_REMOVE) { // Continues scanning until our
																					// desired inputs are given
			scannerInputs = scanner.nextLine().split(" ");
			reorderInputs(scannerInputs);
			int validInputCount = validateInputs(scannerInputs);

			if (validInputCount != 0) {
				copy(scannerInputs, storedInputs, numOfInputsScanned, validInputCount);

			}
			numOfInputsScanned += validInputCount;
		}
		return storedInputs;
	}

	/**
	 * Determines if inputs scanned are valid inputs to be copied
	 * @param inputs string array with scanned inputs
	 * @return Integer number of valid inputs in the String array
	 */
	private int validateInputs(String[] inputs) {
		if (inputs.length == 0) {
			return 0;
		}
		int scannedInputs = 0;
		int i;

		for (i = 0; i < inputs.length; i++) {
			if (!inputs[i].equals("")) {
				scannedInputs++;
			} else {
				return scannedInputs;
			}
		}
		return scannedInputs;

	}

	/**
	 * Copies elements from array1 to array2 given a specific offset and
	 * endingIndex.
	 * @param array1      to be copied from
	 * @param array2      to be copied to
	 * @param offset      displacement index number
	 * @param endingIndex ending index position
	 */
	private void copy(String[] array1, String[] array2, int offset, int endingIndex) {
		// copies from array1 to array 2 given starting index
		int i;
		for (i = 0; i < endingIndex; i++) {
			array2[offset + i] = array1[i];
		}
	}

	/**
	 * Evaluates Remove command by creating a GroceryItem instance and removes from Shopping Bag.
	 * Calls non-static remove method that belongs to ShoppingBag.
	 * If the GroceryItem couldn't be removed a message is displayed to the user.
	 * @param scanner       takes in user input
	 * @param customerBag   user's shopping bag
	 * @param scannerInputs array of inputs given by the user
	 */
	private void evaulateR(Scanner scanner, ShoppingBag customerBag, String[] scannerInputs) {
		String[] validInputs = inputLengthChecker(scanner, scannerInputs);
		String itemName = validInputs[1];
		String itemPrice = validInputs[2];
		String itemTax = validInputs[3];
		double price = Double.parseDouble(itemPrice);
		boolean tax = Boolean.parseBoolean(itemTax);
		boolean removed = customerBag.remove(new GroceryItem(itemName, price, tax));

		if (!removed) {
			System.out.println("Unable to remove, this item is not in the bag.");
		} else {
			System.out.println(itemName + " " + itemPrice + " removed.");
			NUMBER_OF_ITEMS_IN_BAG--; // Decrements total number of items each time an item is successfully removed
		}

	}
}