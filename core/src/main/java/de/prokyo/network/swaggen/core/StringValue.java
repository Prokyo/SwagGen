package de.prokyo.network.swaggen.core;

/**
 * Represents a node holding a string as his value.
 */
public class StringValue extends Value<String> {

	/**
	 * Constructor.
	 *
	 * @param value The value of this node
	 */
	public StringValue(String value) {
		super(value);
	}

	@Override
	public String toString() {
		return "\"" + this.getValue() + "\"";
	}
}
