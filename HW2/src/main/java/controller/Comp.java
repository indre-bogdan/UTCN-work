package controller;

import java.util.Comparator;

import server.Client;

public class Comp implements Comparator<Client> {
	public int compare(Client a, Client b) {
		if (a.getArrivalTime() > b.getArrivalTime())
			return 1;
		else if (a.getArrivalTime() < b.getArrivalTime())
			return -1;
		return 0;
	}
}
