package com.ferrumlabs.commands;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.ferrumlabs.exceptions.ServiceException;
import com.ferrumlabs.services.impl.ChristianCalendarService;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@RunWith(PowerMockRunner.class)
public class GetChristianDatesCommandTests {

	@Mock
	ChristianCalendarService ccService;
	
	@InjectMocks
	private GetChristianDatesCommand cmd = new GetChristianDatesCommand();
	
	private HashMap<String, Date> dates = new HashMap<String, Date>();
	
	private final String MOCKED_RESPONSE = "blah";
	
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);	
	}
	

	@Test
	public void testCommand() throws ServiceException{

		Mockito.when(ccService.getHolidays(Mockito.any(DateTime.class))).thenReturn(dates);
		
		Map<String, Date> response = cmd.setDate(new DateTime()).execute();
		Assert.assertNotNull(response);
	}
	
	@Test(expected=HystrixBadRequestException.class)
	public void testException() throws ServiceException{
		
		Mockito.when(ccService.getHolidays(Mockito.any(DateTime.class))).thenThrow(ServiceException.class);
		
		cmd.setDate(new DateTime()).execute();
		
	}
	
	
}
