package com.ferrumlabs.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;

public abstract class BaseCommand<T> extends HystrixCommand<T> {

	protected BaseCommand(Setter setter){
		super(setter);
	}
	
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
}
