package model;

public class Product {
	private int idProduct;
	private String name;
	private float nominalPrice;

	public Product(int idProduct, String name, float nominalPrice) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.nominalPrice = nominalPrice;
	}

	public Product(String name, float nominalPrice) {
		super();
		this.name = name;
		this.nominalPrice = nominalPrice;
	}

	public Product(int idProduct) {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nominalPrice
	 */
	public float getNominalPrice() {
		return nominalPrice;
	}

	/**
	 * @param nominalPrice
	 *            the nominalPrice to set
	 */
	public void setNominalPrice(float nominalPrice) {
		this.nominalPrice = nominalPrice;
	}

	public String[] toStringVector() {
		String[] a = new String[3];
		a[0] = String.valueOf(idProduct);
		a[1] = this.name;
		a[2] = String.valueOf(nominalPrice);
		return a;
	}

}
