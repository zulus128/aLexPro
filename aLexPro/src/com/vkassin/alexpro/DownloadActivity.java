package com.vkassin.alexpro;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class DownloadActivity extends ListActivity {

	private static final String TAG = "aLexPro.DownloadActivity"; 
//	private ListView list;
	private DownloadArrayAdapter adapter;
	private ProgressBar pb;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
        setContentView(R.layout.favs);
                
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
    		WebView engine = (WebView) findViewById(R.id.web_engine_fav);
            engine.setVisibility(View.VISIBLE);
    		engine.getSettings().setJavaScriptEnabled(true);
    		engine.loadUrl(Common.addfav_url);
    		
    		pb = (ProgressBar)findViewById(R.id.ProgressBar02);
//    		pb.setVisibility(View.VISIBLE);
    		
    	    engine.setWebChromeClient(new WebChromeClient() {
    			
    			public void onProgressChanged(WebView view, int progress) {
    				
    				if(progress < 100)
    					pb.setVisibility(View.VISIBLE);
    				else
    					pb.setVisibility(View.GONE);
    					
//    				activity.setProgress(progress * 1000);
//    				Log.w(TAG, ""+progress);
    			}
    		});
    	    
    	    engine.setWebViewClient(new WebViewClient() {
    	    	
    	    	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

    	    		pb.setVisibility(View.GONE);
    	    	}
    	    });


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
            
	        return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }
	
	
}