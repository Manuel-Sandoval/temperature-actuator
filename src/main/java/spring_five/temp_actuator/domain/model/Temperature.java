package spring_five.temp_actuator.domain.model;

public final class Temperature {
	
	private final double value;

	public Temperature(double value) {
		super();
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	
}
