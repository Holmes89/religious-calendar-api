package com.ferrumlabs.commands;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
public class GetNearestHolidayCommandTests {

	@Mock
	ChristianCalendarService ccService;
	
	@InjectMocks
	private GetNearestHolidayCommand cmd = new GetNearestHolidayCommand();
	
	
	private final String MOCKED_RESPONSE = "blah";
	
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);	
	}
	

	@Test
	public void testCommand() throws ServiceException{

		Set<String> holidays = new HashSet<String>(Arrays.asList(new String[]{"blah"}));
		Mockito.when(ccService.getHoliday(Mockito.any(DateTime.class))).thenReturn(holidays);
		
		Set<String> response = cmd.setDate(new DateTime()).execute();
		Assert.assertNotNull(response);
	}
	
	@Test(expected=HystrixBadRequestException.class)
	public void testException() throws ServiceException{
		
		Mockito.when(ccService.getHoliday(Mockito.any(DateTime.class))).thenThrow(ServiceException.class);
		
		cmd.setDate(new DateTime()).execute();
		
	}
	
	
}
