package de.prokyo.network.swaggen.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines one possible result of a route.<br>
 * The result is defined by its MIME type and its status code.<br>
 * Results can be suitable for different request types. On this way u can use another MIME type according to a status code<br>
 * when using GET instead of POST for example.<br>
 * When the target is a class, this annotation will be used for all route-methods of the class with according request type.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface RouteResult {

	/**
	 * Gets the request type of the route request (e.g. POST, GET, DELETE & PUT).<br>
	 * If UNKNOWN is defined, the request type has to be defined by the @Route tag of the class or method<br>
	 * depending of the target of this annotation.
	 *
	 * @return The request type this result-documentation is suitable for
	 */
	Route.RequestType requestType() default Route.RequestType.UNKNOWN; // The type has to be defined by the @Route tag

	/**
	 * The MIME type of the result of the according route.
	 *
	 * @return The MIME type of this result
	 */
	String mime();

	/**
	 * The html status code of this result.<br>
	 * See <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">Wikipedias list of status codes</a><br>
	 * for more information about the status codes.
	 *
	 * @return The html status code of this route result
	 */
	int code();

}
