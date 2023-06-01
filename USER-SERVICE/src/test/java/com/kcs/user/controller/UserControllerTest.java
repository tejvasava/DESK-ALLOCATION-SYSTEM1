package com.kcs.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.kcs.user.service.UserService;

/*
 * This will be integration test so it will create the entire context
 * so, this context should not be dependent on any port [server.port=0] which mean it will run on any port.
 */

@SpringBootTest({"server.port=0"})
@EnableConfigurationProperties
@AutoConfigureMockMvc
public class UserControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserController userController;
	
	@BeforeEach
	void setup()
	{
		MockitoAnnotations.initMocks(this.userController);
		this.mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
		
	}
	
	@Test
	public void  test_add_user() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders 
				.get("/user/getUser")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
