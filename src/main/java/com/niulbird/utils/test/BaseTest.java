package com.niulbird.utils.test;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.junit.BeforeClass;

/**
 * Abstract class that all Test Case classes should extend. Contains basic common features such
 * as logging setup.
 * 
 * @author nbird
 */
public abstract class BaseTest {
	protected static Logger logger = Logger.getLogger("com.niulbird.utils.XMLUtilsTest");
	
	/**
	 * Initialise the Test Case. Setup the Logger.
	 */
	@BeforeClass
	public static void init() {
		logger.addAppender(new ConsoleAppender(new PatternLayout("%d %-5p [%c]: %m%n"), 
				ConsoleAppender.SYSTEM_OUT));
		logger.debug("Initializing test cases.");
	}
}
