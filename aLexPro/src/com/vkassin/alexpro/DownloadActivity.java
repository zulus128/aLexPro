package com.vkassin.alexpro;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class DownloadActivity extends ListActivity {

	private static final String TAG = "aLexPro.DownloadActivity"; 
//	private ListView list;
	private DownloadArrayAdapter adapter;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
        setContentView(R.layout.favs);
                
//        list = (ListView)this.findViewById(R.id.FavsList);

//        list.setEmptyView(findViewById(R.id.empty));
        
    	ArrayList<RSSItem> items = Common.getFavrs();
		adapter = new DownloadArrayAdapter(this, R.layout.favsitem, items);
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
		
        RelativeLayout Layout1 = (RelativeLayout)findViewById(R.id.layout1);
        RelativeLayout Layout2 = (RelativeLayout)findViewById(R.id.layout2);

        if(Common.addfav_flag) {
			
	        Layout1.setVisibility(View.GONE);
	        Layout2.setVisibility(View.VISIBLE);
    		WebView engine = (WebView) findViewById(R.id.web_engine_fav);
//    		engine.setWebViewClient(new HelloWebViewClient());
    		engine.getSettings().setJavaScriptEnabled(true);
    		engine.loadUrl(Common.addfav_url);
		}
		else {
		
	        Layout2.setVisibility(View.GONE);
	        Layout1.setVisibility(View.VISIBLE);

		}
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        
		if (keyCode == KeyEvent.KEYCODE_BACK) {

	        RelativeLayout Layout1 = (RelativeLayout)findViewById(R.id.layout1);
	        RelativeLayout Layout2 = (RelativeLayout)findViewById(R.id.layout2);
	        Layout2.setVisibility(View.GONE);
	        Layout1.setVisibility(View.VISIBLE);
            
	        return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }
	
}
