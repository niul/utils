package com.niulbird.utils.xml;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Class to manage XML utilities such as:
 * 	- perform XSL transformation
 * 
 * @author niul
 */
public class XMLUtils {
	public static Logger logger = Logger.getLogger("com.niulbird.utils");
	
	/**
	 * Transform an input XML string using provided XSLT file.
	 * 
	 * @param xmlFile Input XML to transform.
	 * @param xsltFile XSLT file to perform the transformation.
	 * 
	 * @return Transformation of the XML.
	 */
	public static String saxTransform(String xmlFile, String xsltFile) {
		StringWriter outWriter = new StringWriter();
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(
					xsltFile));
			transformer.transform(new StreamSource(xmlFile), new StreamResult(
					outWriter));
		} catch (Exception e) {
			logger.error("Cannot perform transform: " + e.getMessage(), e);
		}
		
		return outWriter.toString();
	}


	/**
	 * Transform an input XML string using provided XSLT file and output to a file.
	 * 
	 * @param xmlFile Input XML to transform.
	 * @param xsltFile XSLT file to perform the transformation.
	 * @param xmlOutputFile Out put file for transformation.
	 */
	public static void staxTransform(String xmlInputFile, String xsltFile,
			String xmlOutputFile) {
		XMLInputFactory inputFactory = null;
		XMLOutputFactory outputFactory = null;
		XMLStreamReader streamReaderXSL = null;
		XMLStreamReader streamReaderXML = null;
		XMLStreamWriter streamWriter = null;
		Transformer transf = null;

		// get an instance of the XMLInputFactory class
		inputFactory = XMLInputFactory.newInstance();

		// get an instance of the XMLOutputFactory class
		outputFactory = XMLOutputFactory.newInstance();

		// get the XMLEventReader objects
		try {
			streamReaderXSL = inputFactory.createXMLStreamReader(
					"file://data//", new FileReader(xsltFile));
			streamReaderXML = inputFactory.createXMLStreamReader(
					"file://data//", new FileReader(xmlInputFile));
		} catch (java.io.FileNotFoundException e) {
			logger.error("Cannot find file: " + e.getMessage(), e);
		} catch (javax.xml.stream.XMLStreamException e) {
			logger.error("Cannot read in XML stream: " + e.getMessage(), e);
		}

		// get a TransformerFactory object
		TransformerFactory transfFactory = TransformerFactory.newInstance();

		// define the Source object for the stylesheet
		Source XSL = new StAXSource(streamReaderXSL);

		// get a Transformer object
		try {
			transf = transfFactory.newTransformer(XSL);
		} catch (javax.xml.transform.TransformerConfigurationException e) {
			logger.error("Cannot create transform object: " + e.getMessage(), e);
		}

		// define the Source object for the XML document
		Source XML = new StAXSource(streamReaderXML);

		// create an XMLStreamWriter object
		try {
			streamWriter = outputFactory.createXMLStreamWriter(new FileWriter(
					xmlOutputFile));
		} catch (java.io.IOException e) {
			logger.error("Cannot create output stream: " + e.getMessage(), e);
		} catch (javax.xml.stream.XMLStreamException e) {
			logger.error("Cannot create output stream: " + e.getMessage(), e);
		}

		// define the Result object
		Result XML_r = new StAXResult(streamWriter);

		// call the transform method
		try {
			transf.transform(XML, XML_r);
		} catch (javax.xml.transform.TransformerException e) {
			logger.error("Error performing transform: " + e.getMessage(), e);
		}

		// clean up
		try {
			streamReaderXSL.close();
			streamReaderXML.close();
			streamWriter.close();
		} catch (javax.xml.stream.XMLStreamException e) {
			logger.error("Cannot clean up objects: " + e.getMessage(), e);
		}
	}
}