package com.ferrumlabs.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferrumlabs.enums.LitanyEventsEnum;
import com.ferrumlabs.exceptions.FactoryException;
import com.ferrumlabs.utils.ErrorCodes;

@Component
@Scope("singleton")
public class LectionaryFactory {

	private final String ENG_FILE = "/json/lectionary.json";

	Map<String, Map<LitanyEventsEnum, Set<String>>> lectionary;
	
	protected LectionaryFactory(){
		//No code, singleton, only one instance.
	}
	
	@PostConstruct
	private void init() throws FactoryException{
		Resource resource = new ClassPathResource(ENG_FILE);
		try{
			InputStream resourceInputStream = resource.getInputStream();
			TypeReference<LinkedHashMap<String, LinkedHashMap<String, HashSet<String>>>> typeRef = new TypeReference<LinkedHashMap<String, LinkedHashMap<String, HashSet<String>>>>() {};
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Map<String, Set<String>>> tempMap = mapper.readValue(resourceInputStream, typeRef);
			lectionary = new HashMap<String, Map<LitanyEventsEnum, Set<String>>>();
			for(String year: tempMap.keySet()){
				Map<LitanyEventsEnum, Set<String>> eventVerseMap = new HashMap<LitanyEventsEnum, Set<String>>();
				for(String event: tempMap.get(year).keySet()){
					LitanyEventsEnum e = LitanyEventsEnum.fromDisplayName(event);
					if(e==null){
						throw new FactoryException("Invalid event: "+event, ErrorCodes.INVALID_INPUT);
					}
					eventVerseMap.put(e, tempMap.get(year).get(event));
				} 
				lectionary.put(year, eventVerseMap);
			}
		}catch(IOException e){
			throw new FactoryException("Mapping failure", e);
		}
	}
	
	public Map<LitanyEventsEnum, Set<String>> getLitYear(String yearCode) throws FactoryException{
		if(yearCode==null || yearCode.isEmpty()){
			throw new FactoryException("Year Code cannot be null", ErrorCodes.INVALID_INPUT);
		}
		yearCode = yearCode.toLowerCase();
		if(yearCode.length()>1 || !"abc".contains(yearCode)){
			throw new FactoryException("Year Code can only be a, b, or c", ErrorCodes.INVALID_INPUT);
		}
		return lectionary.get(yearCode);
	}
	
}
