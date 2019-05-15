package businessL;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public interface RestaurantProcessing {

	/**
	 * Adds a BaseProduct
	 * 
	 * @param id
	 *            Must be positive and must not repeat itself
	 * @param name
	 *            Must be set
	 * @param price
	 *            Must be positive
	 * @throws AssertionError
	 *             if the size does not increase by one
	 */
	public void addBaseProduct(int id, String name, float price);

	/**
	 * @param id
	 *            Must be positive and must not repeat itself
	 * @param name
	 *            Must be set
	 * @param itemsId
	 *            Must have at least 2 valid elements elements, none of the elements
	 *            can be id and must contain at most 8 Products
	 * @throws AssertionError
	 *             if the size does not increase by one
	 */
	public void addCompositeProduct(int id, String name, int[] itemsId);

	/**
	 * @param id
	 *            Must be a valid id
	 * @throws AssertionError
	 *             if the size does not decrease by one
	 */
	public void deleteMenuItem(int id);

	/**
	 * @param id
	 *            Must be a valid id
	 * @param name
	 *            Must have a name
	 * @param price
	 *            Must be positive
	 * @throws AssertionError
	 *             if the new Name or the new Price are not set
	 */
	public void editBaseProduct(int id, String name, float price);

	/**
	 * @param id
	 *            Must be a valid id
	 * @param name
	 *            Must have a name
	 * @param itemsId
	 *            Must be at least 2 valid elements, none of the elements can be id
	 *            and must contain at most 8 Products
	 * @throws AssertionError
	 *             if the new Name or the items are not set
	 */
	public void editCompositeProduct(int id, String name, int[] itemsId);

	/**
	 * @param orderId
	 *            Must be positive
	 * @param table
	 *            Must be an integer between 1 and 20 and must not be taken
	 * @param itemsId
	 *            Must be at least 1 valid element and must contain at most 8
	 *            Products
	 * @throws AssertionError
	 *             if the new size of the orders does not increase by one;
	 */
	public void createNewOrder(int orderId, int table, int[] itemsId);

	/**
	 * @param orderId
	 *            Must be a valid id
	 * @throws AssertionError
	 *             if the order does not have a list of Products
	 */
	public void generateBill(int orderId);

	/**
	 * @return The menu as an ArrayList
	 */
	public ArrayList<Object> getMenu();

	/**
	 * @return The menu as a TreeSet
	 */
	public TreeSet<MenuItem> getMenu2();

	/**
	 * @return The orders as an ArrayList
	 */
	public ArrayList<Object> getOrders();

	/**
	 * @param o
	 *            Must be a valid order
	 * @return The list of MenuItems of that order
	 * @throws AssertionError
	 *             if the order does not have a list of Products
	 */
	public List<MenuItem> getOrderItems(Order o);

	/**
	 * @param id
	 *            Must be a valid id
	 * @return The price of the order with id = id
	 */
	public float computeTotalPrice(int id);

	/**
	 * @param id
	 * @return order with id = id or null if the id was not found
	 */
	public Order selectOrder(int id);

	/**
	 * @param id
	 * @return MenuItem with id = id or null if the id was not found
	 */
	public MenuItem selectMenuItem(int id);
}
