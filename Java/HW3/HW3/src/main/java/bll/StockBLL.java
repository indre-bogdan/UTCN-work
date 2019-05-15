package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.StockValidator;
import bll.validators.Validator;
import dao.DAO;
import model.Stock;

/**
 * Business Logic Layer for the Stock
 * 
 * @author IndreBogdan
 *
 */
public class StockBLL {
	private List<Validator<Stock>> validators;

	public StockBLL() {
		validators = new ArrayList<Validator<Stock>>();
		validators.add(new StockValidator());
	}

	public Stock findStockById(int id) {
		Stock s = new Stock(id);
		Stock st = (Stock) DAO.findById(s);
		if (st == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not found in stock");
		}
		return st;
	}

	public int insertStock(Stock stock) {

		for (Validator<Stock> v : validators) {
			v.validate(stock);
		}

		return DAO.insert(stock);
	}

	public int deleteStock(int id) {
		Stock s = findStockById(id);
		if (s != null) {
			return DAO.delete(s);
		} else {
			return -1;
		}
	}

	public List<Object> displayStocks() {
		Stock s = new Stock(0);
		return DAO.view(s);
	}

	public int updateStock(int id, int quantity) {
		Stock c = new Stock(id, quantity);
		return DAO.update(c);
	}
}
