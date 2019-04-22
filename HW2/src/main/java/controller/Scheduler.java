package controller;

import java.util.ArrayList;
import java.util.List;

import server.Client;
import server.Server;
import strategy.QueueStrategy;
import strategy.SelectionPolicy;
import strategy.Strategy;
import strategy.TimeStrategy;

public class Scheduler {
	private List<Server> servers;
	private List<Thread> threads;
	private Strategy strategy;

	public Scheduler(int NrOfServers) {
		this.threads = new ArrayList<Thread>();
		servers = new ArrayList<Server>();
		for (int i = 0; i < NrOfServers; i++) {
			Server s = new Server(i);
			servers.add(s);
			threads.add(new Thread(s));
			threads.get(i).setDaemon(true);
		}
		// default strategy
		strategy = new TimeStrategy();

	}

	public void changeStrategy(SelectionPolicy policy) {
		if (policy == SelectionPolicy.QUEUE)
			strategy = new QueueStrategy();
		if (policy == SelectionPolicy.TIME)
			strategy = new TimeStrategy();
	}

	public Server queueClient(Client c) {
		return strategy.addClient(servers, c);
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public void computeEmptyQueueTime() {

	}

}
