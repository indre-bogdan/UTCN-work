package strategy;

import java.util.List;

import server.Client;
import server.Server;

/**
 * Strategy design pattern
 * 
 * @author IndreBogdan
 *
 */
public interface Strategy {
	public Server addClient(List<Server> servers, Client c);
}
