package spring_five.temp_actuator.domain.service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import spring_five.temp_actuator.domain.model.Temperature;

@Component
public class TemperatureSensor {

	private final ApplicationEventPublisher publisher;
	private final Random rdm = new Random();
	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	public TemperatureSensor(ApplicationEventPublisher publisher) {
		super();
		this.publisher = publisher;
	}
	
	@PostConstruct
	public void startProcessing() {
		this.executor.schedule(this::probe, 1, TimeUnit.SECONDS);
	}
	
	private void probe() {
		
		double temperature = 16 + rdm.nextGaussian() * 10;
		publisher.publishEvent(new Temperature(temperature));
		
		//schedule the next read after some random delay (0-5 seconds)
		executor.schedule(this::probe, rdm.nextInt(), TimeUnit.MILLISECONDS);
		
	}
	
	
}
