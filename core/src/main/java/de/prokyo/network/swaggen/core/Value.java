package de.prokyo.network.swaggen.core;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a node/value of a k-dimensional tree.
 *
 * @param <T>
 */
@AllArgsConstructor
@NoArgsConstructor
public abstract class Value<T> {

	@Getter @Setter private transient T value;

	@Override
	public abstract String toString();

}
