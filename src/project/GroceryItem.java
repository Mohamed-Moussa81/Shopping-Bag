package project;

/**
 * GroceryItem class, used to Model GroceryItems by defining certain attributes.
 * Every grocery item has a name, a price, and a boolean value to tell whether
 * it is taxable or not.
 * 
 * @author Christian Rodriguez, Mohamed Moussa
 *
 */
public class GroceryItem {
	private String name;
	private double price;
	private boolean taxable;

	/**
	 * Constructor for GroceryItem, includes a name, a price, and whether it is
	 * taxable.
	 * 
	 * @param name    of the grocery item
	 * @param price   of the the grocery item
	 * @param taxable whether the grocery item is taxable or not
	 */
	public GroceryItem(String name, double price, boolean taxable) {
		this.name = name;
		this.price = price;
		this.taxable = taxable;
	}

	/**
	 * Default constructor for GroceryItem.
	 */
	public GroceryItem() {
		this.name = null;
		this.price = 0;
		this.taxable = false;
	}

	/**
	 * Determines if two Objects are equal. 
	 * Check whether all data fields values of the two GroceryItem objects are equal.
	 * If the object isn't an instance of GroceryItem returns false.
	 * 
	 * @param obj to be checked.
	 * @return true if the given object has all data fields and values of the
	 *         grocery item, otherwise, return false
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof GroceryItem)) {
			return false;
		}
		GroceryItem test = (GroceryItem) obj;

		if (!(this.name.equals(test.name))) {
			return false;
		}
		if (this.price != test.price) {
			return false;
		}
		if (this.taxable != test.taxable) {
			return false;
		}

		return true;
	}

	/**
	 * Return the string representation of the grocery item.
	 * 
	 * @return string form of the entire grocery item
	 */
	public String toString() {
		String toStringRep = this.name + ": ";
		String formatPrice = String.format("%.2f", this.price);
		if (this.taxable) {
			toStringRep += "$" + formatPrice + " " + " : " + " is " + " taxable";

		}

		else {
			toStringRep += "$" + formatPrice + " " + " : " + "tax " + "free";
		}
		return toStringRep;

	}

	/**
	 * Return the grocery item's name
	 * 
	 * @return string of the grocery item's name
	 */
	public String getItemName() {
		return this.name;
	}

	/**
	 * Return the grocery item's price
	 * 
	 * @return double of the grocery item's price
	 */
	public double getItemPrice() {
		return this.price;
	}

	/**
	 * Return whether the grocery item is taxable or not
	 * 
	 * @return boolean of whether the grocery item can be taxed
	 */
	public boolean isItemTaxable() {
		return this.taxable;
	}

}