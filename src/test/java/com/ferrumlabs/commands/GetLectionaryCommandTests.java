package com.ferrumlabs.commands;

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

import com.ferrumlabs.dto.LectionaryVerseDTO;
import com.ferrumlabs.exceptions.ServiceException;
import com.ferrumlabs.services.impl.LectionaryService;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@RunWith(PowerMockRunner.class)
public class GetLectionaryCommandTests {

	@Mock
	LectionaryService lectService;
	
	@InjectMocks
	private GetLectionaryCommand cmd = new GetLectionaryCommand();
	
	
	private final String MOCKED_RESPONSE = "blah";
	
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);	
	}
	

	@Test
	public void testCommand() throws ServiceException{

		Map<String, LectionaryVerseDTO> lect = new HashMap<String, LectionaryVerseDTO>();
		Mockito.when(lectService.getLectionary(Mockito.any(DateTime.class))).thenReturn(lect);
		
		Map<String, LectionaryVerseDTO> response = cmd.setDate(new DateTime()).execute();
		Assert.assertNotNull(response);
	}
	
	@Test(expected=HystrixBadRequestException.class)
	public void testException() throws ServiceException{
		
		Mockito.when(lectService.getLectionary(Mockito.any(DateTime.class))).thenThrow(ServiceException.class);
		
		cmd.setDate(new DateTime()).execute();
		
	}
	
	
}
