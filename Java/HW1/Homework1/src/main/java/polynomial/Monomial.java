package polynomial;

/**
 * Structure used to hold one monomial
 * 
 * @author IndreBogdan
 *
 */
public class Monomial {
	private float coeff;
	private int power;

	public Monomial(float coeff, int power) {
		this.coeff = coeff;
		this.power = power;
	}

	public float getCoeff() {
		return coeff;
	}

	public void setCoeff(float f) {
		this.coeff = f;
	}

	public int getPower() {
		return power;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		if (coeff > 0)
			stringBuilder.append("+");
		if (coeff != 1 && power != 0)
			stringBuilder.append(coeff).append("x^").append(power);
		else
		if (power != 0)
			stringBuilder.append("x^").append(power);
		else
			stringBuilder.append(coeff);
		return stringBuilder.toString();
	}
	
}
