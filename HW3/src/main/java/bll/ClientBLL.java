package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.IdValidator;
import bll.validators.PhoneValidator;
import bll.validators.Validator;
import dao.DAO;
import model.Client;

public class ClientBLL {
	private List<Validator<Object>> validators;

	public ClientBLL() {
		validators = new ArrayList<Validator<Object>>();
		validators.add(new IdValidator());
		validators.add(new PhoneValidator());

	}

	public Client findClientById(int id) {
		Client find = new Client(id);
		Client st = (Client) DAO.findById(find);
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}

	public int insertClient(Client client) {

		for (Validator<Object> v : validators) {
			v.validate(client);
		}

		return DAO.insert(client);
	}

	public int deleteClient(int id) {
		Client s = findClientById(id);
		if (s != null) {
			return DAO.delete(new Client(id));
		} else {
			return -1;
		}
	}

	public List<Object> displayClients() {
		Client c = new Client(0);
		return DAO.view(c);
	}

	public int updateClient(int id, String name, String address, String phone) {
		Client c = new Client(id, name, address, phone);
		Validator<Object> v2 = new PhoneValidator();
		v2.validate(c);
		return DAO.update(c);
	}
}
