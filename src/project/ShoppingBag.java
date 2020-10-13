package project;

/**
 * This is the ShoppingBag class, a container class for the GroceryItem class.
 * In this class, grocery items can be held as a list of items which can grow
 * with an initial capacity of 5 (automatically grows past this limit). Items
 * can be added, removed, and the contents of the bag can be displayed. The
 * sales price and tax can be listed as well.
 * 
 * @author Christian Rodriguez, Mohamed Moussa
 *
 */
public class ShoppingBag {
	private static final double TAX = 0.06625; // Sales tax in New Jersey. This is a constant and cannot be changed.
	private GroceryItem[] bag; // Array-based implementation of the bag
	private int size; // Number of items currently in the bag
	private int capacity; // Capacity of items that can be held in the bag

	/**
	 * Default constructor for the ShoppingBag class
	 * (The only constructor for ShoppingBag).
	 * Instantiates the GroceryItem array, and sets initial capacity to 5
	 */
	public ShoppingBag() {
		bag = new GroceryItem[5];
		this.size = 0;
		this.capacity = 5;

	}

	/**
	 * Find an item in the grocery item bag given the GroceryItem object.
	 * Find the index of the item and return it.
	 * Helper Method used in removing items form the bag.
	 * @param item  GroceryItem object to search for
	 * @return Array index if item was found, -1 otherwise.
	 */
	private int find(GroceryItem item) {
		int i;
		for (i = 0; i < this.size; i++) {

			if (this.bag[i].equals(item)) {

				return i;
			}
		}
		return -1;
	}

	/**
	 * Grows the capacity of the grocery item bag.
	 * Increase the capacity by +5, and copies GroceryItems into new bag. 
	 */
	private void grow() {
		GroceryItem[] temporaryBag = new GroceryItem[this.size]; // Create a temporary bag to copy all the original grocery item bag items to the temporary bag
																	
		int i;
		for (i = 0; i < this.size; i++) {
			temporaryBag[i] = this.bag[i];
		}
		// Increase the capacity of the bag by 5 and creates a new grocery item bag with this capacity
		this.capacity += 5;
		this.bag = new GroceryItem[this.capacity];
		// Copy all the items back to the new bag with it's increased capacity
		for (i = 0; i < this.size; i++) {
			this.bag[i] = temporaryBag[i];
		}
	}

	/**
	 * Adds an item to the grocery item bag. 
	 * If the capacity of the bag is met, the bag capacity is increased.
	 * @param item   GroceryItem instance to be added to the grocery item bag
	 */
	public void add(GroceryItem item) {
		this.bag[size] = item;
		this.size++;
		if (size == capacity) {
			this.grow();
		}
	}

	/**
	 * Removes an item from the grocery item bag. 
	 * Finds the index of the item to be
	 * removed and moves the last item in the array to this index. Then the item reference at the last index is set to NULL
	 * @param item to remove from the grocery item bag
	 * @return true if the item was found and removed, false otherwise.
	 */
	public boolean remove(GroceryItem item) {
		if (this.size == 0) {
			return false;
		}
		int itemIndex = find(item);
		if (itemIndex == -1) {
			return false;
		}
		this.size--;
		this.bag[itemIndex] = this.bag[size];
		this.bag[size] = null;
		return true;
	}

	/**
	 * Returns the total sales price of the items in the grocery item bag.
	 * @return sales price of the items in the grocery item bag.
	 */
	public double salesPrice() {

		int i;
		double salesPrice = 0;
		GroceryItem baggedItem;
		for (i = 0; i < this.size; i++) {
			baggedItem = this.bag[i];
			salesPrice += baggedItem.getItemPrice();

		}

		return salesPrice;
	}

	/**
	 * Returns the total sales Tax of the taxable items in the grocery item bag.
	 * 
	 * @return sales tax of the items in the grocery item bag
	 */
	public double salesTax() {
		double tax;
		int i;
		double totalTax = 0;
		GroceryItem baggedItem;
		for (i = 0; i < this.size; i++) {
			baggedItem = this.bag[i];
			if (baggedItem.isItemTaxable()) {
				tax = TAX * baggedItem.getItemPrice();
				totalTax += tax;
			}
		}
		return totalTax;
	}

	/**
	 * Print the items in the grocery item bag.
	 * If the bag is empty prints "Bag is empty"
	 */
	public void print() {
		if (this.size == 0) {
			System.out.println("Bag is empty.");
			return;
		}
		int i;
		for (i = 0; i < this.size; i++) {
			System.out.println("." + this.bag[i].toString());
		}

	}

