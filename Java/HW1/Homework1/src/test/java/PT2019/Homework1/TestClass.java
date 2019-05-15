package PT2019.Homework1;

import org.junit.Assert;
import org.junit.Test;

import polynomial.Polynomial;

public class TestClass {
	Polynomial a = new Polynomial();
	Polynomial b = new Polynomial();

	@Test
	public void addition() {
		initialize();
		Assert.assertTrue(polynomial.Polynomial.addition(a, b).toString().equals("-1.0 +3.0x^1 -4.0x^2 +x^3 \n"));
	}

	@Test
	public void subtraction() {
		initialize();
		Assert.assertTrue(polynomial.Polynomial.subtraction(a, b).toString().equals("-5.0 +x^1 -4.0x^2 +x^3 \n"));

	}

	@Test
	public void product() {
		initialize();
		Assert.assertTrue(polynomial.Polynomial.product(a, b).toString()
				.equals("-6.0 +x^1 -6.0x^2 -2.0x^3 +x^4 \n"));
	}

	@Test
	public void division() {
		initialize();
		Polynomial[] result = new Polynomial[2];
		result[0] = new Polynomial();
		result[1] = new Polynomial();
		result = polynomial.Polynomial.division(a, b);
		Assert.assertTrue(result[0].toString().equals("+x^2 -6.0x^1 +14.0 \n"));
		Assert.assertTrue(result[1].toString().equals("-31.0 \n"));
	}

	@Test
	public void differentiation() {
		initialize();
		Assert.assertTrue(polynomial.Polynomial.differentiation(a).toString().equals("+2.0 -8.0x^1 +3.0x^2 \n"));
		Assert.assertTrue(polynomial.Polynomial.differentiation(b).toString().equals("+1.0 \n"));

	}

	@Test
	public void intergration() {
		initialize();
		Assert.assertTrue(
				polynomial.Polynomial.integration(a).toString().equals("-3.0x^1 +x^2 -1.3333334x^3 +0.25x^4 \n"));
		Assert.assertTrue(polynomial.Polynomial.integration(b).toString().equals("+2.0x^1 +0.5x^2 \n"));
	}

	public void initialize() {
		a.addMonomial(1, 3);
		a.addMonomial(-4, 2);
		a.addMonomial(2, 1);
		a.addMonomial(-3, 0);
		a.collapse();
		// x^3 -4x^2 +2x - 3

		b.addMonomial(1, 1);
		b.addMonomial(2, 0);
		b.collapse();
		// x + 2

	}

}