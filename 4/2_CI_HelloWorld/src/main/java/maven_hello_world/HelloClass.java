package maven_hello_world;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;


public class HelloClass {
	
	// This 
	private static final Logger logger = LogManager.getLogger(HelloClass.class);

	public static void main(String[] args) {
		
		// This code snippet changes the Log level FOR ALL THE LOGS, FROM NOW UNTIL THE END OF TIMES
		// This could be a desired behaviour (I only want to log "info" level from everything) or not.
		// If you want more modular control then change the log level for the current class.
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.INFO);
		
		// In the log4j2.xml file there are the the configuration of the logger. In this case
		// It will print to console but also print to file (your_application_name-date.log)
		
		// the log level could also be changed only in the configuration file, avoiding the Configurator.setAllLevels
		// to change it in the log4j2.xml just change line 12 (<Root level="debug">) to (<Root level="info">)
		
		// Play around with it.
		
		logger.debug("This is a debug message!"); // This will NOT be printed, the log level is lower than INFO
		logger.info("This is an info message!");
		logger.warn("This is a warning message!");
		logger.error("This is an error message!");

	}

}
