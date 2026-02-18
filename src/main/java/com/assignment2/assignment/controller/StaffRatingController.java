package com.assignment2.assignment.controller;

import com.assignment2.assignment.model.StaffRating;
import com.assignment2.assignment.service.StaffRatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.assignment2.assignment.repository.StaffRatingRepository;


@Controller
@RequestMapping("/ratings")
public class StaffRatingController {

	private final StaffRatingService service;

    private final StaffRatingRepository repository;

    public StaffRatingController(StaffRatingService service, StaffRatingRepository repository) {
        this.service = service;
        this.repository = repository;
    }    

	@GetMapping
	public String index(Model model) {
		model.addAttribute("ratings", service.listAll());
		return "ratings/index";
	}

	@GetMapping("/{id}")
public String detail(@PathVariable Long id, Model model) {
	StaffRating rating = service.getOrThrow(id);
	model.addAttribute("rating", rating);

	String displayTitle = com.assignment2.assignment.design.ProfileFactory
			.fromRole(rating.getRoleType())
			.displayTitle();
	model.addAttribute("displayTitle", displayTitle);

	return "ratings/detail";
}

    @GetMapping("/new")
public String newForm(Model model) {
	StaffRating rating = new StaffRating();
	rating.setRoleType(com.assignment2.assignment.model.RoleType.TA);
	rating.setClarity(5);
	rating.setNiceness(5);
	rating.setKnowledgeableScore(5);

	model.addAttribute("rating", rating);
	model.addAttribute("roleTypes", com.assignment2.assignment.model.RoleType.values());
	return "ratings/new";
}

@PostMapping
public String create(@Valid @ModelAttribute("rating") StaffRating rating,
					 BindingResult bindingResult,
					 Model model) {

	if (rating.getEmail() != null && service.emailExists(rating.getEmail())) {
		bindingResult.rejectValue("email", "duplicate", "Email already exists");
	}

	if (bindingResult.hasErrors()) {
		model.addAttribute("roleTypes", com.assignment2.assignment.model.RoleType.values());
		return "ratings/new";
	}

	service.save(rating);
	return "redirect:/ratings";
}

@GetMapping("/{id}/edit")
public String editForm(@PathVariable Long id, Model model) {
	StaffRating rating = service.getOrThrow(id);
	model.addAttribute("rating", rating);
	model.addAttribute("roleTypes", com.assignment2.assignment.model.RoleType.values());
	return "ratings/edit";
}

@PostMapping("/{id}/edit")
public String update(@PathVariable Long id,
					 @Valid @ModelAttribute("rating") StaffRating rating,
					 BindingResult bindingResult,
					 Model model) {

	// 保证更新的是同一个 id
	rating.setId(id);

	// email 唯一：允许自己用自己原来的 email
	if (rating.getEmail() != null) {
		repository.findByEmail(rating.getEmail()).ifPresent(existing -> {
			if (!existing.getId().equals(id)) {
				bindingResult.rejectValue("email", "duplicate", "Email already exists");
			}
		});
	}

	if (bindingResult.hasErrors()) {
		model.addAttribute("roleTypes", com.assignment2.assignment.model.RoleType.values());
		return "ratings/edit";
	}

	service.save(rating);
	return "redirect:/ratings";
}

@PostMapping("/{id}/delete")
public String delete(@PathVariable Long id) {
	service.deleteById(id);
	return "redirect:/ratings";
}

}


