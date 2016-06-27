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
public class GetEasterCommand extends BaseCommand<Date> {
	
	@Autowired
	IChristianCalendarService ccService;
	
	private int year;
		
	protected GetEasterCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReligiousCalendarAPI"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetEaster")));
	}
	
	public GetEasterCommand setYear(int year){
		this.year=year;
		return this;
	}
	
		
	@Override
	protected Date run() throws Exception {
		try{
			return ccService.getEaster(year).toDate();
		}
		catch(Exception e){
			log.error("error creating getting easter date "+e);
			throw new HystrixBadRequestException("unable to process request", e);
		}
	}

}

