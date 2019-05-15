package businessL;

import java.util.Date;

public class Order {
	private int orderId;
	private int table;
	private Date date;


	public Order(int orderId, int table) {
		super();
		this.orderId = orderId;
		this.table = table;
		this.date = new Date();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	public int hashCode() {
		return date.hashCode() + table + orderId;
	}

	public String[] toStringVector() {
		String[] a = new String[3];
		a[0] = String.valueOf(orderId);
		a[1] = String.valueOf(table);
		a[2] = date.toString();
		return a;
	}
}
