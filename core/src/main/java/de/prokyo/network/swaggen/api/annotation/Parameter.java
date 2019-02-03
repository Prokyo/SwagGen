package de.prokyo.network.swaggen.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines one parameter of a route and some general information (e.g. type, transmission type...)<br>
 * When the target is a class, this annotation will be used for all route-methods of the class.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Parameter {

	/**
	 * Gets the data type of the parameters value.<br>
	 * See <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md#data-types">the official documentation</a><br>
	 * for a list of all data types.
	 *
	 * @return The data type of the parameters value
	 */
	String type();

	/**
	 * gets the name(key) of the parameter.
	 *
	 * @return The name of key of the parameter
	 */
	String name();

	/**
	 * Gets a description of the parameter.
	 *
	 * @return A description of the parameter
	 */
	String description() default "";

	/**
	 * Gets whether the paramter is required for this route.
	 *
	 * @return Whether the parameter is required for this route or not
	 */
	boolean required() default false;

	/**
	 * Gets the parameterType of this parameter.<br>
	 * The type defines how the paramter shall be transmitted.
	 *
	 * @return The parameters type
	 */
	ParameterType parameterType();

	/**
	 * Defines the way how a paramter shall be transmitted.
	 */
	public enum ParameterType {
		QUERY,
		HEADER,
		FORM_DATA,
		PATH,
		BODY
	}

}
