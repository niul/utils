package com.niulbird.utils.test;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import org.junit.BeforeClass;

/**
 * Abstract class that all Test Case classes should extend. Contains basic common features such
 * as logging setup.
 * 
 * @author nbird
 */
public abstract class BaseTest {
	protected static Logger logger = (Logger)LogManager.getLogger();
	
	/**
	 * Initialise the Test Case. Setup the Logger.
	 */
	@BeforeClass
	public static void init() {
		logger.addAppender(null);
		
		PatternLayout layout = PatternLayout.newBuilder()
				  .withPattern("%d %-5p [%c]: %m%n")
				  .build();
		ConsoleAppender.Builder consoleAppenderBuilder = ConsoleAppender.newBuilder()
			    .setName("TEST")
			    .setLayout(layout);
		ConsoleAppender appender = consoleAppenderBuilder.build();
		logger.addAppender(appender);
		
		logger.debug("Initializing test cases.");
	}
}
