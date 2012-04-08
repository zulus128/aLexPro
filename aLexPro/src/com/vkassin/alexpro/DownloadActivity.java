package com.vkassin.alexpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.app.ListActivity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.ProgressBar;

public class DownloadActivity extends ListActivity {

	private static final String TAG = "aLexPro.DownloadActivity"; 
//	private ListView list;
	private DownloadArrayAdapter adapter;
	private ProgressBar pb;
	private WebView engine;
	
	private String ccc;
	
	class MyJavaScriptInterface  
	{  
	    @SuppressWarnings("unused")  
	    public void showHTML(String html)  
	    {  
//	        new AlertDialog.Builder(myApp)  
//	            .setTitle("HTML")  
//	            .setMessage(html)  
//	            .setPositiveButton(android.R.string.ok, null)  
//	        .setCancelable(false)  
//	        .create()  
//	        .show();
	    	
	    	ccc = html;
	    	
	    	Log.w(TAG, ccc);
	    }  
	} 
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
        setContentView(R.layout.favs);
            
		engine = (WebView) findViewById(R.id.web_engine_fav);
		engine.getSettings().setJavaScriptEnabled(true);
		engine.getSettings().setDomStorageEnabled(true);
		engine.getSettings().setAppCacheMaxSize(1024*1024*8);
//        engine.getSettings().setAppCachePath("/data/data/de.app/cache");
//		engine.getSettings().setAllowFileAccess(true);
//		engine.getSettings().setAppCacheEnabled(true);
		String appCachePath = "/data/data/com.vkassin.alexpro/cache";//getApplicationContext().getCacheDir().getAbsolutePath();
		engine.getSettings().setAppCachePath(appCachePath);
		engine.getSettings().setAllowFileAccess(true);
		engine.getSettings().setAppCacheEnabled(true);
//		engine.clearCache(false);
		
		pb = (ProgressBar)findViewById(R.id.ProgressBar02);

	    engine.setWebChromeClient(new WebChromeClient() {
			
	        public boolean onConsoleMessage(ConsoleMessage cmsg)
	        {
	            // check secret prefix
	            if (cmsg.message().startsWith("MAGIC"))
	            {
	                String msg = cmsg.message().substring(5); // strip off prefix

	                /* process HTML */
	                ccc = msg;

	                return true;
	            }

	            return false;
	        }
	        
			public void onProgressChanged(WebView view, int progress) {
				
				if(progress < 100)
					pb.setVisibility(View.VISIBLE);
				else {
					
					pb.setVisibility(View.GONE);
					
//					engine.saveWebArchive(getFilesDir().getAbsolutePath() + File.separator + "my.webarchive");
//					engine.saveWebArchive("file:///android_asset/qqq");
					
//					String savename = "ggg.webarchive";
//					ValueCallback<String> callback = null;
//					engine.saveWebArchive(getFilesDir().getAbsolutePath() + File.separator  + savename , true , callback);
//					engine.saveWebArchive(getFilesDir().getAbsolutePath() + File.separator  + savename);
//					Log.i("~~~~~~~~~~~~~callback~~~~~~~~~~~~~~","" + callback);
//					engine.loadUrl("file://" +getFilesDir().getAbsolutePath() + File.separator  + savename);

				}
					
//				activity.setProgress(progress * 1000);
//				Log.w(TAG, ""+progress);
			}
		});
	    
	    engine.setWebViewClient(new WebViewClient() {
	    	
	    	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

	    		pb.setVisibility(View.GONE);
	    	}
	    	
	    	   @Override  
	    	    public void onPageFinished(WebView view, String url)  
	    	    {  
	    	        /* This call inject JavaScript into the page which just finished loading. */  
//	    	        engine.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
	    	        Log.w(TAG, "logggg");
	    	        engine.loadUrl("javascript:console.log('MAGIC'+document.getElementsByTagName('html')[0].innerHTML);");
//	    	        engine.loadUrl("javascript:console.log('MAGIC'+document.getElementsByTagName('body')[0].outerText);");
	    	    }  
	    });

//        list = (ListView)this.findViewById(R.id.FavsList);

//        list.setEmptyView(findViewById(R.id.empty));
        
 //   	ArrayList<RSSItem> items = Common.getFavrs();
		adapter = new DownloadArrayAdapter(this, R.layout.favsitem, new ArrayList<RSSItem>());
//    	list.setAdapter(adapter);
		setListAdapter(adapter);

