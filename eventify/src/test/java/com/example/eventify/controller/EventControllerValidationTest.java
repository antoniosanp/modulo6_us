package com.example.eventify.controller;

import com.example.eventify.exception.GlobalExceptionHandler;
import com.example.eventify.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EventControllerValidationTest {

    private MockMvc mockMvc;

    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventService = mock(EventService.class);

        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        EventController controller = new EventController(eventService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(validator)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    void shouldReturnDtoValidationMessagesWhenPostBodyIsMissingRequiredFields() throws Exception {
        mockMvc.perform(post("/api/events")
                        .contentType(APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Solicitud invalida"))
                .andExpect(jsonPath("$.errors.name").value("El nombre del evento es obligatorio"))
                .andExpect(jsonPath("$.errors.eventDate").value("La fecha del evento es obligatoria"))
                .andExpect(jsonPath("$.errors.description").value("La descripción es obligatoria"));

        verifyNoInteractions(eventService);
    }
}
