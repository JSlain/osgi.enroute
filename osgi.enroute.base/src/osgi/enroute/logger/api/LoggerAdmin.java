package osgi.enroute.logger.api;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.dto.DTO;

/**
 * An administrative interface to the logger.
 */
@ProviderType
public interface LoggerAdmin {

	class Info extends DTO {
		public String	name;
		public long		bundleId;
		/**
		 * The minimum level for this logger
		 */
		public Level	level;
		/**
		 * A regex pattern matching the thread name. If this is pattern is a
		 * valid regular expression, only logging on that thread is reported.
		 */
		public String	thread;
		/**
		 * If set, print stack traces
		 */
		public boolean	stackTraces;
		/**
		 * If set, show the location of the logging method
		 */
		public boolean	where;
	}

	class Control extends DTO {
		/**
		 * The pattern is a required regex that is matched against:
		 * 
		 * <pre>
		 * 		logger-name [ ':' bundle-symbolicname] [':' bundle-version]]
		 * </pre>
		 * 
		 * Any logger matching this pattern will get the settings (if this
		 * control is the first match of course).
		 */

		public String	pattern;

		/**
		 * The minimum level for this logger
		 */
		public Level	level;
		/**
		 * A regex pattern matching the thread name. If this is pattern is a
		 * valid regular expression, only logging on that thread is reported.
		 */
		public String	thread;

		/**
		 * If set, print stack traces
		 */
		public boolean	stackTraces;
		/**
		 * If set, show the location of the logging method
		 */
		public boolean	where;
	}

	/**
	 * A record to configure the Admin
	 */
	class Settings extends DTO {
		public long				lastModified;
		public String			name;
		public List<Control>	controls	= new ArrayList<>();
	}

	/**
	 * List current set of loggers.
	 * <p>
	 * Filter can match:
	 * 
	 * <pre>
	 * 	logger	- logger name
	 *  bsn     - Bundle symbolic name
	 *  version - version of the bundle
	 * </pre>
	 * 
	 * @param filter
	 *            filter expression to find loggers
	 * @return a list of LogerInfo's
	 */
	List<Info> list(String filter) throws Exception;

	/**
	 * Get the current log settings. Returned objects are a copy.
	 */
	Settings getSettings() throws Exception;

	/**
	 * Update the current log settings
	 */
	void setSettings(Settings settings) throws Exception;

}