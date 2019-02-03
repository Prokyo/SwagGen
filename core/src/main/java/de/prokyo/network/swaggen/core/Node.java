package de.prokyo.network.swaggen.core;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node of a k-dimensional tree.<br>
 * The difference to {@link Value} is, that this node can contain children.
 */
public class Node extends Value<Node> {

	@Getter private final Map<String, Value> properties = new HashMap<>();

	/**
	 * Constructor.
	 */
	public Node() {
		this.setValue(this);
	}

	/**
	 * Adds the given <i>key</i> and <i>value</i> to the children of this {@link} Node.
	 *
	 * @param key The key of the property
	 * @param value The value of the property
	 */
	public void add(String key, Value value) {
		this.properties.put(key, value);
	}

	/**
	 * Just for debugging purposes.
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "{node}";
	}

}
