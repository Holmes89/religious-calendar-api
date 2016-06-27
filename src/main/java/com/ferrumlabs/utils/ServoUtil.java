package com.ferrumlabs.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.netflix.servo.DefaultMonitorRegistry;
import com.netflix.servo.MonitorRegistry;
import com.netflix.servo.monitor.BasicCounter;
import com.netflix.servo.monitor.Counter;
import com.netflix.servo.monitor.MonitorConfig;
import com.netflix.servo.monitor.StatsTimer;
import com.netflix.servo.stats.StatsConfig;

@Component
public class ServoUtil {

	final private Map<String, Counter> monitorMap = new HashMap<String, Counter>();
	final private Map<String, StatsTimer> timerMap = new HashMap<String, StatsTimer>();

	final private StatsConfig statsConfig;
	final MonitorRegistry monitorRegistry;

	public ServoUtil() {

		monitorRegistry = DefaultMonitorRegistry.getInstance();

		statsConfig = new StatsConfig.Builder().withPublishMax(true)
				.withPublishMin(true).withPublishStdDev(true).build();
	}

	public Counter getCounter(String name) {

		Counter monitor;

		if (monitorMap.containsKey(name)) {

			monitor = monitorMap.get(name);

		} else {

			monitor = new BasicCounter(MonitorConfig.builder(name).build());

			monitorRegistry.register(monitor);

			monitorMap.put(name, monitor);

		}

		return monitor;
	}

	public StatsTimer getTimer(String name) {

		StatsTimer timer;

		if (timerMap.containsKey(name)) {

			timer = timerMap.get(name);

		} else {

			timer = new StatsTimer(MonitorConfig.builder(name).build(),
					statsConfig);

			monitorRegistry.register(timer);

			timerMap.put(name, timer);
		}

		return timer;
	}
}

