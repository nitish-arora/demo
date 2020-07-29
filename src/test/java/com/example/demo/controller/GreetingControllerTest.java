package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Should return Greetings with Name")
	public void shouldReturnGreetingWithName() throws Exception {
		mockMvc.perform(get("/{user}", "Nitish")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello Nitish")));
	}
}
