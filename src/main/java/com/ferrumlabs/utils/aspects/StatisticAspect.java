package com.ferrumlabs.utils.aspects;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ferrumlabs.utils.ServoUtil;
import com.ferrumlabs.utils.StatisticCounter;
import com.ferrumlabs.utils.StatisticTimer;
import com.netflix.servo.monitor.StatsTimer;
import com.netflix.servo.monitor.Stopwatch;

@Aspect
public class StatisticAspect{
	
	
	private ServoUtil servoUtil;
	
	public void setServoUtil(ServoUtil servoUtil)
	{
		this.servoUtil = servoUtil;
	}
	
	@Before("@annotation(counter)")
	public void incrementCounter(StatisticCounter counter)
	{
		log.debug("incrementing counter for {}", counter.name());
		servoUtil.getCounter(counter.name()).increment();
	}
	
	@Around("@annotation(statisticTimer)")
	public Object startRecording(ProceedingJoinPoint pjp, StatisticTimer statisticTimer) throws Throwable
	{
		log.debug("starting timer for {}",statisticTimer.name());		
	
	StatsTimer timer = servoUtil.getTimer(statisticTimer.name());
	Stopwatch stopwatch = timer.start();
	
	
	try
	{
		Object retVal = pjp.proceed();
		return retVal; 
		
	} finally
	{
		stopwatch.stop();
		timer.record(stopwatch.getDuration(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS);
		log.debug("timer stopped for {}",statisticTimer.name());
		}
		
	}
	
		
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
}
