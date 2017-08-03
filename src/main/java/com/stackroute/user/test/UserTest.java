package com.stackroute.user.test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.stackroute.user.main.ActivityStreamUserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ActivityStreamUserService.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=ActivityStreamUserService.class)
public class UserTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void allUserTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/activityStream/api/user")).andExpect(status().isOk());
	}
	
	public void validateUser()throws Exception
	{

			MvcResult mvcResult =(MvcResult) this.mockMvc.perform(post("http://localhost:9012/api/user/authenticate").param("emailId", "sweta@gmail.com")
            .param("password", "password")).andDo(print()).andExpect(status().isOk())        
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.message").value("Hello Sweta!!!"))
            .andExpect(jsonPath("$.emailId").value("sweta@gmail.com"));
	}
}
