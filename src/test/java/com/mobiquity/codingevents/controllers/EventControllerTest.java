package com.mobiquity.codingevents.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.codingevents.data.*;
import com.mobiquity.codingevents.models.*;

@ExtendWith(MockitoExtension.class)

class EventControllerTest {

    EventDetails eventDetails;
    @Mock
    EventCategory eventCategory;
    @Mock
    EventRepository eventRepository;
    @Mock
    EventCategoryRepository eventCategoryRepository;
    @Mock
    TagRepository tagRepository;
    @InjectMocks
    EventController controller;
    Set<Event> events;
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        eventCategory = new EventCategory("MeetUp");
        eventDetails = new EventDetails("Java Web Development  ", "mo@mo.com");
        events = new HashSet<>();
        events.add(Event.builder().name("Java").eventCategory(eventCategory).eventDetails(eventDetails).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void displayEvents() throws Exception {
        when(eventRepository.findAll()).thenReturn(events);
        mockMvc.perform(get("/events"))
                .andExpect(view().name("events/index"))
                .andExpect(model().attribute("events", hasSize(1)))
                .andExpect(status().isOk());

    }

    @Test
    void displayCreateEventForm() throws Exception {
        mockMvc.perform(get("/events/create"))
                .andExpect(view().name("events/create"))
                .andExpect(status().isOk());
    }

    @Test
    void processCreateEventForm() throws Exception {
        when(eventRepository.findAll()).thenReturn(events);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/events/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("event", "events"))
                .andExpect(status().isOk());
    }

    @Test
    void displayDeleteEventForm() throws Exception {
        when(eventRepository.findAll()).thenReturn(events);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/events/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("events/delete"));

    }

    @Test
    void processDeleteEventsForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/events/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .param("eventIds", "1")).andExpect(status().is3xxRedirection());

    }

    @Test
    void displayEventDetails() throws Exception {
        mockMvc.perform(get("/events/detail", eventRepository.findById(1)))
                .andExpect(status().isOk())
                .andExpect(content().string(stringContainsInOrder(eventDetails.getDescription())));
    }

    @Test
    void displayAddTagForm() {
    }

    @Test
    void processAddTagForm() {
    }

}