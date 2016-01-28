package es.neivi.seb.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import es.neivi.smb.annotation.EnableSMB;

/**
 * Enables Simple Mongo Broadcaster capability, to be used on @
 * {@link Configuration} classes as follows:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableSEB
 * public class AppConfig {
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import(SEBConfiguration.class)
@Documented
@EnableSMB
public @interface EnableSEB {
	
	/**
	 * Alias for the {@link #mappingBasePackageClass()} attribute.
	 */
	@AliasFor(annotation = EnableSMB.class, attribute = "value")
	String value() default "";

	/**
	 * Base package to scan for annotated components.
	 * <p>
	 */
	@AliasFor(annotation = EnableSMB.class, attribute = "mappingBasePackage")
	String mappingBasePackage() default "";
}
