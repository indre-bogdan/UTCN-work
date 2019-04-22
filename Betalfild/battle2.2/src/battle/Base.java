package battle;

import exceptions.InvalidDataException;
import exceptions.InvalidNameException;

/**
 * A base represents a player
 * 
 */
public class Base {

	public int STARTING_MONEY = 1000;//Each player has 1000 gold as STARTING_MONEY
	private int currentMoney = STARTING_MONEY;
	public String Name;//also a name
	private Division[] divisions = new Division[5];// The 4 divisions of units used in the game
	private int[] UNIT_PRICE = { 0, 10, 7, 15, 25 };//The price for each unit

	public Base(String Name) {

		this.Name = Name;
		divisions[1] = new Division(1, 0);
		divisions[2] = new Division(2, 0);
		divisions[3] = new Division(3, 0);
		divisions[4] = new Division(4, 0);
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) throws InvalidNameException {
		if (name.length() > 0)
			this.Name = name;
		else
			throw new InvalidNameException("You must have a name!");
	}

	public Division getDivisions(int type) {
		return this.divisions[type];
	}

	/**
	 * buySoldier used to buy 1 soldier
	 */
	public void buySoldier(int type) {
		if (currentMoney > UNIT_PRICE[type])
			if (divisions[type].addSoldier())
				currentMoney -= UNIT_PRICE[type];

	}

	/**
	 * sellSoldier used to sell 1 soldier
	 */
	public void sellSoldier(int type) {
		if (currentMoney < STARTING_MONEY)
			if (divisions[type].killSoldier())
				currentMoney += UNIT_PRICE[type];
	}

	/**
	 * checkSoldiers used to check the number of soldiers at the beginning of the
	 * game so that each player doesn`t have 0 soldiers
	 */
	public void checkSoldiers() throws InvalidDataException {
		if (divisions[1].isDead() && divisions[2].isDead() && divisions[3].isDead() && divisions[4].isDead())
			throw new InvalidDataException("You must have soldiers to play!");
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append("\nBase details: ").append("name = ").append(Name).append(", current money = ")
				.append(currentMoney).append(divisions[1].toString()).append(divisions[2].toString())
				.append(divisions[3].toString()).append(divisions[4].toString()).append("\n").toString();
	}


}