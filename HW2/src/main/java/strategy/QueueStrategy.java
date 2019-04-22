package strategy;

import java.util.List;

import gui.GUI;
import server.Client;
import server.Server;

public class QueueStrategy implements Strategy {
	public Server addClient(List<Server> servers, Client c) {
		Server bestServer = servers.get(0);
		int smallestQueue = 1000;
		for (Server s : servers) {
			if (s.gerNrOfClients() < smallestQueue) {
				smallestQueue = s.gerNrOfClients();
				bestServer = s;
			}

		}
		bestServer.addClient(c);
		GUI.modelL.addElement("Added " + c.toString() + " to server " + bestServer.getName());
		return bestServer;
	}
}
