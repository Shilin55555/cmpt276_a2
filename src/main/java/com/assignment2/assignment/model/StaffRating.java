package com.assignment2.assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

@Entity
public class StaffRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be 2-100 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	@Column(unique = true)
	private String email;

	@NotNull(message = "Role is required")
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	@NotNull(message = "Clarity is required")
	@Min(value = 1, message = "Clarity must be between 1 and 10")
	@Max(value = 10, message = "Clarity must be between 1 and 10")
	private Integer clarity;
	
	@NotNull(message = "Niceness is required")
	@Min(value = 1, message = "Niceness must be between 1 and 10")
	@Max(value = 10, message = "Niceness must be between 1 and 10")
	private Integer niceness;
	
	@NotNull(message = "Knowledgeable score is required")
	@Min(value = 1, message = "Knowledgeable score must be between 1 and 10")
	@Max(value = 10, message = "Knowledgeable score must be between 1 and 10")
	private Integer knowledgeableScore;
	

	@Size(max = 300, message = "Comment must be at most 300 characters")
	private String comment;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}
	

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public double getOverallScore() {
		if (clarity == null || niceness == null || knowledgeableScore == null) {
			return 0.0;
		}
		return (clarity + niceness + knowledgeableScore) / 3.0;
	}	
	

	// ----- getters and setters -----

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Integer getClarity() {
		return clarity;
	}

	public void setClarity(Integer clarity) {
		this.clarity = clarity;
	}

	public Integer getNiceness() {
		return niceness;
	}

	public void setNiceness(Integer niceness) {
		this.niceness = niceness;
	}

	public Integer getKnowledgeableScore() {
		return knowledgeableScore;
	}

	public void setKnowledgeableScore(Integer knowledgeableScore) {
		this.knowledgeableScore = knowledgeableScore;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
