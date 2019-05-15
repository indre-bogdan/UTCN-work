package polynomial;

import java.util.Comparator;

public class Comp implements Comparator<Monomial> {


	public int compare(Monomial a, Monomial b) {
		if (a.getPower() < b.getPower())
			return -1;
		else if (a.getPower() > b.getPower())
			return 1;
		else
			return 0;
	}

}
