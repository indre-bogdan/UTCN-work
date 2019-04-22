package bll.validators;

import dao.DAO;
import model.Client;
import model.OrderType;
import model.Orders;
import model.Product;
import model.Stock;

public class OrderValidator implements Validator<Object> {

	private boolean checkUnderStock(Object o) {
		OrderType a = new OrderType(((Orders) o).getIdOrderType());
		OrderType b = (OrderType) DAO.findById(a);
		if (b.getName().contains("sell")) {
			Stock c = new Stock(((Orders) o).getIdProduct());
			Stock s = (Stock) DAO.findById(c);
			if (s.getQuantity() < ((Orders) o).getQuantity()) {
				return true;
			}

		}
		return false;
	}

	public void validate(Object o) {
		Client a = new Client(((Orders) o).getIdClient());
		Product b = new Product(((Orders) o).getIdProduct());
		OrderType c = new OrderType(((Orders) o).getIdOrderType());
		if (DAO.findById(a) == null)
			throw new IllegalArgumentException("IdClient is not valid!");
		if (DAO.findById(b) == null)
			throw new IllegalArgumentException("IdProduct is not valid!");
		if (DAO.findById(c) == null)
			throw new IllegalArgumentException("IdOrderType is not valid!");
		if (checkUnderStock(o))
			throw new IllegalArgumentException("UnderStocked");
	}


}
