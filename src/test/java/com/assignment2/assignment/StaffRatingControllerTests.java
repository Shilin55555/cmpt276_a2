package com.assignment2.assignment;

import com.assignment2.assignment.model.RoleType;
import com.assignment2.assignment.model.StaffRating;
import com.assignment2.assignment.service.StaffRatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StaffRatingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffRatingService staffRatingService;

    // 1) GET index returns 200 and includes expected model attributes
    @Test
    void getIndexShouldReturn200AndModelHasRatings() throws Exception {
        StaffRating r = new StaffRating();
        r.setId(1L);
        r.setName("Alice");
        r.setEmail("alice@test.com");
        r.setRoleType(RoleType.TA);
        r.setClarity(5);
        r.setNiceness(5);
        r.setKnowledgeableScore(5);

        when(staffRatingService.listAll()).thenReturn(List.of(r));

        mockMvc.perform(get("/ratings"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"))
                .andExpect(view().name("ratings/index"));
    }

    // 2) POST create success path redirects appropriately
    @Test
    void postCreateValidShouldRedirect() throws Exception {
        when(staffRatingService.emailExists("ok@test.com")).thenReturn(false);

        mockMvc.perform(post("/ratings")
                        .param("name", "Bob")
                        .param("email", "ok@test.com")
                        .param("roleType", "TA")
                        .param("clarity", "5")
                        .param("niceness", "5")
                        .param("knowledgeableScore", "5")
                        .param("comment", "hello"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ratings"));

        verify(staffRatingService, times(1)).save(any());
    }

    // 3) POST create failure path returns form with errors
    @Test
    void postCreateInvalidShouldReturnFormWithErrors() throws Exception {
        mockMvc.perform(post("/ratings")
                        .param("name", "") // invalid
                        .param("email", "not-an-email") // invalid
                        .param("roleType", "TA")
                        .param("clarity", "20") // invalid
                        .param("niceness", "5")
                        .param("knowledgeableScore", "5"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(view().name("ratings/new"));

        verify(staffRatingService, never()).save(any());
    }
    @MockBean
    private com.assignment2.assignment.repository.StaffRatingRepository staffRatingRepository;

}
