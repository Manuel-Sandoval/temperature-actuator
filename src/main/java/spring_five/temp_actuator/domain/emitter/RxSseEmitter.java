package spring_five.temp_actuator.domain.emitter;

import java.io.IOException;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import rx.Subscriber;
import spring_five.temp_actuator.domain.model.Temperature;

public class RxSseEmitter extends SseEmitter{

	public static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
	private final Subscriber<Temperature> subscriber;
	
	public RxSseEmitter() {
		super(SSE_SESSION_TIMEOUT);
		this.subscriber = new Subscriber<Temperature>() {

			@Override
			public void onCompleted() {}

			@Override
			public void onError(Throwable e) {}

			@Override
			public void onNext(Temperature t) {
				try {
					RxSseEmitter.this.send(t);
				} catch (IOException e) {
					unsubscribe();
				}
			}
			
		};
	}
	
	public Subscriber<Temperature> getSubscriber() {
		return subscriber;
	}
	
}
