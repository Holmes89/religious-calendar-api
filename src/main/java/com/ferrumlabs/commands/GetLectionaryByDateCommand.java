package com.ferrumlabs.commands;

import java.util.Date;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ferrumlabs.services.interfaces.ILectionaryService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@Component
@Scope("prototype")
public class GetLectionaryByDateCommand extends BaseCommand<Set<String>> {
	
	@Autowired
	ILectionaryService lectService;
	
	private DateTime date;
		
	protected GetLectionaryByDateCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReligiousCalendarAPI"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetLectionaryByDate")));
	}
	
	public GetLectionaryByDateCommand setDate(DateTime date){
		this.date=date;
		return this;
	}
	
		
	@Override
	protected Set<String> run() throws Exception {
		try{
			return lectService.getLectionaryVerses(date);
		}
		catch(Exception e){
			log.error("error creating getting lectionary "+e);
			throw new HystrixBadRequestException("unable to process request", e);
		}
	}

}