//    	list.setOnItemClickListener(new OnItemClickListener() {
//			
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//				
//			}
//		});
		 
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		//Log.i(TAG,"click");
		final RSSItem it = adapter.getItems().get(position);
		
		this.getListView().setVisibility(View.GONE);
        engine.setVisibility(View.VISIBLE);
        
        
        engine.clearView();
        
//		engine.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
//    	engine.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
//        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);
//        if(cm.getActiveNetworkInfo().isConnected()){
//        	engine.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        	engine.loadUrl(it.mplink);
//        }
//        else{
//        	engine.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        	engine.loadUrl(it.mplink);
//        }	
		
//		engine.setWebViewClient(new WebViewClient() {
//		    @Override
//		    public void onReceivedError(WebView view, int errorCode,
//		            String description, String failingUrl)
//		    {
//		        if (it.mplink.equals(failingUrl))
//		        {
//		            Log.e(TAG, "try to load from cache");
//		            view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
//		            view.loadUrl(it.mplink);
//		            return;
//		        }
//		        else
//		        if (it.mplink.equals(failingUrl))
//		        {
//		            Log.e(TAG, "cache failed as well, load a local resource as last resort");
//		            // or inform the user
//		        }
//		        super.onReceivedError(view, errorCode, description, failingUrl);
//		    }
//		});
		
		
//		engine.loadUrl("file://"+getFilesDir().getAbsolutePath() + File.separator + "my.webarchive");
//		engine.loadUrl("file:///android_asset/qqq");
        Log.w(TAG, it.mplink);
		
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpContext localContext = new BasicHttpContext();
//		HttpGet httpGet = new HttpGet(it.mplink);
//		String result = "";
//		try{
//		HttpResponse response = httpClient.execute(httpGet, localContext);
//
//		BufferedReader reader = new BufferedReader(
//		    new InputStreamReader(
//		      response.getEntity().getContent()
//		    )
//		  );
//
//		String line = null;
//		while ((line = reader.readLine()) != null){
//		  result += line + "\n";
//
//		}
//		}
//		catch(Exception e) {
//			
//			Log.w(TAG, "ERROR !!!!!!!!!!!!!");
//		}
//		
//		ccc = result;
		
		
//		engine.loadUrl(it.mplink);
        Log.w(TAG, "CCC = " + ccc);
//		engine.loadData(ccc, "text/html", "utf-8");
//		engine.loadDataWithBaseURL(null, ccc, "text/html", "utf-8", null);

//		String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />" + ccc;
		// lets assume we have /assets/style.css file
//		engine.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "UTF-8", null);
	
        
        
		String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> <html>" + ccc + "</html>";
        engine.loadDataWithBaseURL("file://"+getFilesDir().getAbsolutePath() + File.separator, htmlData, "text/html", "UTF-8", null);
	}
	
	protected void onResume() {
		
		super.onResume();
		
		adapter.setItems(Common.getFavrs());
		adapter.notifyDataSetChanged();
        
//		this.getListView().setVisibility(View.GONE);
//		WebView engine = (WebView) findViewById(R.id.web_engine_fav);
//        engine.setVisibility(View.VISIBLE);
//		engine.getSettings().setJavaScriptEnabled(true);
//		engine.loadUrl("http://www.rbc.ru");

//        RelativeLayout Layout1 = (RelativeLayout)findViewById(R.id.layout1);
//        RelativeLayout Layout2 = (RelativeLayout)findViewById(R.id.layout2);

        if(Common.addfav_flag) {
			
        	Common.addfav_flag = false;
    		this.getListView().setVisibility(View.GONE);
            engine.setVisibility(View.VISIBLE);
    		engine.loadUrl(Common.addfav_url);
//			engine.loadUrl("file://" +getFilesDir().getAbsolutePath() + File.separator  + "ggg.webarchive");
  		
//    		pb.setVisibility(View.VISIBLE);

		}
		else {
		
    		this.getListView().setVisibility(View.VISIBLE);
    		WebView engine = (WebView) findViewById(R.id.web_engine_fav);
            engine.setVisibility(View.GONE);

		}
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			adapter.setItems(Common.getFavrs());
			adapter.notifyDataSetChanged();

    		this.getListView().setVisibility(View.VISIBLE);
    		WebView engine = (WebView) findViewById(R.id.web_engine_fav);
            engine.setVisibility(View.GONE);
            engine.stopLoading();
            pb.setVisibility(View.GONE);
            
	        return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }
	
	
}