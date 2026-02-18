package com.assignment2.assignment.repository;

import com.assignment2.assignment.model.StaffRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRatingRepository extends JpaRepository<StaffRating, Long> {
	Optional<StaffRating> findByEmail(String email);
}
