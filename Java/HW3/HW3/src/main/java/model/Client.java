package model;

/**
 * Represents the client table form the database
 * 
 * @author IndreBogdan
 *
 */
public class Client {
	private int idClient;
	private String name;
	private String address;
	private String phone;

	public Client(int idClient, String name, String address, String phone) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Client(int idClient) {
		super();
		this.idClient = idClient;
	}

	public Client(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return A vector of strings from the fields of the table
	 */
	public String[] toStringVector() {
		String[] a = new String[4];
		a[0] = String.valueOf(idClient);
		a[1] = this.name;
		a[2] = this.address;
		a[3] = this.phone;
		return a;
	}
}
