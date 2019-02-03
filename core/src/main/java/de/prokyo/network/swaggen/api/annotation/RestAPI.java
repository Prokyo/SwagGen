package de.prokyo.network.swaggen.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation has to be defined on the main class of a rest project. It defines some general information about<br>
 * the API itself.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface RestAPI {

	/**
	 * Gets the title of the API.
	 *
	 * @return The title of the API
	 */
	String title() default "";

	/**
	 * Gets the current version of the API.
	 *
	 * @return The current version of the API
	 */
	String version() default "";

}
