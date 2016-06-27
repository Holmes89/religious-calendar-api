package com.ferrumlabs.factories;

import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ferrumlabs.ReligiousCalendarApiApplication;
import com.ferrumlabs.enums.ChristianSpecialDatesEnum;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReligiousCalendarApiApplication.class)
public class ChristianCalendarFactoryTests {

	@Autowired
	ChristianCalendarFactory ccFactory;
	
	@Test
	public void testInit_2015(){
		Map<ChristianSpecialDatesEnum, DateTime> events = ccFactory.getEventMap(2015);
		
		DateTime firstSundayAdvent = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_ADVENT);
		Assert.assertEquals(11, firstSundayAdvent.getMonthOfYear());
		Assert.assertEquals(29, firstSundayAdvent.getDayOfMonth());
		
		DateTime secondSundayAdvent = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_ADVENT);
		Assert.assertEquals(12, secondSundayAdvent.getMonthOfYear());
		Assert.assertEquals(6, secondSundayAdvent.getDayOfMonth());
		
		DateTime thirdSundayAdvent = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_ADVENT);
		Assert.assertEquals(12, thirdSundayAdvent.getMonthOfYear());
		Assert.assertEquals(13, thirdSundayAdvent.getDayOfMonth());
		
		DateTime fourthSundayAdvent = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_ADVENT);
		Assert.assertEquals(12, fourthSundayAdvent.getMonthOfYear());
		Assert.assertEquals(20, fourthSundayAdvent.getDayOfMonth());
		
		DateTime firstSundayChristmas = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_CHRISTMAS);
		Assert.assertEquals(12, firstSundayChristmas.getMonthOfYear());
		Assert.assertEquals(27, firstSundayChristmas.getDayOfMonth());
		
		DateTime secondSundayChristmas = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_CHRISTMAS);
		Assert.assertEquals(1, secondSundayChristmas.getMonthOfYear());
		Assert.assertEquals(3, secondSundayChristmas.getDayOfMonth());
		
		DateTime epiphany = events.get(ChristianSpecialDatesEnum.EPIPHANY);
		Assert.assertEquals(1, epiphany.getMonthOfYear());
		Assert.assertEquals(6, epiphany.getDayOfMonth());
		
		DateTime firstSundayEpiphany = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, firstSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(10, firstSundayEpiphany.getDayOfMonth());
		
		DateTime secondSundayEpiphany = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, secondSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(17, secondSundayEpiphany.getDayOfMonth());
		
		DateTime thirdSundayEpiphany = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, thirdSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(24, thirdSundayEpiphany.getDayOfMonth());
		
		DateTime fourthSundayEpiphany = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, fourthSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(31, fourthSundayEpiphany.getDayOfMonth());
		
		DateTime fifthSundayEpiphany = events.get(ChristianSpecialDatesEnum.FIFTH_SUNDAY_EPIPHANY);
		Assert.assertNull(fifthSundayEpiphany);
		
		DateTime transfiguration = events.get(ChristianSpecialDatesEnum.TRANSFIGURATION_SUNDAY);
		Assert.assertEquals(2, transfiguration.getMonthOfYear());
		Assert.assertEquals(7, transfiguration.getDayOfMonth());
		
		DateTime ashWednesday = events.get(ChristianSpecialDatesEnum.ASH_WEDNESDAY);
		Assert.assertEquals(2, ashWednesday.getMonthOfYear());
		Assert.assertEquals(10, ashWednesday.getDayOfMonth());
		
		DateTime firstSundayLent = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_LENT);
		Assert.assertEquals(2, firstSundayLent.getMonthOfYear());
		Assert.assertEquals(14, firstSundayLent.getDayOfMonth());
		
		DateTime secondSundayLent = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_LENT);
		Assert.assertEquals(2, secondSundayLent.getMonthOfYear());
		Assert.assertEquals(21, secondSundayLent.getDayOfMonth());
		
		DateTime thirdSundayLent = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_LENT);
		Assert.assertEquals(2, thirdSundayLent.getMonthOfYear());
		Assert.assertEquals(28, thirdSundayLent.getDayOfMonth());
		
		DateTime fourthSundayLent = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_LENT);
		Assert.assertEquals(3, fourthSundayLent.getMonthOfYear());
		Assert.assertEquals(6, fourthSundayLent.getDayOfMonth());
		
		DateTime fifthSundayLent = events.get(ChristianSpecialDatesEnum.FIFTH_SUNDAY_LENT);
		Assert.assertEquals(3, fifthSundayLent.getMonthOfYear());
		Assert.assertEquals(13, fifthSundayLent.getDayOfMonth());
		
		DateTime palmSunday = events.get(ChristianSpecialDatesEnum.PALM_SUNDAY);
		Assert.assertEquals(3, palmSunday.getMonthOfYear());
		Assert.assertEquals(20, palmSunday.getDayOfMonth());
		
		DateTime easterSunday = events.get(ChristianSpecialDatesEnum.EASTER);
		Assert.assertEquals(3, easterSunday.getMonthOfYear());
		Assert.assertEquals(27, easterSunday.getDayOfMonth());
		
		DateTime secondSundayEaster = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_EASTER);
		Assert.assertEquals(4, secondSundayEaster.getMonthOfYear());
		Assert.assertEquals(3, secondSundayEaster.getDayOfMonth());
		
		DateTime thirdSundayEaster = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_EASTER);
		Assert.assertEquals(4, thirdSundayEaster.getMonthOfYear());
		Assert.assertEquals(10, thirdSundayEaster.getDayOfMonth());
		
		DateTime fourthSundayEaster = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_EASTER);
		Assert.assertEquals(4, fourthSundayEaster.getMonthOfYear());
		Assert.assertEquals(17, fourthSundayEaster.getDayOfMonth());
		
		DateTime fifthSundayEaster = events.get(ChristianSpecialDatesEnum.FIFTH_SUNDAY_EASTER);
		Assert.assertEquals(4, fifthSundayEaster.getMonthOfYear());
		Assert.assertEquals(24, fifthSundayEaster.getDayOfMonth());
		
		DateTime sixthSundayEaster = events.get(ChristianSpecialDatesEnum.SIXTH_SUNDAY_EASTER);
		Assert.assertEquals(5, sixthSundayEaster.getMonthOfYear());
		Assert.assertEquals(1, sixthSundayEaster.getDayOfMonth());
		
		DateTime ascension = events.get(ChristianSpecialDatesEnum.ASCENSION);
		Assert.assertEquals(5, ascension.getMonthOfYear());
		Assert.assertEquals(5, ascension.getDayOfMonth());
		
		DateTime ascensionSunday = events.get(ChristianSpecialDatesEnum.ASCENSION_SUNDAY);
		Assert.assertEquals(5, ascensionSunday.getMonthOfYear());
		Assert.assertEquals(8, ascensionSunday.getDayOfMonth());
		
		DateTime pentecost = events.get(ChristianSpecialDatesEnum.PENTECOST);
		Assert.assertEquals(5, pentecost.getMonthOfYear());
		Assert.assertEquals(15, pentecost.getDayOfMonth());
		
		DateTime trinitySunday = events.get(ChristianSpecialDatesEnum.TRINITY_SUNDAY);
		Assert.assertEquals(5, trinitySunday.getMonthOfYear());
		Assert.assertEquals(22, trinitySunday.getDayOfMonth());
		
		DateTime secondSundayPentecost = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_PENTECOST);
		Assert.assertEquals(5, secondSundayPentecost.getMonthOfYear());
		Assert.assertEquals(29, secondSundayPentecost.getDayOfMonth());
		
		DateTime thirdSundayPentecost = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_PENTECOST);
		Assert.assertEquals(6, thirdSundayPentecost.getMonthOfYear());
		Assert.assertEquals(5, thirdSundayPentecost.getDayOfMonth());
		
		DateTime twentySixthSundayPentecost = events.get(ChristianSpecialDatesEnum.TWENTY_SIXTH_SUNDAY_PENTECOST);
		Assert.assertEquals(11, twentySixthSundayPentecost.getMonthOfYear());
		Assert.assertEquals(13, twentySixthSundayPentecost.getDayOfMonth());
		
		DateTime twentySeventhSundayPentecost = events.get(ChristianSpecialDatesEnum.TWENTY_SEVENTH_SUNDAY_PENTECOST);
		Assert.assertNull(twentySeventhSundayPentecost);
		
		DateTime christKing = events.get(ChristianSpecialDatesEnum.CHRIST_IS_KING);
		Assert.assertEquals(11, christKing.getMonthOfYear());		
		Assert.assertEquals(20, christKing.getDayOfMonth());
	}
	
	@Test
	public void testInit_2016(){
		Map<ChristianSpecialDatesEnum, DateTime> events = ccFactory.getEventMap(2016);
		
		DateTime firstSundayAdvent = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_ADVENT);
		Assert.assertEquals(11, firstSundayAdvent.getMonthOfYear());
		Assert.assertEquals(27, firstSundayAdvent.getDayOfMonth());
		
		DateTime secondSundayAdvent = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_ADVENT);
		Assert.assertEquals(12, secondSundayAdvent.getMonthOfYear());
		Assert.assertEquals(4, secondSundayAdvent.getDayOfMonth());
		
		DateTime thirdSundayAdvent = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_ADVENT);
		Assert.assertEquals(12, thirdSundayAdvent.getMonthOfYear());
		Assert.assertEquals(11, thirdSundayAdvent.getDayOfMonth());
		
		DateTime fourthSundayAdvent = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_ADVENT);
		Assert.assertEquals(12, fourthSundayAdvent.getMonthOfYear());
		Assert.assertEquals(18, fourthSundayAdvent.getDayOfMonth());
		
		DateTime firstSundayChristmas = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_CHRISTMAS);
		Assert.assertEquals(1, firstSundayChristmas.getMonthOfYear());
		Assert.assertEquals(1, firstSundayChristmas.getDayOfMonth());
		
		DateTime secondSundayChristmas = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_CHRISTMAS);
		Assert.assertNull(secondSundayChristmas);
		
		DateTime epiphany = events.get(ChristianSpecialDatesEnum.EPIPHANY);
		Assert.assertEquals(1, epiphany.getMonthOfYear());
		Assert.assertEquals(6, epiphany.getDayOfMonth());
		
		DateTime firstSundayEpiphany = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, firstSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(8, firstSundayEpiphany.getDayOfMonth());
		
		DateTime secondSundayEpiphany = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, secondSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(15, secondSundayEpiphany.getDayOfMonth());
		
		DateTime thirdSundayEpiphany = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, thirdSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(22, thirdSundayEpiphany.getDayOfMonth());
		
		DateTime fourthSundayEpiphany = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_EPIPHANY);
		Assert.assertEquals(1, fourthSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(29, fourthSundayEpiphany.getDayOfMonth());
		
		DateTime fifthSundayEpiphany = events.get(ChristianSpecialDatesEnum.FIFTH_SUNDAY_EPIPHANY);
		Assert.assertEquals(2, fifthSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(5, fifthSundayEpiphany.getDayOfMonth());
		
		DateTime sixthSundayEpiphany = events.get(ChristianSpecialDatesEnum.SIXTH_SUNDAY_EPIPHANY);
		Assert.assertEquals(2, sixthSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(12, sixthSundayEpiphany.getDayOfMonth());

		DateTime seventhSundayEpiphany = events.get(ChristianSpecialDatesEnum.SEVENTH_SUNDAY_EPIPHANY);
		Assert.assertEquals(2, seventhSundayEpiphany.getMonthOfYear());
		Assert.assertEquals(19, seventhSundayEpiphany.getDayOfMonth());
		
		DateTime eighthSundayEpiphany = events.get(ChristianSpecialDatesEnum.EIGHTH_SUNDAY_EPIPHANY);
		Assert.assertNull(eighthSundayEpiphany);
		
		DateTime transfiguration = events.get(ChristianSpecialDatesEnum.TRANSFIGURATION_SUNDAY);
		Assert.assertEquals(2, transfiguration.getMonthOfYear());
		Assert.assertEquals(26, transfiguration.getDayOfMonth());

		DateTime ashWednesday = events.get(ChristianSpecialDatesEnum.ASH_WEDNESDAY);
		Assert.assertEquals(3, ashWednesday.getMonthOfYear());
		Assert.assertEquals(1, ashWednesday.getDayOfMonth());

		DateTime firstSundayLent = events.get(ChristianSpecialDatesEnum.FIRST_SUNDAY_LENT);
		Assert.assertEquals(3, firstSundayLent.getMonthOfYear());
		Assert.assertEquals(5, firstSundayLent.getDayOfMonth());
		
		DateTime secondSundayLent = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_LENT);
		Assert.assertEquals(3, secondSundayLent.getMonthOfYear());
		Assert.assertEquals(12, secondSundayLent.getDayOfMonth());
		
		DateTime thirdSundayLent = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_LENT);
		Assert.assertEquals(3, thirdSundayLent.getMonthOfYear());
		Assert.assertEquals(19, thirdSundayLent.getDayOfMonth());
		
		DateTime fourthSundayLent = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_LENT);
		Assert.assertEquals(3, fourthSundayLent.getMonthOfYear());
		Assert.assertEquals(26, fourthSundayLent.getDayOfMonth());
		
		DateTime fifthSundayLent = events.get(ChristianSpecialDatesEnum.FIFTH_SUNDAY_LENT);
		Assert.assertEquals(4, fifthSundayLent.getMonthOfYear());
		Assert.assertEquals(2, fifthSundayLent.getDayOfMonth());
		
		DateTime palmSunday = events.get(ChristianSpecialDatesEnum.PALM_SUNDAY);
		Assert.assertEquals(4, palmSunday.getMonthOfYear());
		Assert.assertEquals(9, palmSunday.getDayOfMonth());
		
		DateTime easterSunday = events.get(ChristianSpecialDatesEnum.EASTER);
		Assert.assertEquals(4, easterSunday.getMonthOfYear());
		Assert.assertEquals(16, easterSunday.getDayOfMonth());
		
		DateTime secondSundayEaster = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_EASTER);
		Assert.assertEquals(4, secondSundayEaster.getMonthOfYear());
		Assert.assertEquals(23, secondSundayEaster.getDayOfMonth());
		
		DateTime thirdSundayEaster = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_EASTER);
		Assert.assertEquals(4, thirdSundayEaster.getMonthOfYear());
		Assert.assertEquals(30, thirdSundayEaster.getDayOfMonth());
		
		DateTime fourthSundayEaster = events.get(ChristianSpecialDatesEnum.FOURTH_SUNDAY_EASTER);
		Assert.assertEquals(5, fourthSundayEaster.getMonthOfYear());
		Assert.assertEquals(7, fourthSundayEaster.getDayOfMonth());
		
		DateTime fifthSundayEaster = events.get(ChristianSpecialDatesEnum.FIFTH_SUNDAY_EASTER);
		Assert.assertEquals(5, fifthSundayEaster.getMonthOfYear());
		Assert.assertEquals(14, fifthSundayEaster.getDayOfMonth());
		
		DateTime sixthSundayEaster = events.get(ChristianSpecialDatesEnum.SIXTH_SUNDAY_EASTER);
		Assert.assertEquals(5, sixthSundayEaster.getMonthOfYear());
		Assert.assertEquals(21, sixthSundayEaster.getDayOfMonth());
		
		DateTime ascension = events.get(ChristianSpecialDatesEnum.ASCENSION);
		Assert.assertEquals(5, ascension.getMonthOfYear());
		Assert.assertEquals(25, ascension.getDayOfMonth());
		
		DateTime ascensionSunday = events.get(ChristianSpecialDatesEnum.ASCENSION_SUNDAY);
		Assert.assertEquals(5, ascensionSunday.getMonthOfYear());
		Assert.assertEquals(28, ascensionSunday.getDayOfMonth());
		
		DateTime pentecost = events.get(ChristianSpecialDatesEnum.PENTECOST);
		Assert.assertEquals(6, pentecost.getMonthOfYear());
		Assert.assertEquals(4, pentecost.getDayOfMonth());
		
		DateTime trinitySunday = events.get(ChristianSpecialDatesEnum.TRINITY_SUNDAY);
		Assert.assertEquals(6, trinitySunday.getMonthOfYear());
		Assert.assertEquals(11, trinitySunday.getDayOfMonth());
		
		DateTime secondSundayPentecost = events.get(ChristianSpecialDatesEnum.SECOND_SUNDAY_PENTECOST);
		Assert.assertEquals(6, secondSundayPentecost.getMonthOfYear());
		Assert.assertEquals(18, secondSundayPentecost.getDayOfMonth());
		
		DateTime thirdSundayPentecost = events.get(ChristianSpecialDatesEnum.THIRD_SUNDAY_PENTECOST);
		Assert.assertEquals(6, thirdSundayPentecost.getMonthOfYear());
		Assert.assertEquals(25, thirdSundayPentecost.getDayOfMonth());
		
		DateTime twentyFourthSundayPentecost = events.get(ChristianSpecialDatesEnum.TWENTY_FOURTH_SUNDAY_PENTECOST);
		Assert.assertEquals(11, twentyFourthSundayPentecost.getMonthOfYear());
		Assert.assertEquals(19, twentyFourthSundayPentecost.getDayOfMonth());
				
		DateTime twentyFifthSundayPentecost = events.get(ChristianSpecialDatesEnum.TWENTY_FIFTH_SUNDAY_PENTECOST);
		Assert.assertNull(twentyFifthSundayPentecost);

		DateTime christKing = events.get(ChristianSpecialDatesEnum.CHRIST_IS_KING);
		Assert.assertEquals(11, christKing.getMonthOfYear());		
		Assert.assertEquals(26, christKing.getDayOfMonth());
	}
}
