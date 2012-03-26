package com.vkassin.alexpro;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.util.ByteArrayBuffer;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.vkassin.alexpro.RSSHandler;
import com.vkassin.alexpro.RSSItem;

public class Common {

	private static final String TAG = "aLexPro.Common"; 

	public enum item_type { IT_NONE, IT_REGULARNEWS, IT_TOPNEWS, IT_QA, IT_PODCAST, IT_BANNER };

	public static final String MENU_URL_FOR_REACH = "www.lexpro.ru";
//	public static final String TOPMENU_URL = "http://lexpro.ru/rss";
	public static final String MENU_URL = "http://lexpro.ru/rss";
	public static final String ITEM_TAG = "item";
	public static final String TITLE_TAG = "title";
	public static final String DATE_TAG = "pubDate";
	public static final String LINK_TAG = "link";
	public static final String DESCRIPTION_TAG = "description";
	public static final String TEST_STRING = "export?DocID=";
	public static final String TEST_STRING1 = "lexpro.ru/document/";
	public static final String TEST_STRING2 = "lexpro.ru/stream/documents/print?DocID=";
	public static final String TEST_STRING3 = "http://open.lexpro.ru";
	public static final String TEST_STRING4 = "http://online.lexpro.ru";
	public static final String TEST_STRING5 = "login.php";
	
	public static final String WEB_OPEN = "http://open.lexpro.ru";
//	public static final String WEB_OPEN = "http://lexpro.ru";
	public static final String WEB_ONLINE = "http://online.lexpro.ru";
	

	public static ArrayList<RSSItem> news;
	public static RSSItem curnews;
	
	public static ArrayList<RSSItem> getNews() {
		
		RSSHandler handler = new RSSHandler(item_type.IT_REGULARNEWS);
		String errorMsg = generalWebServiceCall(MENU_URL, handler);
		
		if(errorMsg.length() > 0)
			Log.e(TAG, errorMsg);
		
		return handler.getParsedData();
	}
	
	public static String generalWebServiceCall(String urlStr, ContentHandler handler) {
		
		String errorMsg = "";
		
		try {
			URL url = new URL(urlStr);
			
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
	        urlc.setRequestProperty("User-Agent", "Android Application: aBuh");
	        urlc.setRequestProperty("Connection", "close");
//	        urlc.setRequestProperty("Accept-Charset", "windows-1251");
//	        urlc.setRequestProperty("Accept-Charset", "windows-1251,utf-8;q=0.7,*;q=0.7");
	        urlc.setRequestProperty("Accept-Charset", "utf-8");

	        urlc.setConnectTimeout(1000 * 5); // mTimeout is in seconds
	        urlc.setDoInput(true);
	        urlc.connect();
	        
	        if(urlc.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Get a SAXParser from the SAXPArserFactory.
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
	
				// Get the XMLReader of the SAXParser we created.
				XMLReader xr = sp.getXMLReader();
				
				// Apply the handler to the XML-Reader
				xr.setContentHandler(handler);
	
				// Parse the XML-data from our URL.
				InputStream is = urlc.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(500);
                int current = 0;
                while ((current = bis.read()) != -1) {
                        baf.append((byte) current);
                }
                ByteArrayInputStream bais = new ByteArrayInputStream(baf.toByteArray());
//                Reader isr = new InputStreamReader(bais, "windows-1251");
                Reader isr = new InputStreamReader(bais, "utf-8");
                InputSource ist = new InputSource();
                //ist.setEncoding("UTF-8");
                ist.setCharacterStream(isr);
				xr.parse(ist);
				// Parsing has finished.
				
				bis.close();
				baf.clear();
				bais.close();
				is.close();
	        }
	        
	        urlc.disconnect();
			
		} catch (SAXException e) {
			// All is OK :)
		} catch (MalformedURLException e) {
			Log.e(TAG, errorMsg = "MalformedURLException");
		} catch (IOException e) {
			Log.e(TAG, errorMsg = "IOException");
		} catch (ParserConfigurationException e) {
			Log.e(TAG, errorMsg = "ParserConfigurationException");
		} catch (ArrayIndexOutOfBoundsException e) {
			Log.e(TAG, errorMsg = "ArrayIndexOutOfBoundsException");
		}
		
		return errorMsg;
	}

}
