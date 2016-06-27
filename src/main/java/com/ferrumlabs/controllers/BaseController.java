package com.ferrumlabs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class BaseController  {

	public final static String VERSION = "sacredtextapi.v1";
	
	public BaseController()
	{
		super();
	
	}
	
	protected HttpHeaders createEntityHeaders()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-SacredText-Media-Type", VERSION);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	public Logger log()
	{
		return (log);
	}
}