package com.xiaoshu.test;

import java.util.Calendar;
import java.util.Date;

import javax.swing.text.AbstractDocument.Content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:dispatcherServlet-servlet.xml" })
public class TestMenuTree {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private MockHttpSession session;

	@Before
	public void setup() {
		// init applicationContext
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.wac).build();
		this.session = new MockHttpSession();
	}

	@Test
	public void test(){
		try {
			MvcResult andReturn = this.mockMvc.perform(MockMvcRequestBuilders.post("login").
					param("parentId", "-1").accept(MediaType.APPLICATION_JSON)).andReturn();
			String string = andReturn.getResponse().getContentAsString();
			System.out.println(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.MONTH,3);
		Date time = ca.getTime();
		System.out.println(time);
	}

}
