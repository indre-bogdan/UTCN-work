package model;

public class Orders {
	private int idOrder;
	private int idClient;
	private int idProduct;
	private int idOrderType;
	private float price;
	private int quantity;

	public Orders(int idOrder, int idClient, int idProduct, int idOrderType, float price, int quantity) {
		super();
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.idOrderType = idOrderType;
		this.quantity = quantity;
		this.price = price;
	}

	public Orders(int idClient, int idProduct, int idOrderType, float price) {
		super();
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.idOrderType = idOrderType;
		this.price = price;
	}

	public Orders(int idOrder) {
		super();
		this.idOrder = idOrder;
	}

	/**
	 * @return the idOrder
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder
	 *            the idOrder to set
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient
	 *            the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
	 * @return the idOrderType
	 */
	public int getIdOrderType() {
		return idOrderType;
	}

	/**
	 * @param idOrderType
	 *            the idOrderType to set
	 */
	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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
		String[] a = new String[6];
		a[0] = String.valueOf(idOrder);
		a[1] = String.valueOf(idClient);
		a[2] = String.valueOf(idProduct);
		a[3] = String.valueOf(idOrderType);
		a[4] = String.valueOf(price);
		a[5] = String.valueOf(quantity);
		return a;
	}
}
