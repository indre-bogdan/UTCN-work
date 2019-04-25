package strategy;

import java.util.List;

import gui.GUI;
import server.Client;
import server.Server;

/**
 * Clients will find the server with the least waitingTime
 * 
 * @author IndreBogdan
 *
 */
public class TimeStrategy implements Strategy {

	public Server addClient(List<Server> servers, Client c) {
		Server bestServer = servers.get(0);
		int smallestWaitingTime = 1000;
		for (Server s : servers) {
			if (s.getWaitingTime() < smallestWaitingTime) {
				smallestWaitingTime = s.getWaitingTime();
				bestServer = s;
			}

		}
		bestServer.addClient(c);
		GUI.modelL.addElement("Added " + c.toString() + " to server " + bestServer.getName());
		return bestServer;
	}
}
