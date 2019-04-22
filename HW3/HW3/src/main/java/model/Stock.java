package model;

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
	/**
	 * @return the idProduct
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct
	 *            the idProduct to set
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String[] toStringVector() {
		String[] a = new String[2];
		a[0] = String.valueOf(idProduct);
		a[1] = String.valueOf(quantity);
		return a;
	}

}
