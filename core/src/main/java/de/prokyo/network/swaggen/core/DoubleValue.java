package de.prokyo.network.swaggen.core;

/**
 * Represents a node holding a double value.
 */
public class DoubleValue extends Value<Double> {

	/**
	 * Constructor.
	 *
	 * @param value The value of this node
	 */
	public DoubleValue(Double value) {
		super(value);
	}

	@Override
	public String toString() {
		return String.valueOf(this.getValue());
	}

}
