package com.ferrumlabs.services;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ferrumlabs.ReligiousCalendarApiApplication;
import com.ferrumlabs.exceptions.ServiceException;
import com.ferrumlabs.services.interfaces.IChristianCalendarService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReligiousCalendarApiApplication.class)
public class ChristianCalendarServiceTests {

	@Autowired
	IChristianCalendarService ccService;
	
	private DateTime testDate = new DateTime(2016, 4, 20, 0, 0, 0);
	
	@Test
	public void testGetEaster() throws Exception{
		DateTime easter = ccService.getEaster(2016);
		Assert.assertEquals(3, easter.getMonthOfYear());
		Assert.assertEquals(27, easter.getDayOfMonth());
	}
	
	@Test
	public void testGetHolidays() throws Exception{
		Map<String, Date> holidays = ccService.getHolidays(testDate);
		Assert.assertNotNull(holidays);
		DateTime easter = new DateTime(holidays.get("Easter"));
		Assert.assertEquals(3, easter.getMonthOfYear());
		Assert.assertEquals(27, easter.getDayOfMonth());
	}
	
	@Test(expected=ServiceException.class)
	public void testGetHolidays_nullDate() throws Exception{
		ccService.getHolidays(null);
	}
	
	@Test
	public void testGetHoliday() throws Exception{
		Set<String> holiday = ccService.getHoliday(testDate);
		Assert.assertNotNull(holiday);
		Assert.assertTrue(holiday.contains("Fourth Sunday of Easter"));
	}
	
	@Test(expected=ServiceException.class)
	public void testGetHoliday_nullDate() throws Exception{
		ccService.getHoliday(null);
	}

}
