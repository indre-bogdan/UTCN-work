package model;

/**
 * Represents the product table form the database
 * 
 * @author IndreBogdan
 *
 */
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

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getNominalPrice() {
		return nominalPrice;
	}

	public void setNominalPrice(float nominalPrice) {
		this.nominalPrice = nominalPrice;
	}

	/**
	 * @return A vector of strings from the fields of the table
	 */
	public String[] toStringVector() {
		String[] a = new String[3];
		a[0] = String.valueOf(idProduct);
		a[1] = this.name;
		a[2] = String.valueOf(nominalPrice);
		return a;
	}

}
