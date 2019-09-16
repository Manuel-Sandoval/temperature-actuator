package spring_five.temp_actuator.domain.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import spring_five.temp_actuator.domain.emitter.RxSseEmitter;
import spring_five.temp_actuator.domain.service.TemperatureSensor;

@RestController
public class TemperatureController {

	private final TemperatureSensor temperatureSensor;
	
	public TemperatureController(TemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}
	
	@RequestMapping(value = "/temperature-stream",
					method = RequestMethod.GET)
	public SseEmitter events(HttpServletRequest request) {
		RxSseEmitter emitter = new RxSseEmitter();

		temperatureSensor.temperatureStream()
						 .subscribe(emitter.getSubscriber());
		
		return emitter;
	}
	
}
