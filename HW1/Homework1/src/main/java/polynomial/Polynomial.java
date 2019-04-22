package polynomial;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
	private ArrayList<Monomial> pol;

	public Polynomial() {
		pol = new ArrayList<Monomial>();
	}

	public ArrayList<Monomial> getPol() {
		return pol;
	}

	public void addMonomial(float coeff, int power) {
		Monomial a = new Monomial(coeff, power);
		pol.add(a);
	}

	public void addMonomial(Monomial a) {
		pol.add(a);
	}

	public void collapse() {
		for (int i = 0; i < this.pol.size() - 1; i++) {
			for (int j = i + 1; j < this.pol.size(); j++) {
				if (this.pol.get(i).getPower() == this.pol.get(j).getPower()) {
					this.pol.get(i).setCoeff(this.pol.get(i).getCoeff() + this.pol.get(j).getCoeff());
					this.pol.remove(j);
				}
			}
		}
		for (int i = 0; i < this.pol.size(); i++) {
			if (this.pol.get(i).getCoeff() == 0)
				this.pol.remove(i);
		}
		Collections.sort(pol, new Comp());
	}

	public static Polynomial addition(Polynomial a, Polynomial b) {
		Polynomial c = new Polynomial();
		for (Monomial Ita : a.pol)
			c.addMonomial(Ita.getCoeff(), Ita.getPower());
		for (Monomial Itb : b.pol)
			c.addMonomial(Itb.getCoeff(), Itb.getPower());
		c.collapse();
		return c;
	}

	public static Polynomial subtraction(Polynomial a, Polynomial b) {
		Polynomial c = new Polynomial();
		for (Monomial Ita : a.pol)
			c.addMonomial(Ita.getCoeff(), Ita.getPower());
		for (Monomial Itb : b.pol)
			c.addMonomial((-1) * Itb.getCoeff(), Itb.getPower());
		c.collapse();
		return c;
	}

	public static Polynomial product(Polynomial a, Polynomial b) {
		Polynomial c = new Polynomial();
		for (Monomial Ita : a.pol) {
			for (Monomial Itb : b.pol) {
				c.addMonomial(Ita.getCoeff() * Itb.getCoeff(), Ita.getPower() + Itb.getPower());
			}
		}
		c.collapse();
		return c;
	}

	public static Polynomial differentiation(Polynomial a) {
		Polynomial c = new Polynomial();
		for (Monomial Ita : a.pol) {
			if (Ita.getPower() != 0)
				c.addMonomial(Ita.getCoeff() * Ita.getPower(), Ita.getPower() - 1);
		}
		return c;
	}

	public static Polynomial integration(Polynomial a) {
		Polynomial c = new Polynomial();
		for (Monomial Ita : a.pol) {
			if (Ita.getPower() != -1)
				c.addMonomial(Ita.getCoeff() / (Ita.getPower() + 1), Ita.getPower() + 1);
			else
			{
				throw new IllegalArgumentException("Monomials with the power = -1 will output ln");

			}
		}
		return c;
	}

	private static Polynomial MonomialPolynomialProduct(Monomial a, Polynomial b) {
		Polynomial c = new Polynomial();
		for (Monomial Itb : b.pol) {
			c.addMonomial(a.getCoeff() * Itb.getCoeff(), a.getPower() + Itb.getPower());
		}
		return c;
	}

	private static int degree(Polynomial a) {
		return a.pol.get(a.pol.size() - 1).getPower();
	}

	private static float coeff(Polynomial a) {
		return a.pol.get(a.pol.size() - 1).getCoeff();
	}

	public static Polynomial[] division(Polynomial a, Polynomial b) {
		Polynomial[] c = new Polynomial[2];// 1st quotient 2nd the remainder
		c[0] = new Polynomial();
		c[1] = new Polynomial();
		Polynomial d = new Polynomial();
		for(Monomial Ita : a.pol)
			c[1].addMonomial(Ita.getCoeff(), Ita.getPower());
		while (!c[1].pol.isEmpty() && degree(c[1]) >= degree(b)) {
			Monomial m = new Monomial(coeff(c[1]) / coeff(b), degree(c[1]) - degree(b));
			d.addMonomial(m);
			c[0] = MonomialPolynomialProduct(m, b);
			c[1] = subtraction(c[1], c[0]);
		}
		c[0] = d;
		return c;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Monomial a : pol)
			stringBuilder.append(a.toString()).append(" ");
		return stringBuilder.append("\n").toString();
	}

}
