package de.prokyo.network.swaggen.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines a route.<br>
 * If the target is a class, the method which also has a @Route annotation defining the requestType will be called.<br>
 * Adding this on directly to a method works also absolutely fine. In this case u can't use UNKNOWn as a requestType.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Route {

	/**
	 * Gets the route-path of this route.<br>
	 * If the target is a method, the returned route will be appended to the route defined by the class.
	 *
	 * @return The route path of this route
	 */
	String route() default "";

	/**
	 * Gets the request type of the route.<br>
	 * If the requestType is UNKNOWN, the methods of the class have to define the right requestType for themselves.
	 *
	 * @return The requestType of the route
	 */
	RequestType requestType() default RequestType.UNKNOWN;

	/**
	 * Gets the description of this route.
	 *
	 * @return The description of the route
	 */
	String description() default "";

	/**
	 * Gets the summary of this route.
	 *
	 * @return The summary of the route
	 */
	String summary() default "";

	/**
	 * Gets the operation id of this route. The opertation id allows optional documentation features such as "tags".
	 *
	 * @return The operation id of the route
	 */
	String operationId() default ""; // allows to define optional things related to this route (e.g. "tags")

	/**
	 * Defines how all the data/parameters is/are transmitted by the client.
	 */
	public enum RequestType {
		POST,
		GET,
		PUT,
		DELETE,
		/**
		 * In case of this type the route type has to be defined for each route method of the target class itself,<br>
		 * this allows specific implementations of the route based on the request type.
		 */
		UNKNOWN
	}

}
