package com.ferrumlabs.commands;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ferrumlabs.dto.LectionaryVerseDTO;
import com.ferrumlabs.services.interfaces.ILectionaryService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@Component
@Scope("prototype")
public class GetLectionaryCommand extends BaseCommand<Map<String, LectionaryVerseDTO>> {
	
	@Autowired
	ILectionaryService lectService;
	
	private DateTime date;
		
	protected GetLectionaryCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReligiousCalendarAPI"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetLectionary")));
	}
	
	public GetLectionaryCommand setDate(DateTime date){
		this.date=date;
		return this;
	}
	
		
	@Override
	protected Map<String, LectionaryVerseDTO> run() throws Exception {
		try{
			return lectService.getLectionary(date);
		}
		catch(Exception e){
			log.error("error creating getting lectionary "+e);
			throw new HystrixBadRequestException("unable to process request", e);
		}
	}

}

