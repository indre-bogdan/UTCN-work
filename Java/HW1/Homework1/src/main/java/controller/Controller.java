package controller;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import polynomial.Polynomial;

/**
 * The Controller is the link between the GUI and back end of the project
 * 
 * @author IndreBogdan
 *
 */
public class Controller {
	private Polynomial[] polynomials = new Polynomial[2];
	private Polynomial[] result = new Polynomial[2];
	private final Pattern pattern = Pattern.compile("([-+])\\d+[x]\\^([-])?\\d+");
	

	public Controller() {
		polynomials[0] = new Polynomial();
		polynomials[1] = new Polynomial();
		result[0] = new Polynomial();
		result[1] = new Polynomial();
	}
	
	/**
	 * Checks if s matches the pattern
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkString(String s) {
		Matcher matcher = pattern.matcher(s);
		boolean matches = matcher.matches();
		return matches;
	}

	/**
	 * Converts String s of the pol (can be 1 or 2) in a Polynomial
	 * 
	 * @param s
	 * @param pol
	 * @return True on success, false otherwise
	 */
	public boolean StringToPoly(String s, int pol) {
		StringTokenizer multiTokenizer = new StringTokenizer(s, " ");
		String a = new String();
		String nr = new String();
		int coeff = 0;
		int power = 0;
		while (multiTokenizer.hasMoreTokens()) {
			a = multiTokenizer.nextToken();
			if (checkString(a)) {
				StringTokenizer Token2 = new StringTokenizer(a, "x^");
				while (Token2.hasMoreTokens()) {
					nr = Token2.nextToken();
					coeff = power;
					power = Integer.parseInt(nr);
				}
				polynomials[pol].addMonomial(coeff, power);
			}
			else {
				polynomials[pol].getPol().clear();
				return false;
			}

		}
		polynomials[pol].collapse();
		return true;

	}

	public Polynomial getPolynomial(int pol) {
		return polynomials[pol];
	}

	public void addition() {
		result[0] = polynomial.Polynomial.addition(polynomials[0], polynomials[1]);
	}

	public void subtraction() {
		result[0] = polynomial.Polynomial.subtraction(polynomials[0], polynomials[1]);
	}

	public void product() {
		result[0] = polynomial.Polynomial.product(polynomials[0], polynomials[1]);
	}

	public void division() {
		if (polynomials[1].getPol().isEmpty())
			throw new IllegalArgumentException("Cannot divide by 0");
		else
			result = polynomial.Polynomial.division(polynomials[0], polynomials[1]);

	}

	public void differentiation() {
		result[0] = polynomial.Polynomial.differentiation(polynomials[0]);
		result[1] = polynomial.Polynomial.differentiation(polynomials[1]);
	}

	public void integration() {
		result[0] = polynomial.Polynomial.integration(polynomials[0]);
		result[1] = polynomial.Polynomial.integration(polynomials[1]);
	}
	public Polynomial[] getResult() {
		return result;
	}

}
