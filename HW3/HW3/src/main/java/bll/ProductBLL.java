package bll;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.IdValidator;
import bll.validators.Validator;
import dao.DAO;
import model.Product;
import model.Stock;


/**
 * Business Logic Layer for the Product
 * 
 * @author IndreBogdan
 *
 */
public class ProductBLL {
	private List<Validator<Object>> validators;
	public ProductBLL() {
		validators = new ArrayList<Validator<Object>>();
		validators.add(new IdValidator());
	}

	public Product findProductById(int id) {
		Product p = new Product(id);
		Product st = (Product) DAO.findById(p);
		if (st == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return st;
	}

	public int insertProduct(Product product) {
		for (Validator<Object> v : validators) {
			v.validate(product);
		}
		int id = DAO.insert(product);
		if (id != -1) {
			DAO.insert(new Stock(product.getIdProduct(), 0));
			return 1;
		}
		return -1;
	}

	public int deleteProduct(int id) {
		Product s = findProductById(id);
		if (s != null) {
			int id2 = DAO.delete(s);
			if (id2 != -1) {
				DAO.delete(new Stock(s.getIdProduct()));
				return 1;
			}
		}
		return -1;
	}

	public List<Object> displayProducts() {
		Product p = new Product(0);
		return DAO.view(p);
	}

	public int updateProduct(int id, String name, float nominalPrice) {
		Product c = new Product(id, name, nominalPrice);
		return DAO.update(c);
	}
}
