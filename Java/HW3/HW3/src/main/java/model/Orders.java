package model;

/**
 * Represents the orders table form the database
 * 
 * @author IndreBogdan
 *
 */
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

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdOrderType() {
		return idOrderType;
	}

	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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
