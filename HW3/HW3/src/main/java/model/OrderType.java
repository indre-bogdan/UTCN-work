package model;

/**
 * Represents the ordertype table form the database
 * 
 * @author IndreBogdan
 *
 */
public class OrderType {
	private int idOrderType;
	private String name;

	public OrderType(int idOrderType, String name) {
		super();
		this.idOrderType = idOrderType;
		this.name = name;
	}

	public OrderType(int idOrderType) {
		super();
		this.idOrderType = idOrderType;
	}

	public int getIdOrderType() {
		return idOrderType;
	}

	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return A vector of strings from the fields of the table
	 */
	public String[] toStringVector() {
		String[] a = new String[2];
		a[0] = String.valueOf(idOrderType);
		a[1] = this.name;
		return a;
	}

}
