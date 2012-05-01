package com.vkassin.alexpro;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StreamCorruptedException;
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

import android.content.Context;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.vkassin.alexpro.RSSHandler;
import com.vkassin.alexpro.RSSItem;

public class Common {

	private static final String TAG = "aLexPro.Common"; 

	public enum item_type { IT_NONE, IT_REGULARNEWS, IT_KODEKS };
	public enum paid_type { PT_NONE, PT_OPEN, PT_ONLINE };

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
	public static final String FAV_FNAME = "favourites";
	public static final String SETT_FNAME = "settings";
	
	public static Context app_ctx;

	public static ArrayList<RSSItem> news;
	public static RSSItem curnews;
	private static ArrayList<RSSItem> favourites;
	
	public static TabHost tabHost;
	public static boolean addfav_flag;
	public static String addfav_url;
	public static String title;
	
	public static paid_type paid;
//	public static paid_type paid1;
	public static String curl;
	
	public static void addToFavr(RSSItem item) {
	
		favourites.add(item);
		
		saveFavr();
		
	}
	
	public static void delFavr(int i) {
		
		favourites.remove(i);
		Log.w(TAG, "size1 = " + favourites.size());
		saveFavr();
		
	}
	
	public static ArrayList<RSSItem> getFavrs() {
	
		Log.w(TAG, "size = " + favourites.size());
		return favourites;
	}
	
	public static void saveFavr() {
	
		FileOutputStream fos;
		try {
			
			fos = app_ctx.openFileOutput(FAV_FNAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(favourites);
			os.close();
			fos.close();
			Log.w(TAG, "wrote: " + favourites.size());
			
		} catch (FileNotFoundException e) {

			Toast.makeText(app_ctx, "Файл не записан " + e.toString(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			
			Toast.makeText(app_ctx, "Файл не записан: " + e.toString(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
		Toast.makeText(app_ctx, "Загружено и сохранено", Toast.LENGTH_SHORT).show();

	}
	
	public static void loadFavr() {
	   
	FileInputStream fileInputStream;
	try {
		
		fileInputStream = app_ctx.openFileInput(FAV_FNAME);
		ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
		Object one = oInputStream.readObject();
		favourites = (ArrayList<RSSItem>) one;
		oInputStream.close();
		fileInputStream.close();
		
	} catch (FileNotFoundException e) {
		
		//e.printStackTrace();
  	   Log.i(TAG, "creates blank. no file " + FAV_FNAME);
 	   favourites = new ArrayList<RSSItem>();
 	   
	} catch (StreamCorruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//return favourites;
	}
	
	public static void saveStringToFile(String name, String s) {
		
		FileOutputStream fos;
		try {
			
			fos = app_ctx.openFileOutput(name, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(s);
			os.close();
			fos.close();
			Log.w(TAG, "wrote: " + name);
			
		} catch (FileNotFoundException e) {

			Toast.makeText(app_ctx, "Файл не записан " + e.toString(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			
			Toast.makeText(app_ctx, "Файл не записан: " + e.toString(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
	}

	public static String loadStringFromFile(String name) {
		   
		FileInputStream fileInputStream;
		String s = "";
		try {
			
			fileInputStream = app_ctx.openFileInput(name);
			ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
			Object one = oInputStream.readObject();
			s = (String) one;
			oInputStream.close();
			fileInputStream.close();
			
		} catch (FileNotFoundException e) {
			
			//e.printStackTrace();
	  	   Log.i(TAG, "file not found " + name);
	 	   
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
		}

	public static void saveSettingsToFile(Settings s) {
		
		FileOutputStream fos;
		try {
			
			fos = app_ctx.openFileOutput(SETT_FNAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(s);
			os.close();
			fos.close();
			Log.w(TAG, "wrote: " + SETT_FNAME);
			
		} catch (FileNotFoundException e) {

			Toast.makeText(app_ctx, SETT_FNAME + " Файл не записан " + e.toString(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			
		} catch (IOException e) {
			
			Toast.makeText(app_ctx, SETT_FNAME + " Файл не записан: " + e.toString(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
	}

	public static Settings loadSettingsFromFile() {
		   
		FileInputStream fileInputStream;
		Settings s = null;
		try {
			
			fileInputStream = app_ctx.openFileInput(SETT_FNAME);
			ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
			Object one = oInputStream.readObject();
			s = (Settings) one;
			oInputStream.close();
			fileInputStream.close();
			
		} catch (FileNotFoundException e) {
			
			//e.printStackTrace();
	  	   Log.i(TAG, "file not found " + SETT_FNAME);
	 	   
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
		}
	
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
