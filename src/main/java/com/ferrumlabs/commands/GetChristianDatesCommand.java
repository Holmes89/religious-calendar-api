package com.ferrumlabs.commands;

import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ferrumlabs.exceptions.ServiceException;
import com.ferrumlabs.services.interfaces.IChristianCalendarService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@Component
@Scope("prototype")
public class GetChristianDatesCommand extends BaseCommand<Map<String, Date>> {
	
	@Autowired
	IChristianCalendarService ccService;
	
	private DateTime date;
		
	protected GetChristianDatesCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReligiousCalendarAPI"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetChristianDates")));
	}
	
	public GetChristianDatesCommand setDate(DateTime date){
		this.date=date;
		return this;
	}
	
		
	@Override
	protected Map<String, Date> run() throws Exception {
		try{
			return ccService.getHolidays(date);
		}
		catch(ServiceException e){
			log.error("error creating getting calendar "+e);
			throw new HystrixBadRequestException("unable to process request", e);
		}
	}

}

