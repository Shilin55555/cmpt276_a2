package com.assignment2.assignment;

import com.assignment2.assignment.model.RoleType;
import com.assignment2.assignment.model.StaffRating;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaffRatingValidationTests {

	@Test
	void invalidEmailShouldFailValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		StaffRating rating = new StaffRating();
		rating.setName("Test User");
		rating.setEmail("not-an-email");
		rating.setRoleType(RoleType.TA);
		rating.setClarity(5);
		rating.setNiceness(5);
		rating.setKnowledgeableScore(5);

		Set<ConstraintViolation<StaffRating>> violations = validator.validate(rating);

		boolean hasEmailError = violations.stream()
				.anyMatch(v -> v.getPropertyPath().toString().equals("email"));

		assertTrue(hasEmailError);
	}
}
