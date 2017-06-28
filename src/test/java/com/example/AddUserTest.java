package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.web.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MockServletContext.class)
public class AddUserTest {

	private MockMvc mvc;
	
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
		mvc=MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
	@Test
	public void test() throws Exception{
		RequestBuilder request=null;
        request=post("/users/a")
        		.param("int", "1")
        		.param("name", "abc")
        		.param("age", "20");
        mvc.perform(request).andExpect(content().string(equalTo("success")));		
        
	}
}
