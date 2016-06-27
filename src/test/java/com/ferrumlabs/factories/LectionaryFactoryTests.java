package com.ferrumlabs.factories;

import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ferrumlabs.ReligiousCalendarApiApplication;
import com.ferrumlabs.enums.LitanyEventsEnum;
import com.ferrumlabs.exceptions.FactoryException;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReligiousCalendarApiApplication.class)
public class LectionaryFactoryTests {

	@Autowired
	LectionaryFactory lectFactory;
	
	@Test
	public void testInit() throws FactoryException{
		Map<LitanyEventsEnum, Set<String>> litYear = lectFactory.getLitYear("a");
		Assert.assertEquals(LitanyEventsEnum.values().length, litYear.keySet().size());
		Assert.assertEquals(6, litYear.get(LitanyEventsEnum.GOOD_FRIDAY).size());
		litYear = lectFactory.getLitYear("B");
		Assert.assertEquals(LitanyEventsEnum.values().length, litYear.keySet().size());
		Assert.assertEquals(6, litYear.get(LitanyEventsEnum.GOOD_FRIDAY).size());
		litYear = lectFactory.getLitYear("c");
		Assert.assertEquals(LitanyEventsEnum.values().length, litYear.keySet().size());
		Assert.assertEquals(6, litYear.get(LitanyEventsEnum.GOOD_FRIDAY).size());
	}
	
	@Test(expected=FactoryException.class)
	public void test_null_year() throws FactoryException{
		lectFactory.getLitYear(null);
	}
	
	@Test(expected=FactoryException.class)
	public void test_empty_year() throws FactoryException{
		lectFactory.getLitYear("");
	}
	
	@Test(expected=FactoryException.class)
	public void test_invalid_year_long() throws FactoryException{
		lectFactory.getLitYear("abc");
	}
	
	@Test(expected=FactoryException.class)
	public void test_invalid_year_character() throws FactoryException{
		lectFactory.getLitYear("z");
	}
}
