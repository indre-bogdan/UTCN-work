package bll.validators;

import java.util.regex.Pattern;

import model.Client;

public class PhoneValidator implements Validator<Object> {
	private static final String PHONE_PATTERN = "(07)\\d{8}";

	public void validate(Object t) {

		Pattern pattern = Pattern.compile(PHONE_PATTERN);
		if (!pattern.matcher(((Client) t).getPhone()).matches()) {
			throw new IllegalArgumentException("Phone is not a valid phone!");
		}
	}
}
