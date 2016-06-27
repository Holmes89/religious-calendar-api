package com.ferrumlabs.controllers;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferrumlabs.commands.GetChristianDatesCommand;
import com.ferrumlabs.commands.GetEasterCommand;
import com.ferrumlabs.commands.GetLectionaryByDateCommand;
import com.ferrumlabs.commands.GetLectionaryCommand;
import com.ferrumlabs.commands.GetNearestHolidayCommand;
import com.ferrumlabs.dto.LectionaryVerseDTO;
import com.ferrumlabs.utils.StatisticCounter;
import com.ferrumlabs.utils.StatisticTimer;

@RequestMapping("/calendar/christian")
@RestController
public class ChristianCalendarController extends BaseController {

	@Autowired
	Provider<GetChristianDatesCommand> getChristianDatesProvider;
	
	@Autowired
	Provider<GetEasterCommand> getEasterProvider;

	@Autowired
	Provider<GetNearestHolidayCommand> getNearestHolidayProvider;
	
	@Autowired
	Provider<GetLectionaryByDateCommand> getLectionaryByDateProvider;
	
	@Autowired
	Provider<GetLectionaryCommand> getLectionaryProvider;
	
	public ChristianCalendarController(){
		super();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/", produces = "application/json")
	@StatisticTimer(name="getChristianDatesTimer")
	@StatisticCounter(name="getChristianDatesCounter")
	public HttpEntity<Map<String, Date>> getCalendar(HttpServletRequest request, @RequestParam(required=false, value="date") @DateTimeFormat(pattern="MM-dd-yyyy") Date date) throws Throwable
	{
		if(date==null){
			date = new Date();
		}
		
		DateTime dateTime = new DateTime(date);
		
		log.info("Request Calendar for date: {}", dateTime);
		return new HttpEntity<Map<String, Date>>(getChristianDatesProvider.get().setDate(dateTime).execute(), createEntityHeaders());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/nearestHoliday", produces = "application/json")
	@StatisticTimer(name="getNearestHolidayTimer")
	@StatisticCounter(name="getNearestHolidayCounter")
	public HttpEntity<Set<String>> getNearestHoliday(HttpServletRequest request, @RequestParam(required=false, value="date") @DateTimeFormat(pattern="MM-dd-yyyy") Date date) throws Throwable
	{
		if(date==null){
			date = new Date();
		}
		
		DateTime dateTime = new DateTime(date);
		
		log.info("Request neareset holiday on date: {}", dateTime);
		return new HttpEntity<Set<String>>(getNearestHolidayProvider.get().setDate(dateTime).execute(), createEntityHeaders());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/easter/{year}", produces = "application/json")
	@StatisticTimer(name="getEasterTimer")
	@StatisticCounter(name="getEasterCounter")
	public HttpEntity<Date> getEaster(HttpServletRequest request, @PathVariable int year) throws Throwable
	{
		
		log.info("Request Easter for year: {}", year);
		return new HttpEntity<Date>(getEasterProvider.get().setYear(year).execute(), createEntityHeaders());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/lectionaryVerses", produces = "application/json")
	@StatisticTimer(name="getLectionaryTimer")
	@StatisticCounter(name="getLectionaryCounter")
	public HttpEntity<Set<String>> getLectionaryByDate(HttpServletRequest request, @RequestParam(required=false, value="date") @DateTimeFormat(pattern="MM-dd-yyyy") Date date) throws Throwable
	{
		if(date==null){
			date = new Date();
		}
		
		DateTime dateTime = new DateTime(date);
		
		log.info("Request leciontary by date: {}", dateTime);
		return new HttpEntity<Set<String>>(getLectionaryByDateProvider.get().setDate(dateTime).execute(), createEntityHeaders());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/lectionary", produces = "application/json")
	@StatisticTimer(name="getLectionaryTimer")
	@StatisticCounter(name="getLectionaryCounter")
	public HttpEntity<Map<String, LectionaryVerseDTO>> getLectionary(HttpServletRequest request, @RequestParam(required=false, value="date") @DateTimeFormat(pattern="MM-dd-yyyy") Date date) throws Throwable
	{
		if(date==null){
			date = new Date();
		}
		
		DateTime dateTime = new DateTime(date);
		
		log.info("Request leciontary by date: {}", dateTime);
		return new HttpEntity<Map<String, LectionaryVerseDTO>>(getLectionaryProvider.get().setDate(dateTime).execute(), createEntityHeaders());
	}
}
