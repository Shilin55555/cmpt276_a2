package com.assignment2.assignment.service;

import com.assignment2.assignment.model.StaffRating;
import com.assignment2.assignment.repository.StaffRatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffRatingService {

	private final StaffRatingRepository repository;

	public StaffRatingService(StaffRatingRepository repository) {
		this.repository = repository;
	}

	public List<StaffRating> listAll() {
		return repository.findAll();
	}

	public StaffRating getOrThrow(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("StaffRating not found: " + id));
	}

	public StaffRating save(StaffRating rating) {
		return repository.save(rating);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean emailExists(String email) {
		return repository.findByEmail(email).isPresent();
	}
}
