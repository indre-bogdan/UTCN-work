package model;

/**
 * Represents the stock table form the database
 * 
 * @author IndreBogdan
 *
 */
public class Stock {
	private int idProduct;
	private int quantity;

	public Stock(int idProduct, int quantity) {
		super();
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public Stock(int idProduct) {
		super();
		this.idProduct = idProduct;
	}
	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return A vector of strings from the fields of the table
	 */
	public String[] toStringVector() {
		String[] a = new String[2];
		a[0] = String.valueOf(idProduct);
		a[1] = String.valueOf(quantity);
		return a;
	}

}
