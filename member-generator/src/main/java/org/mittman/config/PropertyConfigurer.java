
package org.mittman.config;

import java.util.Properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * A Configurer that configures objects from a set of properties
 * @author Edward Mittman
 *
 * @param <T>
 */
public abstract class PropertyConfigurer<T> implements Configurer<T> {
	@Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
	private Properties properties;

}
