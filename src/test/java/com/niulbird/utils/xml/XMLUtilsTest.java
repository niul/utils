package com.niulbird.utils.xml;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

import com.niulbird.utils.test.BaseTest;
import com.niulbird.utils.xml.XMLUtils;

/**
 * Test class for XMLUtils
 * 
 * @author niul
 */
public class XMLUtilsTest extends BaseTest {
	
	@Test
	public void xslTransformToString() {
		logger.debug("Executing: xslTransformToString");
		String output = XMLUtils.saxTransform("src/test/data/employees.xml", 
				"src/test/data/employeesCsv.xsl");
		logger.debug(output);
		
		assertNotNull(output);
	}
	
	@Test
	public void xslTransformToFile() {
		logger.debug("Executing: xslTransformToFile");
		XMLUtils.staxTransform("src/test/data/employees.xml", 
				"src/test/data/employeesCsv.xsl", 
				"src/test/data/employees.csv");
		
		assertTrue(new File("src/test/data/employees.csv").length() > 0);
	}
	
	public static void main(String[] args) {
		XMLUtilsTest test = new XMLUtilsTest();
		init();
		test.xslTransformToString();
		test.xslTransformToFile();
	}
}