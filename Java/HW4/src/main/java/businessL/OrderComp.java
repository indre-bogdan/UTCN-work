package businessL;

import java.util.Comparator;

public class OrderComp implements Comparator<Order> {

	public int compare(Order a, Order b) {
		if (a.getOrderId() < b.getOrderId())
			return -1;
		else if (a.getOrderId() > b.getOrderId())
			return 1;
		else
			return 0;
	}

}