	/**
	 * Test bed main for ShoppingBag
	 * Tests the methods: add(), remove(), grow(), salesTax() for different cases
	 * @param args for test bed main
	 */
	public static void main(String[] args) {
		// Create 3 shopping bags, serves as a constructor test.
		
		// CASE 1: 1 item in a bag. Bag 1 will have 1 item.
		ShoppingBag bag1 = new ShoppingBag();
		
		// CASE 2: Multiple items in a bag. Bag 2 will have 4 items.
		ShoppingBag bag2 = new ShoppingBag();
		
		// CASE 3: No items in a bag. Bag 3 will be empty.
		ShoppingBag bag3 = new ShoppingBag();
		
		// Create 5 grocery items 
		GroceryItem groceryItem1 = new GroceryItem("Pumpkin", 3.99, true);
		GroceryItem groceryItem2 = new GroceryItem("Bread", 0.99, false);
		GroceryItem groceryItem3 = new GroceryItem("Water", 2.99, true);
		GroceryItem groceryItem4 = new GroceryItem("Eggs", 4.99, false);
		GroceryItem groceryItem5 = new GroceryItem("Lettuce", 5.00, true);

		// Test add() and print()
		System.out.println("\tTESTING add() AND print():\n\n");

		System.out.printf("Adding %s to bag 1\n\n", groceryItem1.getItemName());

		bag1.add(groceryItem1);
		
		System.out.println("Printing the contents of bag 1:\n");
		
		bag1.print();
		
		System.out.printf("\nAdding %s to bag 2\n", groceryItem2.getItemName());
		System.out.printf("Adding %s to bag 2\n", groceryItem3.getItemName());
		System.out.printf("Adding %s to bag 2\n", groceryItem4.getItemName());
		System.out.printf("Adding %s to bag 2\n\n", groceryItem5.getItemName());
		
		bag2.add(groceryItem2);
		bag2.add(groceryItem3);
		bag2.add(groceryItem4);
		bag2.add(groceryItem4);

		System.out.println("Printing the contents of bag 2:\n");

		bag2.print();
		
		System.out.println("\nPrinting the contents of bag 3:\n");

		bag3.print();
		
		// Test remove()
		System.out.println("\n\tTESTING remove():\n");
		
		// Case 1: Remove 1 item
		System.out.println("Removing an item from bag 1\n");
		System.out.println("Item removed: " + bag1.remove(groceryItem1));
		
		System.out.println("Printing the contents of bag 1:\n");

		
		bag1.print();

		
		// Case 2: Remove multiple items
		System.out.println("Removing items from bag 2\n");
		System.out.println("Item removed: " + bag2.remove(groceryItem2) +"\n");
		System.out.println("Item removed: " + bag2.remove(groceryItem3) + "\n");

		System.out.println("Printing the contents of bag 2:\n");

		bag2.print();
		
		// Case 3: Try to remove from an empty bag
		System.out.println("\n Testing removing item from an empty bag \n");
		System.out.println("\nRemoving an item from bag 3\n");
		System.out.println("Item removed: " +  bag3.remove(groceryItem2));
				
		System.out.println("Printing the contents of bag 3:\n");

		bag3.print();
				
		// Case 4: Try to remove an item that is not foun
		System.out.println("\n Testing removing item not in the bag \n");
		System.out.println("Removing item from bag 2\n");
		System.out.println("Item removed: " +bag2.remove(groceryItem2));
		
		System.out.println("\nPrinting the contents of bag 2:\n");

		bag2.print();
		
		// Reinsert item to bag 1 and 2 
		
		bag1.add(groceryItem1);
		bag2.add(groceryItem2);
		bag2.add(groceryItem3);


		// Test grow
		System.out.println("\n\tTESTING grow():");
		
		System.out.printf("\nInitial capacities\nbag 1: %d\n", bag1.capacity);
		System.out.printf("bag 2: %d\n", bag2.capacity);
		System.out.printf("bag 3: %d\n\n", bag3.capacity);

		// Case 1: Grow with 1 item bag
		bag1.grow();
		System.out.println("Bag 1 size after grow (item reinserted): " + bag1.capacity + "\n");
		
		// Case 2: Grow with multiple item bag
		bag2.grow();
		System.out.println("Bag 2 size after grow (items reinserted): " + bag2.capacity + "\n");

		// Case 3: Grow an empty bag
		bag3.grow();
		System.out.println("Bag 3 size after grow: " + bag3.capacity + "\n");
		
		System.out.println("\tTESTING salesTax():\n");

		
		// Test salesTax
		
		// Case 1: 1 Item bag sales tax
		System.out.printf("Bag 1 sales tax: %.2f \n", bag1.salesTax());
		
		// Case 2: Multiple Item bag sales tax
		System.out.printf("Bag 2 sales tax: %.2f \n", bag2.salesTax());
		
		// Case 3: Empty bag sales tax
		System.out.printf("Bag 3 sales tax: %.2f \n", bag3.salesTax());

	}
}