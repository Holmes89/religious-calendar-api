package com.ferrumlabs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ferrumlabs.ReligiousCalendarApiApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReligiousCalendarApiApplication.class)
@WebAppConfiguration
public class ReligiousCalendarApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
