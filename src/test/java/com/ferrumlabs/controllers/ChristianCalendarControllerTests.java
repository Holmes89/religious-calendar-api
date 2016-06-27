package com.ferrumlabs.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Provider;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferrumlabs.ReligiousCalendarApiApplication;
import com.ferrumlabs.commands.GetChristianDatesCommand;
import com.ferrumlabs.commands.GetEasterCommand;
import com.ferrumlabs.commands.GetLectionaryByDateCommand;
import com.ferrumlabs.commands.GetLectionaryCommand;
import com.ferrumlabs.commands.GetNearestHolidayCommand;
import com.ferrumlabs.dto.LectionaryVerseDTO;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReligiousCalendarApiApplication.class)
@WebAppConfiguration
public class ChristianCalendarControllerTests {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	private final String MOCKED_RESPONSE = "blah";
	
	private ObjectMapper mapper = new ObjectMapper();
		
	@Mock
	Provider<GetChristianDatesCommand> getChristianDatesProvider;
	
	@Mock
	Provider<GetEasterCommand> getEasterProvider;
	
	@Mock
	Provider<GetNearestHolidayCommand> getNearestHolidayProvider;
	
	@Mock
	Provider<GetLectionaryByDateCommand> getLectionaryByDateProvider;

	@Mock
	Provider<GetLectionaryCommand> getLectionaryProvider;

	@Mock
	GetChristianDatesCommand getChristianDatesCommand;
	
	@Mock
	GetEasterCommand getEasterCommand;
	
	@Mock
	GetNearestHolidayCommand getNearestHolidayCommand;
	
	@Mock
	GetLectionaryByDateCommand getLectionaryByDateCommand;
	
	@Mock
	GetLectionaryCommand getLectionaryCommand;
	
	@InjectMocks
	@Autowired
	ChristianCalendarController underTest = new ChristianCalendarController();
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = webAppContextSetup(this.wac).build();
		when(getChristianDatesProvider.get()).thenReturn(getChristianDatesCommand);
		when(getEasterProvider.get()).thenReturn(getEasterCommand);
		when(getNearestHolidayProvider.get()).thenReturn(getNearestHolidayCommand);
		when(getLectionaryByDateProvider.get()).thenReturn(getLectionaryByDateCommand);
		when(getLectionaryProvider.get()).thenReturn(getLectionaryCommand);
	}
	
	@Test
	public void testGetCalendar() throws Exception{
		
		Map<String, Date> cal = new HashMap<String, Date>();
		cal.put("Blah", new Date());
		when(getChristianDatesCommand.setDate(Mockito.any(DateTime.class))).thenReturn(getChristianDatesCommand);
		
		when(getChristianDatesCommand.execute()).thenReturn(cal);
		
		MvcResult mvcResult = this.mockMvc.perform(get("/calendar/christian/")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		Map<String, Date> response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Date>>() { });
		
		Assert.assertEquals(1, response.size());
		
		mvcResult = this.mockMvc.perform(get("/calendar/christian/")
				.accept(MediaType.APPLICATION_JSON)
				.param("date", "04-22-2014")
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Date>>() { });
		
		Assert.assertEquals(1, response.size());
	}
	
	@Test
	public void testGetNearestHoliday() throws Exception{
		
		Set<String> holidays = new HashSet<String>(Arrays.asList(new String[]{"blah"}));
		when(getNearestHolidayCommand.setDate(Mockito.any(DateTime.class))).thenReturn(getNearestHolidayCommand);
		
		when(getNearestHolidayCommand.execute()).thenReturn(holidays);
		
		MvcResult mvcResult = this.mockMvc.perform(get("/calendar/christian/nearestHoliday")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		Set<String> response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Set<String>>() { });
		
		Assert.assertEquals(1, response.size());
		
		mvcResult = this.mockMvc.perform(get("/calendar/christian/nearestHoliday")
				.accept(MediaType.APPLICATION_JSON)
				.param("date", "04-22-2014")
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Set<String>>() { });
		
		Assert.assertEquals(1, response.size());
	}
	
	@Test
	public void testGetEaster() throws Exception{
	
		Date d = new Date();
		when(getEasterCommand.setYear(Mockito.anyInt())).thenReturn(getEasterCommand);
			
		when(getEasterCommand.execute()).thenReturn(d);
		
		MvcResult mvcResult = this.mockMvc.perform(get("/calendar/christian/easter/2016")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		Date response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Date>() { });
		
		Assert.assertEquals(d, response);
		
	}
	
	@Test
	public void testGetLectionaryByDate() throws Exception{
		
		Set<String> verses = new HashSet<String>(Arrays.asList(new String[]{"blah"}));
		when(getLectionaryByDateCommand.setDate(Mockito.any(DateTime.class))).thenReturn(getLectionaryByDateCommand);
		
		when(getLectionaryByDateCommand.execute()).thenReturn(verses);
		
		MvcResult mvcResult = this.mockMvc.perform(get("/calendar/christian/lectionaryVerses")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		Set<String> response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Set<String>>() { });
		
		Assert.assertEquals(1, response.size());
		
		mvcResult = this.mockMvc.perform(get("/calendar/christian/lectionaryVerses")
				.accept(MediaType.APPLICATION_JSON)
				.param("date", "04-22-2014")
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		response = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Set<String>>() { });
		
		Assert.assertEquals(1, response.size());
	}
	
	@Test
	public void testGetLectionary() throws Exception{
		
		Map<String, LectionaryVerseDTO> result = new HashMap<String, LectionaryVerseDTO>();
		when(getLectionaryCommand.setDate(Mockito.any(DateTime.class))).thenReturn(getLectionaryCommand);
		
		when(getLectionaryCommand.execute()).thenReturn(result);
		
		MvcResult mvcResult = this.mockMvc.perform(get("/calendar/christian/lectionary")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		
		Assert.assertNotNull(response);
	}
}
