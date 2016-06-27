package com.ferrumlabs.commands;

import java.util.Date;
import java.util.List;
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
public class GetEasterCommandTests {

	@Mock
	ChristianCalendarService ccService;
	
	@InjectMocks
	private GetEasterCommand cmd = new GetEasterCommand();
	
	private DateTime date = new DateTime();
	
	private final String MOCKED_RESPONSE = "blah";
	
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);	
	}
	

	@Test
	public void testCommand() throws ServiceException{

		Mockito.when(ccService.getEaster(Mockito.anyInt())).thenReturn(date);
		
		Date response = cmd.setYear(12).execute();
		Assert.assertNotNull(response);
	}
	
	@Test(expected=HystrixBadRequestException.class)
	public void testException() throws ServiceException{
		
		Mockito.when(ccService.getEaster(Mockito.anyInt())).thenThrow(ServiceException.class);
		
		cmd.setYear(12).execute();
		
	}
	
	
}
