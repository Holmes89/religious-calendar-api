package com.ferrumlabs.commands;

import java.util.Arrays;
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
import com.ferrumlabs.services.impl.LectionaryService;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@RunWith(PowerMockRunner.class)
public class GetLectionaryByDateCommandTests {

	@Mock
	LectionaryService lectService;
	
	@InjectMocks
	private GetLectionaryByDateCommand cmd = new GetLectionaryByDateCommand();
	
	
	private final String MOCKED_RESPONSE = "blah";
	
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);	
	}
	

	@Test
	public void testCommand() throws ServiceException{

		Set<String> verses = new HashSet<String>(Arrays.asList(new String[]{"blah"}));
		Mockito.when(lectService.getLectionaryVerses(Mockito.any(DateTime.class))).thenReturn(verses);
		
		Set<String> response = cmd.setDate(new DateTime()).execute();
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}
	
	@Test(expected=HystrixBadRequestException.class)
	public void testException() throws ServiceException{
		
		Mockito.when(lectService.getLectionaryVerses(Mockito.any(DateTime.class))).thenThrow(ServiceException.class);
		
		cmd.setDate(new DateTime()).execute();
		
	}
	
	
}
