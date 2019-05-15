package bll;


import java.util.List;
import java.util.NoSuchElementException;

import dao.DAO;
import model.OrderType;

/**
 * Business Logic Layer for the Order Type
 * 
 * @author IndreBogdan
 *
 */
public class OrderTypeBLL {
	public OrderTypeBLL() {
		
	}

	public OrderType findOrderTypeById(int id) {
		OrderType a = new OrderType(id);
		OrderType st = (OrderType) DAO.findById(a);
		if (st == null) {
			throw new NoSuchElementException("The orderType with id =" + id + " was not found!");
		}
		return st;
	}

	public int insertOrderType(OrderType orderType) {
		return DAO.insert(orderType);
	}

	public int deleteOrderType(int id) {
		OrderType s = findOrderTypeById(id);
		if (s != null) {
			return DAO.delete(id);
		} else {
			return -1;
		}
	}

	public List<Object> displayOrderTypes() {
		OrderType a = new OrderType(0);
		return DAO.view(a);
	}

	public int updateOrderType(int id, String name) {
		OrderType c = new OrderType(id, name);
		return DAO.update(c);
	}
}
