package bll.validators;

import dao.DAO;

/**
 * Checks for duplicate id
 * 
 * @author IndreBogdan
 *
 */
public class IdValidator implements Validator<Object> {

	public void validate(Object t) {
		if (DAO.findById(t) != null)
			throw new IllegalArgumentException("Id is a duplicate!");

	}

}
