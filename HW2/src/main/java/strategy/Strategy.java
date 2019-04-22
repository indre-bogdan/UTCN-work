package strategy;

import java.util.List;

import server.Client;
import server.Server;

public interface Strategy {
	public Server addClient(List<Server> servers, Client c);
}
