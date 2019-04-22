package bll.validators;

import dao.DAO;
import model.Product;
import model.Stock;

public class StockValidator implements Validator<Stock> {
	public void validate(Stock s) {
		Product p = new Product(s.getIdProduct());
		if (DAO.findById(p) == null) {
			throw new IllegalArgumentException("IdProduct is not valid!");
		}
	}

}
