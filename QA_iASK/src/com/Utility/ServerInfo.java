package com.Utility;


import java.io.File;
import java.net.Inet6Address;
import java.net.UnknownHostException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class ServerInfo 
{
	public static Integer getTomcatPortFromConfigXml() {
		File serverXml= new File("C:/OxygenWorkspace/Servers/Tomcat v8.0 Server at localhost-config/server.xml");
		   Integer port;
		   try {
		      DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		      domFactory.setNamespaceAware(true); // never forget this!
		      DocumentBuilder builder = domFactory.newDocumentBuilder();
		      Document doc = builder.parse(serverXml);
		      XPathFactory factory = XPathFactory.newInstance();
		      XPath xpath = factory.newXPath();
		      XPathExpression expr = xpath.compile
		        ("/Server/Service[@name='Catalina']/Connector[count(@scheme)=0]/@port[1]");
		      String result = (String) expr.evaluate(doc, XPathConstants.STRING);
		      port =  result != null && result.length() > 0 ? Integer.valueOf(result) : null;
		   } catch (Exception e) {
		     port = null;
		   }
		   return port;
		}
	
	public static String systemIP()
	{	
		String ip=null;
		try {
			String ip1=Inet6Address.getLocalHost().toString();
			String[] arr =ip1.split("/");
			ip=arr[1];
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
}
