package com.UH.OtherLevel.infrastructure.adapter.in.web;

import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.CreateVenueRequest;
import com.UH.OtherLevel.infrastructure.config.TestContainersConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainersConfig.class)
class EventControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @WithMockUser
        void shouldCreateAndRetrieveEvent() throws Exception {
                // First create a Venue
                CreateVenueRequest venueRequest = new CreateVenueRequest();
                venueRequest.setName("Test Venue");
                venueRequest.setCapacity(100);
                venueRequest.setAddress("Test Address");

                String venueResponse = mockMvc.perform(post("/venues")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(venueRequest)))
                                .andExpect(status().isCreated())
                                .andReturn().getResponse().getContentAsString();

                // Extract ID (assuming simple JSON structure or using JsonPath)
                // For simplicity, let's just assume ID 1 if it's the first test running against
                // empty DB
                // But better to parse it.
                // Let's just hardcode ID 1 for now as it's a fresh container usually.
                // Actually, let's rely on the fact that we can create an event with a
                // non-existent venue if we mock,
                // but here we are using real DB. So we MUST create a venue first.

                // Let's try to parse the ID from response
                Long venueId = objectMapper.readTree(venueResponse).get("id").asLong();

                CreateEventRequest eventRequest = new CreateEventRequest();
                eventRequest.setName("Integration Test Event");
                eventRequest.setVenueId(venueId);
                eventRequest.setDate(java.time.LocalDateTime.now().plusDays(1));
                eventRequest.setEndDate(java.time.LocalDateTime.now().plusDays(1).plusHours(2));

                mockMvc.perform(post("/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(eventRequest)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.name").value("Integration Test Event"));

                mockMvc.perform(get("/events"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].name").value("Integration Test Event"));
        }
}
