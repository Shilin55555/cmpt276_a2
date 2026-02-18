package com.assignment2.assignment;

import com.assignment2.assignment.model.RoleType;
import com.assignment2.assignment.model.StaffRating;
import com.assignment2.assignment.repository.StaffRatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StaffRatingRepositoryTests {

    @Autowired
    private StaffRatingRepository repo;

    @Test
    void saveAndFindShouldWork() {
        StaffRating r = new StaffRating();
        r.setName("Persist User");
        r.setEmail("persist@test.com");
        r.setRoleType(RoleType.TA);
        r.setClarity(5);
        r.setNiceness(5);
        r.setKnowledgeableScore(5);

        StaffRating saved = repo.save(r);

        StaffRating found = repo.findById(saved.getId()).orElseThrow();
        assertEquals("Persist User", found.getName());
        assertEquals("persist@test.com", found.getEmail());
    }

    @Test
    void deleteShouldRemoveEntry() {
        StaffRating r = new StaffRating();
        r.setName("Delete User");
        r.setEmail("delete@test.com");
        r.setRoleType(RoleType.TA);
        r.setClarity(5);
        r.setNiceness(5);
        r.setKnowledgeableScore(5);

        StaffRating saved = repo.save(r);
        Long id = saved.getId();

        repo.deleteById(id);

        assertTrue(repo.findById(id).isEmpty());
    }
}
