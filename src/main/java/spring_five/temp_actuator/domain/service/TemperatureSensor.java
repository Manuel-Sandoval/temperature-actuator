package spring_five.temp_actuator.domain.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

import rx.Observable;
import spring_five.temp_actuator.domain.model.Temperature;

@Component
public class TemperatureSensor {

	private final Random rnd  = new Random();

	public final Observable<Temperature> dataStream = Observable.range(0, Integer.MAX_VALUE)
																.concatMap(this::concatTemperature)
																.publish()
																.refCount();
	
	private Temperature probe() {
		return new Temperature(16 + rnd.nextGaussian() * 10);
	}
	
	public Observable<Temperature> temperatureStream() {
		return dataStream;
	}
	
	private Observable<Temperature> concatTemperature(int tick) {
		return Observable.just(tick)
					     .delay(rnd.nextInt(5000), TimeUnit.MILLISECONDS)
					     .map(tickValue -> this.probe());
	} 
	
}
