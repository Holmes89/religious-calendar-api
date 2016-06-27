package com.ferrumlabs.commands;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ferrumlabs.services.interfaces.IChristianCalendarService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@Component
@Scope("prototype")
public class GetNearestHolidayCommand extends BaseCommand<Set<String>> {
	
	@Autowired
	IChristianCalendarService ccService;
	
	private DateTime date;
		
	protected GetNearestHolidayCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReligiousCalendarAPI"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetNearestHoliday")));
	}
	
	public GetNearestHolidayCommand setDate(DateTime date){
		this.date=date;
		return this;
	}
	
		
	@Override
	protected Set<String> run() throws Exception {
		try{
			return ccService.getHoliday(date);
		}
		catch(Exception e){
			log.error("error creating getting easter date "+e);
			throw new HystrixBadRequestException("unable to process request", e);
		}
	}

}

